package Main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable  { //so my Game panel is inherited of JPanel
     //$$$ Screen settings $$$
    //Now i am doing some screen settings
    public final int originalTitleSize=16; //we will create our every characfter or anything using 16x16 pixel it is said original tiles size because we will scale it  , look out the next variable
    public final int scale=3; //now it will (originalTitleSize x scale) so i will get 48x48 pixel character so that in my monitor my character will look bigger
    public final int tileSize =originalTitleSize*scale; // now it will show the tiles size to the screen

    //$$$ now screen size $$$
    public final int maxScreenCol=20; //here will be 16 column of titles
    public final int maxScreenRow=12; //here will be 12 row of titles

    public final int screenWidth= tileSize*maxScreenCol; //768 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //576 pixel height


    // $$$$$$$$$  World Setting $$$$$$$$$
    public  int maxWorldRow;
    public  int maxWorldCol;
    public final int worldWidth=tileSize* maxScreenCol;
    public final int worldHeight=tileSize* maxScreenRow;
    public final int maxMap=10;
    public int currentMap=1;




    public boolean fullScreenOn = false;


    // $$$$$$$$$  FPS  $$$$$$$$$
    int FPS=60;

    // $$$$$$$$$ System $$$$$$$$$
    //instantiates new instances
    TileManager tileM= new TileManager(this);
    public KeyHandler keyHandler= new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    //to save the data of game
    Config config= new Config(this);
    //This class is made for checking collision
    public CollisionChecker cChecker= new CollisionChecker(this);
    public EventHandler eHandler= new EventHandler(this);
    //Setting all the asset by using aSetter [npc,monster,key,door,dry tree etc...]
    public AssetSetter aSetter = new AssetSetter(this);
    //This class is for using all the UI for the game.
    public UI ui = new UI(this);


            // $$$$$$$$$ Entity and Object $$$$$$$$$

    public Player player= new Player(this,keyHandler);
    public Entity obj[][]= new Entity[maxMap][20]; //set the number of objects 10;
    public Entity npc[][]= new Entity[maxMap][10]; //set the number of 10
    public Entity monster[][]= new Entity[maxMap][20];
    public InteractiveTile iTile[][]= new InteractiveTile[maxMap][50];

    //Now creating the arrayList
    public ArrayList<Entity>projectileList= new ArrayList<>();
    public ArrayList<Entity> particleList= new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // $$$$$$$$$  GAME STATE $$$$$$$$$
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;
    public final int optionState=5;
    public final int gameOverState=6;
    public final int transitionState=7;
    public final int tradeState=8;


    //>so to do this animation in our game we need to create a time in our game we need to start this game clock.
    // and to do that we will use a class called Thread and i will mane this gameThread.

    Thread gameThread;


    //Now i am creating constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // setFocusable(true) => with this GamePanel can be "focused " to recieve key input
    }

    //This is the setup method for setting up the game to play. [this is first method we will call for our game]
    public void setupGame(){
    aSetter.setObject();
    aSetter.setNPC();
    aSetter.setMonster();
    aSetter.setInteractiveTile();
    //playMusic(0); // 0 -> because we want to play first index of the music
    //stating the game
    gameState=titleState;

    }
    //This is a Retry method for using the functionalblity of retry
    //this method only set default player position and restore life and mana , and set again npc and monster.
    public void retry(){
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    //This is a Restart method for using teh functionality of restart
    //this method clear all the game status and make a new game
    public void restart(){
        player.setDefaultValues();
        player.setItems();
        //setting objects
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }
    //to create a gameThread start function
    //This is the function to run the game.
    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }

    //This the run function to run the game thread ...
    @Override
    public void run() {
    //In this run method, we will create a game loop which will be the core of our game;
        double drawInterval= 1000000000/FPS; //1 billion nano second //0.016666 seconds
        double nextDrawTime= System.nanoTime()+drawInterval;
        while (gameThread !=null){ //this means, as long as this gameThread exists , it repeats the process that is writen inside of the these brackets
            //System.out.println("Game loop is running");
            // in this loop we will do two things here
            //long currentTime= System.nanoTime(); //System.nanoTime()=> returns the current value of the running java virtual machine's high-resolution time source in nano secondss


            // 1 UPDATE: update information such as character position
            update();

            // 2 DRAW: draw the screen with the Updated information
            repaint(); //not paintComponent ! we will call repaint to call paintComponent.

            try {
            double remainingTime=nextDrawTime-System.nanoTime();
            remainingTime=remainingTime/1000000;

            if(remainingTime<0){
                remainingTime=0;
            }
                Thread.sleep((long)remainingTime);
            nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // $$$$$ UPDATE METHOD  $$$$$

    //this update function is calling from the run method of gamePanel .
    //here we code only for update the position and situation of objects including player.
    public void update(){
        if(gameState==playState) {

            //we are going to change player position in this function
            //Player UPDATE
            player.update();

            //NPC UPDATE
            for(int i=0; i<npc[currentMap].length; i++){
                if(npc[currentMap][i] !=null){
                    npc[currentMap][i].update();
                }
            }

            //Monster UPDATE
            for(int i=0; i<monster[currentMap].length; i++){
                if(monster[currentMap][i] !=null){
                    if(monster[currentMap][i].alive==true&&monster[currentMap][i].dying==false){
                        monster[currentMap][i].update();
                    } else if (monster[currentMap][i].alive==false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i]=null;
                    }
                }
            }
            //PROJECTILE LIST  UPDATE
            for(int i=0; i<projectileList.size(); i++){
                if(projectileList.get(i) !=null){
                    if(projectileList.get(i).alive==true){
                        projectileList.get(i).update();
                    } else if (projectileList.get(i).alive==false) {
                        projectileList.remove(i);
                    }
                }
            }

            //Particle LIST  UPDATE
            for(int i=0; i<particleList.size(); i++){
                if(particleList.get(i) !=null){
                    if(particleList.get(i).alive==true){
                        particleList.get(i).update();
                    } else if (particleList.get(i).alive==false) {
                        particleList.remove(i);
                    }
                }
            }
            //Interactive TILEs  UPDATE
            for(int i=0;i<iTile[currentMap].length;i++) {
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

//        System.out.println(playerX);
//        System.out.println(playerY);
        }
        if(gameState==pauseState){
            //we will add later
        }
    }


    // $$$$$ PAINT COMPONENT METHOD

    //this paintComponent function is calling from the run method of gamePanel .
    //here we code only for draw the position and situation of objects including player.
    //and use arraylist to add all the objects and by considering the y position we will sort the objects arraylist adn then draw the objects
    public void paintComponent(Graphics g){
        //this is not custom method it is actually a built in method in java to draw things in java
        //in the Run method this function is called by repaint() method.
        //this graphics are like my pencil
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;  //Type casting the graphics for Graphics2D.


        // $$$$$ DEBUG PART START $$$$$
        long drawStart=0;
        if(keyHandler.showDebugText==true){
            drawStart=System.nanoTime();
        }
        // $$$$$ DEBUG PART END $$$$$


        //TITLE SCREEN
        if(gameState==titleState){
            //DOING our TILE Screen
            ui.draw(g2);
        }else{
            //Drawing the tile [For GAME SCREEN]
            tileM.draw(g2);
            //drawing the interactive tile
            for(int i=0;i< iTile[currentMap].length;i++){
                if(iTile[currentMap][i]!=null){
                    iTile[currentMap][i].draw(g2);
                }
            }

            // $$$$$ Entity Draw $$$$$

            //add player
            entityList.add(player);

            //add npc entity TO the list.
            for(int i=0; i<npc[currentMap].length; i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            //add object to list
            for(int i=0; i<obj[currentMap].length; i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            //add monster to list
            for(int i=0; i<monster[currentMap].length; i++){
                if(monster[currentMap][i]!=null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            //add projectile to list
            for(int i=0; i<projectileList.size(); i++){
                if(projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));
                }
            }
            //add Particle to list
            for(int i=0; i<particleList.size(); i++){
                if(particleList.get(i)!=null){
                    entityList.add(particleList.get(i));
                }
            }
            //Sorting before draw to draw in perfect layer thinking z index
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            //Finally we will draw the entity
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }

            //Empty the entity list because when this will render again the entity will added to the list again
            entityList.clear();

            //UI
            ui.draw(g2);
        }


    // $$$$$ DEBUG PART START $$$$$
        if(keyHandler.showDebugText==true){
            long drawEnd = System.nanoTime();
            long passed= drawEnd- drawStart;
            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x=10;
            int y=400;
            int lineHeight=20;
            g2.drawString("WorldX: "+ player.worldX,x,y);
            y+=lineHeight;
            g2.drawString("WorldY: "+ player.worldY,x,y);
            y+=lineHeight;
            g2.drawString("Col: "+ (player.worldX+ player.solidArea.x)/tileSize,x,y);
            y+=lineHeight;
            g2.drawString("row: "+ (player.worldY+ player.solidArea.y)/tileSize,x,y);
            y+=lineHeight;
            g2.drawString("Draw Time: "+ passed,x,y);

            //System.out.println("DrawTime: "+ passed);
        }
        // $$$$$ DEBUG PART END  $$$$$

        g2.dispose();
        //dispose() => Dispose of this graphics context and release any system resources that it is using

    }


    // $$$$$ MUSIC PART  $$$$$

    // now creating a method for playing music

    //This is for play a Music
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    //This is for stop a music
    public void stopMusic(){
        music.stop();
    }
    //This is for play Sound Effects
    public void playSE(int i){//play sound Effects
        se.setFile(i);
        se.play();
    }
}
