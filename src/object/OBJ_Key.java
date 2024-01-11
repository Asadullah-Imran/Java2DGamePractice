package object;
//created in part7 for object placement
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name="Key";
        //adding exception handling for image load
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch (IOException e)
        {e.printStackTrace();}
    }
}
