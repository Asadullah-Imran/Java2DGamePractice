package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        super(gp);
        name="Door";
        down1=setup("/objects/door.png",gp.tileSize,gp.tileSize);
        collision=true;

        //set the collision part
        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
}
