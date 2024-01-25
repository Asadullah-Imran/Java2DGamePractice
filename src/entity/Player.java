package entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends
    public boolean attackCanceled=false;
    //INVENTORY
    public ArrayList<Entity> inventory= new ArrayList<>();
    public final int maxInventorySize=20;

    //Constructor starts
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



        //PLAYER ATTACK Area
//        attackArea.width=36;
//        attackArea.height=36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttkackImage();
        setItems();
    }
    public void setDefaultValues(){
//        worldX= gp.tileSize*23;
//        worldY= gp.tileSize*21;
        worldX= gp.tileSize*59;
        worldY= gp.tileSize*85;
        speed=4;
        direction="down";

        //Player Status;
        level=1;
        maxLife=6;
        life=maxLife;
        maxMana=4;
        mana=maxMana;
        //ammo=10;
        strength=1; //the more strength he has the more damage he gives
        dexterity=1;// the more dexterity he has the less damage he receives
        exp=0;
        nextLevelExp=5;
        coin=0;
        //currentWeapon=new OBJ_Sword_normal(gp);
        currentWeapon=new OBJ_Axe(gp);
        currentShield= new OBJ_Shield_Wood(gp);
        projectile= new OBJ_Fireball(gp);
        //this is for testing that you can use anything to throw .
        //projectile= new OBJ_Rock(gp);
        attack=getAttack(); //the total attack value is decided by the strength and weapon
        defense=getDefense();// the total defense value is decided by the dexterity and shield
    }

    public void setDefaultPositions(){
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        direction="down";
    }
    public void restoreLifeAndMana(){
        life=maxLife;
        mana=maxMana;
        invincible=false;
    }
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }
    public int getAttack() {
        attackArea=currentWeapon.attackArea;
        return attack=strength*currentWeapon.attackValue;
    }
    public int getDefense() {
        return defense=dexterity*currentShield.defenseValue;
    }
    public void getPlayerImage(){

            up1=setup( "/player/boy_up_1.png",gp.tileSize,gp.tileSize);
            up2= setup("/player/boy_up_2.png",gp.tileSize,gp.tileSize);
            down1= setup("/player/boy_down_1.png",gp.tileSize,gp.tileSize);
            down2= setup("/player/boy_down_2.png",gp.tileSize,gp.tileSize);
            left1=setup ("/player/boy_left_1.png",gp.tileSize,gp.tileSize);
            left2= setup("/player/boy_left_2.png",gp.tileSize,gp.tileSize);
            right1= setup("/player/boy_right_1.png",gp.tileSize,gp.tileSize);
            right2= setup("/player/boy_right_2.png",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttkackImage(){
        if(currentWeapon.type==type_sword){

        attackUp1=setup( "/player/boy_attack_up_1.png",gp.tileSize,gp.tileSize*2);
        attackUp2= setup("/player/boy_attack_up_2.png",gp.tileSize,gp.tileSize*2);
        attackDown1= setup("/player/boy_attack_down_1.png",gp.tileSize,gp.tileSize*2);
        attackDown2= setup("/player/boy_attack_down_2.png",gp.tileSize,gp.tileSize*2);
        attackLeft1=setup ("/player/boy_attack_left_1.png",gp.tileSize*2,gp.tileSize);
        attackLeft2= setup("/player/boy_attack_left_2.png",gp.tileSize*2,gp.tileSize);
        attackRight1= setup("/player/boy_attack_right_1.png",gp.tileSize*2,gp.tileSize);
        attackRight2= setup("/player/boy_attack_right_2.png",gp.tileSize*2,gp.tileSize);
        } else if(currentWeapon.type==type_axe){
            attackUp1=setup( "/player/boy_axe_up_1.png",gp.tileSize,gp.tileSize*2);
            attackUp2= setup("/player/boy_axe_up_2.png",gp.tileSize,gp.tileSize*2);
            attackDown1= setup("/player/boy_axe_down_1.png",gp.tileSize,gp.tileSize*2);
            attackDown2= setup("/player/boy_axe_down_2.png",gp.tileSize,gp.tileSize*2);
            attackLeft1=setup ("/player/boy_axe_left_1.png",gp.tileSize*2,gp.tileSize);
            attackLeft2= setup("/player/boy_axe_left_2.png",gp.tileSize*2,gp.tileSize);
            attackRight1= setup("/player/boy_axe_right_1.png",gp.tileSize*2,gp.tileSize);
            attackRight2= setup("/player/boy_axe_right_2.png",gp.tileSize*2,gp.tileSize);
        }
    }

    public void update(){

        //Code for attacking part
        if(attacking==true){
            //Attacking code
            attacking();
        } //else ..
        //we are going to change player position in this function
        else if(keyHandler.upPressed==true||keyHandler.downPressed==true||keyHandler.leftPressed==true||keyHandler.rightPressed==true|| keyHandler.enterPressed==true) {

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

            //CHECK MONSTER COLLSIION
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);

            //CHECK Interactive Tile COLLSIION
            int iIileIndex=gp.cChecker.checkEntity(this,gp.iTile);


            //CHECK EVENT
            gp.eHandler.checkEvent();






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

            if(keyHandler.enterPressed==true && attackCanceled==false){
                gp.playSE(7);
                attacking=true;
                spriteCounter=0;
            }
            attackCanceled=false;
            gp.keyHandler.enterPressed=false;

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
        ///Outside of if statement

        if(keyHandler.shotKeyPressed==true && projectile.alive==false
                && shotAvailableCounter==30 &&projectile.haveResource(this)==true) { //you can not shoot a projectile if the previous projectile is alive so you can shot only one at a time
            //Set Default Coordinates , direction and user
            projectile.set(worldX, worldY, direction,true,this);

            //Subtract the MANA , Ammo, etc you want
            projectile.subtractResource(this);
            //ADD IT TO THE arraylist
            gp.projectileList.add(projectile);
            gp.playSE(10);
            shotAvailableCounter=0;
        }

        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }
        if(life>maxLife){
            life=maxLife;
        }
        if(mana>maxMana){
            mana=maxMana;
        }
        if(life<=0){
            gp.gameState=gp.gameOverState;
            gp.ui.commandNum=-1;
            gp.stopMusic();
            gp.playSE(12);
        }


        //checking the player position
//        System.out.println("x: "+ worldX/gp.tileSize);
//       System.out.println("y: "+ worldY/gp.tileSize);

    }


    public void attacking(){
        spriteCounter++;
        if(spriteCounter<=5){
            spriteNum=1;
        }if(spriteCounter>5 && spriteCounter<=25){
            spriteNum=2;
            //SAVE THE CURRENT WORLDX WORLDY AND SOLIDAREA
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;

            //ADJUST PLAYERS worldX/Y for the attack area
            switch (direction){
                case "up": worldY-=attackArea.height;break;
                case "down": worldY+=attackArea.height;break;
                case "left": worldX-=attackArea.width;break;
                case "right": worldX+=attackArea.width;break;
            }
            //ATTACK AREa becomes solid Area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //CHECK MONSTER COLLISION with the updated worldx and worldy and solidArea
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex,attack);

            //INTERACTIVE TILE
            int iTileIndex=gp.cChecker.checkEntity(this,gp.iTile);
            damageInteractiveTile(iTileIndex);

            //AFTER checking the attack restore the worldx and worldy and solidArea
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }if(spriteCounter>25){
            spriteNum=1;
            spriteCounter=0;
            attacking=false;
        }
    }

    //part 8 Object Interaction part starts
    public void pickUpObject(int i){
        //if index is 999 that means it didn't touch any objects
        // if index is not 999 that means it touched any objects'
        if(i!=999){

            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][i].type==type_pickupOnly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i]=null;

            }
            //INVENTORY ITEMS
            else{
                String text;
                if(inventory.size()!=maxInventorySize){
                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);
                    text="Got a "+ gp.obj[gp.currentMap][i].name+" !";
                }else {
                    text="You can not carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i]=null;

            }

        }
    }

    public void interactNPC(int i){
        if(gp.keyHandler.enterPressed==true){
            if(i!=999){
                attackCanceled=true;
                gp.gameState=gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
//            else {
//                gp.playSE(7);
//                attacking=true;
//            }
        }
    }

    public void contactMonster(int i){
        if(i!=999){
            if(invincible==false && gp.monster[gp.currentMap][i].dying==false) {
                gp.playSE(6);
                int damage=gp.monster[gp.currentMap][i].attack-defense;
                if(damage<0){
                    damage=0;
                }
                life -= damage;
                invincible=true;
            }
        }
    }
    public void damageMonster(int i,int attack){
        if(i!=999){
            if(gp.monster[gp.currentMap][i].invincible==false) {
                gp.playSE(5);
                int damage=attack-gp.monster[gp.currentMap][i].defense;
                if(damage<0){
                    damage=0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage+ " damage");

                gp.monster[gp.currentMap][i].invincible=true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life<=0){
                    gp.monster[gp.currentMap][i].dying=true;
                    gp.ui.addMessage("Killed the "+ gp.monster[gp.currentMap][i].name+"!");
                    gp.ui.addMessage("Exp + "+ gp.monster[gp.currentMap][i].exp);
                    exp+=gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }

    }

    public void damageInteractiveTile(int i){
        if(i!=999 && gp.iTile[gp.currentMap][i].destructible==true&& gp.iTile[gp.currentMap][i].isCorrectItem(this)==true
        && gp.iTile[gp.currentMap][i].invincible==false){

            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible=true; // other wise with one hit it will take life--; in multipletime so that tree cut off immediately with one shot
            //CREATING PARTICLE
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
            if(gp.iTile[gp.currentMap][i].life==0){
                gp.iTile[gp.currentMap][i]=gp.iTile[gp.currentMap][i].getDestryoedForm();
            }

        }
    }

    public void checkLevelUp(){
        if(exp>=nextLevelExp){
            level++;
            nextLevelExp=nextLevelExp*2;
            maxLife+=2;
            strength++;
            attack=getAttack();
            defense=getDefense();
            gp.playSE(8);
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue="You are level "+level+" now\n you feel stronger!";
        }
    }



    public void selectItem(){
        int itemIndex=gp.ui.getItemIndexOnSlot();
        if(itemIndex<inventory.size()){
            Entity selectedItem=inventory.get(itemIndex);
            if(selectedItem.type==type_sword|| selectedItem.type==type_axe){
                currentWeapon=selectedItem;
                //update the attack method with proper power
                attack=getAttack();
                getPlayerAttkackImage();
            }
            if(selectedItem.type==type_shield){
                currentShield=selectedItem;
                //update the defense method with proper defense power
                defense=getDefense();
            }
            if(selectedItem.type==type_consumable){
                //WE are gonna use this later
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }

    }
    public  void draw( Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image= null;

        //temp variable
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        switch (direction){
            case"up":
                if(attacking==false){
                    if(spriteNum==1){image= up1;}
                    if(spriteNum==2){image=up2;}
                }else if(attacking==true){
                    tempScreenY=screenY-gp.tileSize;
                    if(spriteNum==1){image= attackUp1;}
                    if(spriteNum==2){image=attackUp2;}
                }
                break;
            case"down":
                if(attacking==false){
                    if(spriteNum==1){image= down1;}
                    if(spriteNum==2){image=down2;}
                }else if(attacking==true){
                    if(spriteNum==1){image= attackDown1;}
                    if(spriteNum==2){image=attackDown2;}
                }

                break;
            case"left":
                if(attacking==false){
                    if(spriteNum==1){image= left1;}
                    if(spriteNum==2){image=left2;}
                }else if(attacking==true){
                    tempScreenX=screenX-gp.tileSize;
                    if(spriteNum==1){image= attackLeft1;}
                    if(spriteNum==2){image=attackLeft2;}
                }

                break;
            case"right":
                if(attacking==false){
                    if(spriteNum==1){image= right1;}
                    if(spriteNum==2){image=right2;}
                }else if(attacking==true){
                    if(spriteNum==1){image= attackRight1;}
                    if(spriteNum==2){image=attackRight2;}
                }

                break;
        }
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4F));
        }

        g2.drawImage(image,tempScreenX,tempScreenY,null); //drawImage();  draw image in canvas
        //Reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));



    }
}
