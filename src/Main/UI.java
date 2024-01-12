package Main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0; //to set timer so that the message will be disappear after some moment
    public boolean gameFinished=false; // if game is finished then the message will be shown

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        OBJ_Key key = new OBJ_Key();
        keyImage =key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        if(gameFinished==true){
            //now i am trying to adjust the center position of the screen
            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            text="You found the treasure";
            textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

             x= gp.screenWidth/2-textLength/2;
             y= gp.screenHeight/2-(gp.tileSize*3);
             g2.drawString(text, x, y);

             g2.setFont(arial_80B);
             g2.setColor(Color.yellow);
            text="Congratulations!";
            textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x= gp.screenWidth/2-textLength/2;
            y= gp.screenHeight/2+(gp.tileSize*2);
            g2.drawString(text, x, y);

            // now stoping the thread so the the game will end
            gp.gameThread=null;
        }else {

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("Key = " + gp.player.hasKey, 74, 65); // in here y position define not as regualar way
            // but it y define the baseline of text so if you need to get the same position as regualar you should use "y+height of your text"
            //MESSAGE
            if (messageOn == true) {
                //changing font size
                g2.setFont(g2.getFont().deriveFont(30F));

                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
