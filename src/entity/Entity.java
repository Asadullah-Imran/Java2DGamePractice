package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
//    public int x,y;
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,down1, down2,left1,left2,right1,right2;
    public BufferedImage attackUp1,attackUp2,attackDown1, attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public String direction="down";
    public int spriteCounter=0;
    public  int spriteNum=1;

    //part 6 collision
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    //part 8 Object Interaction part starts
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;
    //part 8 Object Interaction part  ends
    public boolean collisionOn= false;
    //to move npc good way
    public int actionLookCounter=0;
    String dialogue[]=new String[20];
    int dialogueIndex=0;

    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public  int ammo;

    public boolean invincible=false;
    public int invincibleCounter=0;
    //Character ATributes
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description="";
    public int useCost;
    public int value;

    //Type of character;
    public int type; // 0 for player, 1 for npc, 2 for monster
    public final int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_sword=3;
    public final int type_axe=4;
    public final int type_shield=5;
    public final int type_consumable=6;
    public final int type_pickupOnly=7;

    //Monster
    //Attack
    public boolean attacking= false;
    public boolean alive= true;
    public boolean dying= false;
    public boolean hpBarOn=false;

    int dyingCounter=0;
    int hpBarCounter=0;
    public int shotAvailableCounter=0;

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

    public void use(Entity entity){}


    public void checkDrop(){

    }
    public void dropItem(Entity droppedItem){
        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]==null){
                gp.obj[i]=droppedItem;
                gp.obj[i].worldX=worldX; //dead monsters worldX
                gp.obj[i].worldY=worldY; //dead monsters worldY
                break;

            }
        }
    }

    ///the method of particle
    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= null;
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=0;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=0;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target){
        //Doing it later.
        Color color=generator.getParticleColor();
        int size=generator.getParticleSize();
        int speed=generator.getParticleSpeed();
        int maxLife=generator.getParticleMaxLife();

        //creating particle
        Particle p1= new Particle(gp,generator,color,size,speed,maxLife,-1,-1 );
        Particle p2= new Particle(gp,generator,color,size,speed,maxLife,1,-1 );
        Particle p3= new Particle(gp,generator,color,size,speed,maxLife,-1,1 );
        Particle p4= new Particle(gp,generator,color,size,speed,maxLife,1,1 );
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    //create two method for running our NPC
    public void setAction(){}

    public void damageReaction(){}
    public void update(){
        setAction();
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false); //its not player so its remain false
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer= gp.cChecker.checkPlayer(this);

        if(this.type ==type_monster && contactPlayer==true){
           damagePlayer(attack);
        }


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

        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>40){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }

    }
    public void damagePlayer(int attack){
        if(gp.player.invincible==false){
            //recieve damage
            gp.playSE(6);
            int damage=attack-gp.player.defense;
            if(damage<0){
                damage=0;
            }

            gp.player.life-=damage;
            gp.player.invincible=true;
        }
    }


    //Scaling Player Image
    //as it has no array like tile class so it will be different than tile scaling
    //This function is doing for not doing set its height and width all the time when the game loop but just the position of x and y
    public BufferedImage setup(String imagePath,int width,int height){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image= uTool.scaleImage(image,width,height);
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

            //MONSTER HEALTH BAR
            if(type==type_monster && hpBarOn==true){ //type 2 for monster
                double oneScale= (double)gp.tileSize/maxLife;
                double hpPerValue=oneScale*life;

                //set background color
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY-15,(int)hpPerValue,10);
                hpBarCounter++;
                if(hpBarCounter>600){
                    hpBarCounter=0;
                    hpBarOn=false;

                }
            }



            if(invincible==true){
                hpBarOn=true;
                hpBarCounter=0;
                changeAlpha(g2,0.4F);
            }
            if(dying==true){
                dyingAnimation(g2);
            }
            g2.drawImage(image,screenX,screenY,null);

            //Reset opacity
            changeAlpha(g2,1F);
        }
    }

//DEATH EFFECT
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=5;
        if(dyingCounter<=i){
            changeAlpha(g2,0f);
        }else if(dyingCounter>i&&dyingCounter<=i*2){
            changeAlpha(g2,1f);
        }else if(dyingCounter>i*2&&dyingCounter<=i*3){
            changeAlpha(g2,0f);
        }else if(dyingCounter>i*3&&dyingCounter<=i*4){
            changeAlpha(g2,1f);
        }else if(dyingCounter>i*4&&dyingCounter<=i*5){
            changeAlpha(g2,0f);
        }else if(dyingCounter>i*5&&dyingCounter<=i*6){
            changeAlpha(g2,1f);
        }else if(dyingCounter>i*6&&dyingCounter<=i*7){
            changeAlpha(g2,0f);
        }else if(dyingCounter>i*7&&dyingCounter<=i*8){
            changeAlpha(g2,1f);
        } else if (dyingCounter>i*8) {
            alive=false;
        }
    }

    public void changeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }

}
