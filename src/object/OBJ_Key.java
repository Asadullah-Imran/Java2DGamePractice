package object;
//created in part7 for object placement
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        this.gp=gp;
        name="Key";
        //adding exception handling for image load
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch (IOException e)
        {e.printStackTrace();}
    }
}
