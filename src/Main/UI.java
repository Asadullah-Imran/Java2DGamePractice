package Main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
//    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0; //to set timer so that the message will be disappear after some moment
    public boolean gameFinished=false; // if game is finished then the message will be shown
    //setting for timer
    double playTime;
    //format the time so that i can show how much decimal number i want to show
    DecimalFormat dFormat= new DecimalFormat("#0.00");
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

        if(gp.gameState == gp.playState){
            //Do PlayState stuff
        }
        if(gp.gameState == gp.pauseState){
            //Do pauseState stuff
            drawPauseScreen();
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
    //creating a method to get the middle position of x co ordinate as dependign the text
    public int getXforCenterText(String text){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.screenWidth/2-length/2;
    }
}
