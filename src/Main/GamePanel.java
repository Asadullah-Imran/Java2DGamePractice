package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable  { //so my Game panel is inherited of JPanel
    //Screen settings
    //Now i am doing some screen settings
    final int originalTitleSize=16; //we will create our every characfter or anything using 16x16 pixel it is said original tile size because we will scale it  , look out the next variable
    final int scale=3; //now it will (originalTitleSize x scale) so i will get 48x48 pixel character so that in my monitor my character will look bigger
    public final int tileSize =originalTitleSize*scale; // now it will show the tile size to the screen

    //now screen size
    final int maxScreenCol=16; //here will be 16 column of titles
    final int maxScreenRow=12; //here will be 12 row of titles

    final int screenWidth= tileSize*maxScreenCol; //768 pixel width
    final int screenHeight= tileSize*maxScreenRow; //576 pixel height

    //FPS
    int FPS=60;

    //instantiate KeyHandler
    KeyHandler keyHandler= new KeyHandler();


    //>so to do this animation in our game we need to create a time in our game we need to start this game clock.
    // and to do that we will use a class called Thread and i will mane this gameThread.

    Thread gameThread;

    //Adding Player class
    Player player= new Player(this,keyHandler);

    //Set Player default Position
    int playerX=100;
    int playerY=100;
    int playerSpeed=4;


    //Now i am creating constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // setFocusable(true) => with this GamePanel can be "focused " to recieve key input
    }


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
        //we are going to change player position in this function

        player.update();

    }
    //the draw function for run thread
    public void paintComponent(Graphics g){ //this is not custom method it is actually a built in method in java to draw things in java
        //this graphics are like my pencil
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
        //dispose() => Dispose of this graphics context and release any system resources that it is using

    }
}
