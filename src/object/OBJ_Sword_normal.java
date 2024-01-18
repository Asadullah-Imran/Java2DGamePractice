package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_normal extends Entity {
    public OBJ_Sword_normal(GamePanel gp) {
        super(gp);
        name="Boots";
        down1=setup("/objects/sword_normal.png",gp.tileSize,gp.tileSize);
        attackValue=1;
        attackArea.width=36;
        attackArea.height=36;
        description="["+name+"]\nAn old sword";
    }
}
