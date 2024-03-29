package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){

        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name="Heart";
        value=2;

        down1 = setup("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image = setup("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/heart_half.png",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/heart_blank.png",gp.tileSize,gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSE(2);
        gp.ui.addMessage("Life + "+value);
        entity.life+=value;
    }
}
