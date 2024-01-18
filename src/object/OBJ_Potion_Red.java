package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity {
    int value =5;
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type=type_consumable;
        name="Red Potion";
        down1=setup("/objects/potion_red.png",gp.tileSize,gp.tileSize);
        description="["+name+"]\nHeals your life by "+value+".";
    }
    public void use(Entity entity){
        gp.gameState=gp.dialogueState;
        gp.ui.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
        entity.life+=value;
        if(gp.player.life>gp.player.maxLife){
            gp.player.life=gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
