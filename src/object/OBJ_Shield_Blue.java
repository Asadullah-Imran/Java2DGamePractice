package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Blue extends Entity {
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        type=type_shield;
        name="Blue Sheild";
        down1=setup("/objects/shield_blue.png",gp.tileSize,gp.tileSize);
        defenseValue=2;
        description="["+name+"]\nA Shiny blue shield";
    }
}
