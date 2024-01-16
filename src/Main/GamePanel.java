package Main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable  { //so my Game panel is inherited of JPanel
    //Screen settings
    //Now i am doing some screen settings
    public final int originalTitleSize=16; //we will create our every characfter or anything using 16x16 pixel it is said original tiles size because we will scale it  , look out the next variable
    public final int scale=3; //now it will (originalTitleSize x scale) so i will get 48x48 pixel character so that in my monitor my character will look bigger
    public final int tileSize =originalTitleSize*scale; // now it will show the tiles size to the screen

    //now screen size
    public final int maxScreenCol=16; //here will be 16 column of titles
    public final int maxScreenRow=12; //here will be 12 row of titles

    public final int screenWidth= tileSize*maxScreenCol; //768 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //576 pixel height


    //World Setting
    public final int maxWorldRow=50;
    public final int maxWorldCol=50;
    public final int worldWidth=tileSize* maxScreenCol;
    public final int worldHeight=tileSize* maxScreenRow;


        //FPS
    int FPS=60;

        //System
    TileManager tileM= new TileManager(this);

    //instantiate KeyHandler
    public KeyHandler keyHandler= new KeyHandler(this);

    //part 9 Sound part starts
    Sound music = new Sound();
    Sound se = new Sound();
    //part 9 Sound part ends



    //>so to do this animation in our game we need to create a time in our game we need to start this game clock.
    // and to do that we will use a class called Thread and i will mane this gameThread.

    Thread gameThread;
    //part 6 collision part starts
    public CollisionChecker cChecker= new CollisionChecker(this);
    //part 6 collision part ends

    public EventHandler eHandler= new EventHandler(this);


    //Adding Player class


    //part 7 Object Placement part starts
    public AssetSetter aSetter = new AssetSetter(this);

            //Entity and Object

    //part 10 UI part starts
    public UI ui = new UI(this);
    //part 10 UI part ends
    //
    public Player player= new Player(this,keyHandler);
    public Entity obj[]= new Entity[10]; //set the number of objects 10;

    public Entity npc[]= new Entity[10]; //set the number of 10

    //Now creating the arrayList
    ArrayList<Entity> entityList = new ArrayList<>();

    //part 7 Object Placement part ends

    // GAME STATE
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;


    //Now i am creating constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // setFocusable(true) => with this GamePanel can be "focused " to recieve key input
    }

    //part 7 Object Placement part starts
    public void setupGame(){
    aSetter.setObject();
    aSetter.setNPC();
    //playMusic(0); // 0 -> because we want to play first index of the music
    //stating the game
    gameState=titleState;

    }
    //part 7 Object Placement part ends

    //to create a gameThread start function
    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
    //In this run method, we will create a game loop which will be the core of our game;


        double drawInterval= 1000000000/FPS; //1 billion nano second //0.016666 seconds
        double nextDrawTime= System.nanoTime()+drawInterval;

        while (gameThread !=null){ //this means, as long as this gameThread exists , it repeats the process that is writen inside of the these brackets
            //System.out.println("Game loop is running");
            // in this loop we will do two things here

            //long currentTime= System.nanoTime(); //System.nanoTime()=> returns the current value of the running java virtual machine's high-resolution time source in nano secondss



            // 1 UPDATE: update information such as characterposition
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

    //the update function for run thread
    public void update(){
        if(gameState==playState) {
            //we are going to change player position in this function
            //Player
            player.update();

            //NPC
            for(int i=0; i<npc.length; i++){
                if(npc[i] !=null){
                    npc[i].update();
                }
            }

//        System.out.println(playerX);
//        System.out.println(playerY);
        }
        if(gameState==pauseState){
            //we will add later
        }
    }
    //the draw function for run thread
    public void paintComponent(Graphics g){ //this is not custom method it is actually a built in method in java to draw things in java
        //this graphics are like my pencil
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;

        //TITLE SCREEN
        if(gameState==titleState){
            //DOING our TILE Screen
            ui.draw(g2);
        }else{
            //Drawing the tile [For GAME SCREEN]
            tileM.draw(g2);

            //Entity Draw
            //add player
            entityList.add(player);
            //add npc entity
            for(int i=0; i<npc.length; i++){
                if(npc[i]!=null){
                    entityList.add(npc[i]);
                }
            }
            //add object to list
            for(int i=0; i<obj.length; i++){
                if(obj[i]!=null){
                    entityList.add(obj[i]);
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
            //Finally we will draw the entity]
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }

            //Empty the entity list because when this will render again the entity will added to the list again
            entityList.clear();

            //UI
            ui.draw(g2);
            g2.dispose();
            //dispose() => Dispose of this graphics context and release any system resources that it is using

        }




    }

    // part 9 sound part start
    // now creating a method for playing music
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){//play sound Effects
        se.setFile(i);
        se.play();
    }
    // part 9 sound part ends
}
