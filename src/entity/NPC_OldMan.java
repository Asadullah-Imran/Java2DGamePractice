package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp){
        super(gp);
        direction ="down";
        speed=2;
        getNPCImage();
    }
    public void getNPCImage(){

        up1=setup( "/npc/oldman_up_1.png");
        up2= setup("/npc/oldman_up_2.png");
        down1= setup("/npc/oldman_down_1.png");
        down2= setup("/npc/oldman_down_2.png");
        left1=setup ("/npc/oldman_left_1.png");
        left2= setup("/npc/oldman_left_2.png");
        right1= setup("/npc/oldman_right_1.png");
        right2= setup("/npc/oldman_right_2.png");
    }

    //this is kind of AI work
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

}
