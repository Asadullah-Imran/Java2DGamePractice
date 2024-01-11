package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        name="Door";
        //adding exception handling for image load
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }catch (IOException e)
        {e.printStackTrace();}
        collision=true;
    }
}
