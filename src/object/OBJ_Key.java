package object;
//created in part7 for object placement
import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        name="Key";
        down1=setup("/objects/key.png",gp.tileSize,gp.tileSize);
        description="["+name+"]\nit opens a door";
    }
}
