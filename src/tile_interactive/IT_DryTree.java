package tile_interactive;

import Main.GamePanel;
import entity.Entity;

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
}
