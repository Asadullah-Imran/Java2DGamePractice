package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject{
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        this.gp = gp;
        name="Boots";
        //adding exception handling for image load
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {e.printStackTrace();}
    }
}
