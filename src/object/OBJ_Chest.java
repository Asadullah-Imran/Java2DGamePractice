package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        this.gp=gp;
        name="Chest";
        //adding exception handling for image load
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch (IOException e)
        {e.printStackTrace();}
    }
}
