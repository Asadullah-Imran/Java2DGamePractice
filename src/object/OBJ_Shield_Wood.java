package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        type=type_shield;
        name="Wood Sheild";
        down1=setup("/objects/shield_wood.png",gp.tileSize,gp.tileSize);
        defenseValue=1;
        description="["+name+"]\nMade by Wood";
    }
}
