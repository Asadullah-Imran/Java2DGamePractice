package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Entity {
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        super(gp);
        name="Normal Sword";
        down1=setup("/objects/boots.png",gp.tileSize,gp.tileSize);
    }
}
