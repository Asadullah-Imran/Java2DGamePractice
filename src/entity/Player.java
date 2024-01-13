package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends

    public Player(GamePanel gp, KeyHandler keyHandler){
        super(gp);
        this.keyHandler=keyHandler;
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        screenX=gp.screenWidth/2-(gp.tileSize/2);

        //part 6 collision part starts
        solidArea= new Rectangle(); //we can skip this session. (as we already make it in Entity class)
        solidArea.x=8;
        solidArea.y=16;

        //part 8 Object Interaction part starts
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        //part 8 Object Interaction part  ends

        solidArea.width=32;
        solidArea.height=32;
        //part 6 collision part ends

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX= gp.tileSize*23;
        worldY= gp.tileSize*21;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){

            up1=setup( "/player/boy_up_1.png");
            up2= setup("/player/boy_up_2.png");
            down1= setup("/player/boy_down_1.png");
            down2= setup("/player/boy_down_2.png");
            left1=setup ("/player/boy_left_1.png");
            left2= setup("/player/boy_left_2.png");
            right1= setup("/player/boy_right_1.png");
            right2= setup("/player/boy_right_2.png");
    }

    public void update(){
        //we are going to change player position in this function
        if(keyHandler.upPressed==true||keyHandler.downPressed==true||keyHandler.leftPressed==true||keyHandler.rightPressed==true) {

            if (keyHandler.upPressed == true) {
                direction = "up";

            } else if (keyHandler.downPressed == true) {
                direction = "down";

            } else if (keyHandler.leftPressed == true) {
                direction = "left";

            } else if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            //part 6 collision part starts
            //check Tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //part 8 Object Interaction part starts
            //Check Object collision
            int objIndex=gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //part 8 Object Interaction part ends

            //CHeck NPC collision
            int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);


            //if collisionOn is false then player can be able to move
            if(collisionOn==false){
                switch (direction){
                    case "up":worldY -= speed;
                        break;
                    case "down":worldY+= speed;
                        break;
                    case "left":worldX -= speed;
                        break;
                    case "right":worldX += speed;
                        break;
                }
            }

            //part 6 collision part ends


            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //checking the player position
//        System.out.println("x: "+ worldX/gp.tileSize);
//        System.out.println("y: "+ worldY/gp.tileSize);

    }
    //part 8 Object Interaction part starts
    public void pickUpObject(int i){
        //if index is 999 that means it didn't touch any objects
        // if index is not 999 that means it touched any objects'
        if(i!=999){

        }
    }

    public void interactNPC(int i){
        if(i!=999){
            gp.gameState=gp.dialogueState;
        }
    }


    //part 8 Object Interaction part ends
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
        g2.drawImage(image,screenX,screenY,null); //drawImage();  draw image in canvas
    }
}
