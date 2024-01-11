package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//created in part7 for object placement
public class SuperObject {
    //adding basic properties
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    //part 8 Object Interaction part starts
    public Rectangle solidArea= new Rectangle(0,0,48,48); //because we need to make full solid in object not specific area like player class
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    //part 8 Object Interaction part ends

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX= worldX-gp.player.worldX + gp.player.screenX;
        int screenY= worldY-gp.player.worldY + gp.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY- gp.tileSize<gp.player.worldY+gp.player.screenY
        ){

            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }

}
