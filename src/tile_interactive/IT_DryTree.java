package tile_interactive;

import Main.GamePanel;
import entity.Entity;

import java.awt.*;

public class IT_DryTree extends InteractiveTile{
    GamePanel gp;
    public IT_DryTree(GamePanel gp,int col,int row) {
        super(gp,col,row);
        this.gp=gp;
        life=3;
        this.worldX=gp.tileSize*col;
        this.worldY=gp.tileSize*row;
        down1=setup("/tiles_interactive/drytree.png",gp.tileSize,gp.tileSize);
        destructible=true;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        if(entity.currentWeapon.type==type_axe){
            isCorrectItem=true;
        }
        return isCorrectItem;

    }
    public void playSE(){
        gp.playSE(11);
    }
    public InteractiveTile getDestryoedForm(){
        InteractiveTile tile= new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=6;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=1;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=20;
        return maxLife;
         }

}
