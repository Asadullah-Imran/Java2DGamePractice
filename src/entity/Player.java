package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
     GamePanel gp;
     int picSpeedController=1;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp=gp;
        this.keyHandler=keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try{
//
//            up1= ImageIO.read(getClass().getResourceAsStream("/customPlayer/up_left.png"));
//            up2= ImageIO.read(getClass().getResourceAsStream("/customPlayer/up_right.png"));
//            down1= ImageIO.read(getClass().getResourceAsStream("/customPlayer/down_left.png"));
//            down2= ImageIO.read(getClass().getResourceAsStream("/customPlayer/down_right.png"));
//            left1= ImageIO.read(getClass().getResourceAsStream("/customPlayer/left_left.png"));
//            left2= ImageIO.read(getClass().getResourceAsStream("/customPlayer/left_right.png"));
//            right1= ImageIO.read(getClass().getResourceAsStream("/customPlayer/right_right.png"));
//            right2= ImageIO.read(getClass().getResourceAsStream("/customPlayer/right_left.png"));
            up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        //we are going to change player position in this function
        if(keyHandler.upPressed==true||keyHandler.downPressed==true||keyHandler.leftPressed==true||keyHandler.rightPressed==true) {

             if (keyHandler.upPressed == true && keyHandler.rightPressed == true) {
                direction = "up";
                picSpeedController=2;
                y -= (speed-1);
                x += (speed-1);
            }else if (keyHandler.upPressed == true && keyHandler.leftPressed == true) {
                 direction = "up";
                 picSpeedController=2;
                 y -= (speed-1);
                 x -= (speed-1);
             }else if (keyHandler.downPressed == true && keyHandler.rightPressed == true) {
                direction = "down";
                picSpeedController=2;
                y += (speed-1);
                x += (speed-1);
            }else if (keyHandler.downPressed == true && keyHandler.leftPressed == true) {
                 direction = "down";
                 picSpeedController=2;
                 y += (speed-1);
                 x -= (speed-1);
             }else
             if (keyHandler.upPressed == true) {
                picSpeedController=1;
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed == true) {
                direction = "down";
                picSpeedController=1;
                y += speed;
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
                picSpeedController=1;
                x -= speed;
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
                picSpeedController=1;
                x += speed;
            }


            spriteCounter++;
            if(picSpeedController==1) {
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            } else if (picSpeedController==2) {

                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }

    }
    public  void draw( Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image= null;
        switch (direction){
            case"up":
                if(spriteNum==1){
                image= up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case"down":
                if(spriteNum==1){
                    image= down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case"left":
                if(spriteNum==1){
                    image= left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
            case"right":
                if(spriteNum==1){
                    image= right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null); //drawImage();  draw image in canvas
    }
}
