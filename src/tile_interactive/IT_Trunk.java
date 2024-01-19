package tile_interactive;

import Main.GamePanel;

public class IT_Trunk extends InteractiveTile{
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp=gp;
        this.worldX=gp.tileSize*col;
        this.worldY=gp.tileSize*row;
        down1=setup("/tiles_interactive/trunk.png",gp.tileSize,gp.tileSize);
        //set no solid area for set no collision
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=0;
        solidArea.height=0;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
}
