package Main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

//part 7 Object Placement part starts
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp ){
        this.gp = gp;
    }
    public void setObject(){
        int i=0;
        gp.obj[0]=new OBJ_Coin_Bronze(gp);
        gp.obj[0].worldX=gp.tileSize*21;
        gp.obj[0].worldY=gp.tileSize*22;
        i++;
        gp.obj[1]=new  OBJ_Coin_Bronze(gp);
        gp.obj[1].worldX=gp.tileSize*23;
        gp.obj[1].worldY=gp.tileSize*25;
        i++;
        gp.obj[i]=new  OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX=gp.tileSize*22;
        gp.obj[i].worldY=gp.tileSize*20;
        i++;
        gp.obj[i]=new OBJ_Axe(gp);
        gp.obj[i].worldX=gp.tileSize*33;
        gp.obj[i].worldY=gp.tileSize*21;
        i++;
        gp.obj[i]=new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX=gp.tileSize*35;
        gp.obj[i].worldY=gp.tileSize*21;
        i++;
        gp.obj[i]=new OBJ_Potion_Red(gp);
        gp.obj[i].worldX=gp.tileSize*22;
        gp.obj[i].worldY=gp.tileSize*27;
        i++;
        gp.obj[i]=new OBJ_Heart(gp);
        gp.obj[i].worldX=gp.tileSize*22;
        gp.obj[i].worldY=gp.tileSize*29;
        i++;
        gp.obj[i]=new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX=gp.tileSize*22;
        gp.obj[i].worldY=gp.tileSize*31;
    }
    public void setNPC(){
        gp.npc[0]= new NPC_OldMan(gp);
        gp.npc[0].worldX=gp.tileSize*21;
        gp.npc[0].worldY=gp.tileSize*21;

        gp.npc[1]= new NPC_OldMan(gp);
        gp.npc[1].worldX=gp.tileSize*11;
        gp.npc[1].worldY=gp.tileSize*21;

        gp.npc[2]= new NPC_OldMan(gp);
        gp.npc[2].worldX=gp.tileSize*31;
        gp.npc[2].worldY=gp.tileSize*21;

        gp.npc[3]= new NPC_OldMan(gp);
        gp.npc[3].worldX=gp.tileSize*21;
        gp.npc[3].worldY=gp.tileSize*24;
    }
    public void setMonster(){
        int i=0;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*23;
        gp.monster[i].worldY=gp.tileSize*36;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*23;
        gp.monster[i].worldY=gp.tileSize*37;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*24;
        gp.monster[i].worldY=gp.tileSize*37;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*23;
        gp.monster[i].worldY=gp.tileSize*42;

        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*23;
        gp.monster[i].worldY=gp.tileSize*40;

    }
    //For Interactive Tile
    public void setInteractiveTile(){
        int i=0;
        gp.iTile[i]=new IT_DryTree(gp,27,12);
        i++;
        gp.iTile[i]=new IT_DryTree(gp,28,12);
        i++;
        gp.iTile[i]=new IT_DryTree(gp,29,12);

        i++;
        gp.iTile[i]=new IT_DryTree(gp,30,12);

        i++;
        gp.iTile[i]=new IT_DryTree(gp,31,12);

        i++;
        gp.iTile[i]=new IT_DryTree(gp,32,12);

        i++;
        gp.iTile[i]=new IT_DryTree(gp,33,12);



        i++;
        gp.iTile[i]=new IT_DryTree(gp,30,20);i++;
        gp.iTile[i]=new IT_DryTree(gp,30,21);i++;
        gp.iTile[i]=new IT_DryTree(gp,30,22);i++;
        gp.iTile[i]=new IT_DryTree(gp,20,20);i++;
        gp.iTile[i]=new IT_DryTree(gp,20,21);i++;
        gp.iTile[i]=new IT_DryTree(gp,20,22);i++;
        gp.iTile[i]=new IT_DryTree(gp,22,24);i++;
        gp.iTile[i]=new IT_DryTree(gp,23,24);i++;
        gp.iTile[i]=new IT_DryTree(gp,24,24);
    }
}
