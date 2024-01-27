package entity;

import Main.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp) {
        super(gp);
        direction ="down";
        speed=2;
        //set the collision part
        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getNPCImage();
        setDialogue();
        setItems();
    }

    public void getNPCImage(){

        up1=setup( "/npc/merchant_down_1.png",gp.tileSize,gp.tileSize);
        up2= setup("/npc/merchant_down_2.png",gp.tileSize,gp.tileSize);
        down1= setup("/npc/merchant_down_1.png",gp.tileSize,gp.tileSize);
        down2= setup("/npc/merchant_down_2.png",gp.tileSize,gp.tileSize);
        left1=setup ("/npc/merchant_down_1.png",gp.tileSize,gp.tileSize);
        left2= setup("/npc/merchant_down_2.png",gp.tileSize,gp.tileSize);
        right1= setup("/npc/merchant_down_1.png",gp.tileSize,gp.tileSize);
        right2= setup("/npc/merchant_down_2.png",gp.tileSize,gp.tileSize);
    }

    //set dialogue
    public void setDialogue(){
        dialogue[0]="He he, So you found me!\nI have some good stuff.\nDo you want to trade?";
    }
    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
    }
    public void speak(){
        super.speak();
        gp.gameState=gp.tradeState;
        gp.ui.npc=this;
    }
}
