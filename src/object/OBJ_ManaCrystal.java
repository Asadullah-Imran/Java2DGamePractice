package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_ManaCrystal extends Entity {
    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name="Mana Crystal";
        image = setup("/objects/manacrystal_full.png",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/manacrystal_blank.png",gp.tileSize,gp.tileSize);
    }
}
