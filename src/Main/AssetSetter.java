package Main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

//This class is made for setting Asset to the Game map.
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp ){
        this.gp = gp;
    }

    //HERE IS SOME METHOD OF
    //setObject [key, shield, weapon, etc]
    //setNPC
    //setMonster
    //setInteractiveTile [dry tree, breakable tree, trunk.]
    public void setObject(){
        int mapNum=0; //For map 1  //to create object for map 2 then we need to ser mapNum 2 under the code.
        int i=0;
        gp.obj[mapNum][i]=new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*76;
        gp.obj[mapNum][i].worldY=gp.tileSize*90;
        i++;
        gp.obj[mapNum][i]=new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*81;
        gp.obj[mapNum][i].worldY=gp.tileSize*48;
        i++;
        gp.obj[mapNum][i]=new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*80;
        gp.obj[mapNum][i].worldY=gp.tileSize*83;
        i++;
        gp.obj[mapNum][i]=new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*69;
        gp.obj[mapNum][i].worldY=gp.tileSize*55;
        i++;
        gp.obj[mapNum][i]=new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*75;
        gp.obj[mapNum][i].worldY=gp.tileSize*86;
        i++;
        gp.obj[mapNum][i]=new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*79;
        gp.obj[mapNum][i].worldY=gp.tileSize*64;
        i++;
        gp.obj[mapNum][i]=new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*72;
        gp.obj[mapNum][i].worldY=gp.tileSize*82;
        i++;
        gp.obj[mapNum][i]=new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*71;
        gp.obj[mapNum][i].worldY=gp.tileSize*69;
        i++;
        gp.obj[mapNum][i]=new OBJ_Shield_Wood(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*77;
        gp.obj[mapNum][i].worldY=gp.tileSize*80;
        i++;
        gp.obj[mapNum][i]=new OBJ_Sword_normal(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*60;
        gp.obj[mapNum][i].worldY=gp.tileSize*89;
        i++;
        gp.obj[mapNum][i]=new  OBJ_Coin_Bronze(gp);

        i++;
        gp.obj[mapNum][i]=new OBJ_Axe(gp);

        i++;
        gp.obj[mapNum][i]=new OBJ_Shield_Blue(gp);

        i++;
        gp.obj[mapNum][i]=new OBJ_Potion_Red(gp);

        i++;
        gp.obj[mapNum][i]=new OBJ_Heart(gp);

        i++;


    }
    public void setNPC(){
        int mapNum=0;
        int i=0;
        gp.npc[mapNum][i]= new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX=gp.tileSize*60;
        gp.npc[mapNum][i].worldY=gp.tileSize*86;

        i++;

        //New Map;
        mapNum=1;
        i=0;
        gp.npc[mapNum][i]= new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX=gp.tileSize*59;
        gp.npc[mapNum][i].worldY=gp.tileSize*86;
        i++;

    }
    public void setMonster(){
        int mapNum=0;
        int i=0;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*65;
        gp.monster[mapNum][i].worldY=gp.tileSize*90;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*68;
        gp.monster[mapNum][i].worldY=gp.tileSize*86;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*71;
        gp.monster[mapNum][i].worldY=gp.tileSize*89;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*80;
        gp.monster[mapNum][i].worldY=gp.tileSize*87;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*76;
        gp.monster[mapNum][i].worldY=gp.tileSize*77;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*72;
        gp.monster[mapNum][i].worldY=gp.tileSize*75;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*80;
        gp.monster[mapNum][i].worldY=gp.tileSize*71;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*74;
        gp.monster[mapNum][i].worldY=gp.tileSize*68;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*71;
        gp.monster[mapNum][i].worldY=gp.tileSize*59;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*78;
        gp.monster[mapNum][i].worldY=gp.tileSize*53;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX=gp.tileSize*75;
        gp.monster[mapNum][i].worldY=gp.tileSize*49;

    }
    //For Interactive Tile
    public void setInteractiveTile(){
        int mapNum=0;
        int i=0;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,79,83);i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,81,83);i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,69,54);i++;
        gp.iTile[mapNum][i]=new IT_DryTree(gp,69,56);i++;
    }
}
