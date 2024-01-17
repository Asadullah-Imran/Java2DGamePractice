package monster;

import Main.GamePanel;
import entity.Entity;

import java.util.Random;

public class MON_GreenSlime extends Entity {
GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Green Slime";
        speed= 1;
        type= 2;
        maxLife=4;
        life=maxLife;
        attack=2;
        defense=0;
        exp=2;


        //set the SOLID AREA for  collision part
        solidArea.x=3;
        solidArea.y=18;
        solidArea.width=42;
        solidArea.height=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();
    }

    public void getImage(){
        up1=setup( "/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        up2= setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        down1= setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        down2= setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        left1=setup ("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        left2= setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        right1= setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        right2= setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter==100){//for two seconds it means
            Random random= new Random();
            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=25){
                direction="up";
            }
            if(i>25&&i<=50){
                direction="down";
            }
            if(i>50&&i<=75){
                direction="left";
            }
            if(i>75&&i<=100){
                direction="right";
            }
            actionLookCounter=0;
        }
    }
    public void damageReaction(){
        actionLookCounter=0;
        direction=gp.player.direction;

//        if(actionLookCounter<50){
//            speed=4;
//        }else{
//            speed=1;
//        }
    }
}
