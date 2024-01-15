package Main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0; //to set timer so that the message will be disappear after some moment
    public boolean gameFinished=false; // if game is finished then the message will be shown
    public String currentDialogue=""; //for setting the dialogue
    public int commandNum=0; // this is for showing our menu specific commands

    //CONSTRUCTOR START

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
 //       OBJ_Key key = new OBJ_Key(gp);
   //     keyImage =key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        //we did this because we need to use this g2 in other methods also
        this.g2=g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        //TITLE STATE
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //Do PlayState stuff
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            //Do pauseState stuff
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }
    public void drawTitleScreen(){
        //to color Background [THough default is black but by chance if we want to change the background color]
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text= "Blue Boy Adventure";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x=gp.screenWidth/2-(gp.tileSize);
        y+=gp.tileSize*2;

        g2.drawImage(gp.player.down1, x, y,gp.tileSize*2, gp.tileSize*2,null);

        //MENU
        //changing font for menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text="New Game";
        x= getXforCenterText(text);
        y+=gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text="Load Game";
        x= getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text="Quit Game";
        x= getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x=getXforCenterText(text);
        int y =gp.screenHeight/2 ;
        //now drawing
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //  CREATING A DIALOGUE WINDOW
        //set parameter for window
        int x=gp.tileSize*2;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x+= gp.tileSize;
        y+= gp.tileSize;

        //to create new line
        for(String line: currentDialogue.split("\n")){
        g2.drawString(line,x,y);
        y+=40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c= new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }


    //creating a method to get the middle position of x co ordinate as dependign the text
    public int getXforCenterText(String text){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.screenWidth/2-length/2;
    }
}
