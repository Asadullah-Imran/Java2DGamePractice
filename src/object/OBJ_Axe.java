package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp) {
        super(gp);
        name="Woodcutter's Axe";
        down1=setup("/objects/axe.png",gp.tileSize,gp.tileSize);
        attackValue=2;
        attackArea.width=30;
        attackArea.height=30;
        description="["+name+"]\nA bit rusty but still can \ncut some trees";
    }
}
