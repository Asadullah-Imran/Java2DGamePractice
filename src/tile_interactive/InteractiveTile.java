package tile_interactive;

import Main.GamePanel;
import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible=false;
    public InteractiveTile(GamePanel gp,int col,int row) {
        super(gp);
        this.gp = gp;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        return isCorrectItem;

    }
    public void playSE(){

    }
    public InteractiveTile getDestryoedForm(){
        InteractiveTile tile=null;
        return tile;
    }
    public void update(){
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>20){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2){
        int screenX= worldX-gp.player.worldX + gp.player.screenX;
        int screenY= worldY-gp.player.worldY + gp.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY- gp.tileSize<gp.player.worldY+gp.player.screenY
        ){
            g2.drawImage(down1,screenX,screenY,null);
        }

    }
}
