package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
//    public int x,y;
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,down1, down2,left1,left2,right1,right2;
    public String direction;
    public int spriteCounter=0;
    public  int spriteNum=1;

    //part 6 collision
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    //part 8 Object Interaction part starts
    public int solidAreaDefaultX,solidAreaDefaultY;
    //part 8 Object Interaction part  ends
    public boolean collisionOn= false;
    //to move npc good way
    public int actionLookCounter=0;
    String dialogue[]=new String[20];
    int dialogueIndex=0;
    GamePanel gp;
    public Entity (GamePanel gp){
        this.gp= gp;
    }

    public void speak(){
        if(dialogue[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.ui.currentDialogue=dialogue[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up":
                direction="down";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
            case "down":
                direction="up";
                break;
        }
    }
    //create two method for running our NPC
    public void setAction(){}
    public void update(){
        setAction();
        collisionOn=false;
        //CHeching part
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false); //its not player so its remain false
        gp.cChecker.checkPlayer(this);

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


    //Scaling Player Image
    //as it has no array like tile class so it will be different than tile scaling
    //This function is doing for not doing set its height and width all the time when the game loop but just the position of x and y
    public BufferedImage setup(String imagePath){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image= uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    //creating Draw Method
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX= worldX-gp.player.worldX + gp.player.screenX;
        int screenY= worldY-gp.player.worldY + gp.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY- gp.tileSize<gp.player.worldY+gp.player.screenY
        ){
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
            g2.drawImage(image,screenX,screenY,null);
        }
    }



}
