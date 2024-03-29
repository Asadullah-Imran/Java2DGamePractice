package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_ManaCrystal extends Entity {
    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type= type_pickupOnly;
        name="Mana Crystal";
        value=1;
        down1 = setup("/objects/manacrystal_full.png",gp.tileSize,gp.tileSize);
        image = setup("/objects/manacrystal_full.png",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/manacrystal_blank.png",gp.tileSize,gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSE(2);
        gp.ui.addMessage("Mana + "+value);
        entity.mana+=value;
    }
}
