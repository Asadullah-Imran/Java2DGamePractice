package Main;

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
        int i=0;
        gp.obj[i]=new OBJ_Key(gp);
        gp.obj[i].worldX=gp.tileSize*76;
        gp.obj[i].worldY=gp.tileSize*90;
        i++;
        gp.obj[i]=new OBJ_Key(gp);
        gp.obj[i].worldX=gp.tileSize*81;
        gp.obj[i].worldY=gp.tileSize*48;
        i++;
        gp.obj[i]=new OBJ_Door(gp);
        gp.obj[i].worldX=gp.tileSize*80;
        gp.obj[i].worldY=gp.tileSize*83;
        i++;
        gp.obj[i]=new OBJ_Door(gp);
        gp.obj[i].worldX=gp.tileSize*69;
        gp.obj[i].worldY=gp.tileSize*55;
        i++;
        gp.obj[i]=new OBJ_Heart(gp);
        gp.obj[i].worldX=gp.tileSize*75;
        gp.obj[i].worldY=gp.tileSize*86;
        i++;
        gp.obj[i]=new OBJ_Heart(gp);
        gp.obj[i].worldX=gp.tileSize*79;
        gp.obj[i].worldY=gp.tileSize*64;
        i++;
        gp.obj[i]=new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX=gp.tileSize*72;
        gp.obj[i].worldY=gp.tileSize*82;
        i++;
        gp.obj[i]=new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX=gp.tileSize*71;
        gp.obj[i].worldY=gp.tileSize*69;
        i++;
        gp.obj[i]=new OBJ_Shield_Wood(gp);
        gp.obj[i].worldX=gp.tileSize*77;
        gp.obj[i].worldY=gp.tileSize*80;
        i++;
        gp.obj[i]=new OBJ_Sword_normal(gp);
        gp.obj[i].worldX=gp.tileSize*60;
        gp.obj[i].worldY=gp.tileSize*89;
        i++;
        gp.obj[i]=new  OBJ_Coin_Bronze(gp);

        i++;
        gp.obj[i]=new OBJ_Axe(gp);

        i++;
        gp.obj[i]=new OBJ_Shield_Blue(gp);

        i++;
        gp.obj[i]=new OBJ_Potion_Red(gp);

        i++;
        gp.obj[i]=new OBJ_Heart(gp);

        i++;


    }
    public void setNPC(){
        int i=0;
        gp.npc[i]= new NPC_OldMan(gp);
        gp.npc[i].worldX=gp.tileSize*60;
        gp.npc[i].worldY=gp.tileSize*86;
        i++;

    }
    public void setMonster(){
        int i=0;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*65;
        gp.monster[i].worldY=gp.tileSize*90;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*68;
        gp.monster[i].worldY=gp.tileSize*86;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*71;
        gp.monster[i].worldY=gp.tileSize*89;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*80;
        gp.monster[i].worldY=gp.tileSize*87;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*76;
        gp.monster[i].worldY=gp.tileSize*77;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*72;
        gp.monster[i].worldY=gp.tileSize*75;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*80;
        gp.monster[i].worldY=gp.tileSize*71;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*74;
        gp.monster[i].worldY=gp.tileSize*68;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*71;
        gp.monster[i].worldY=gp.tileSize*59;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*78;
        gp.monster[i].worldY=gp.tileSize*53;
        i++;
        gp.monster[i]= new MON_GreenSlime(gp);
        gp.monster[i].worldX=gp.tileSize*75;
        gp.monster[i].worldY=gp.tileSize*49;

//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*86;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*76;
//        gp.monster[i].worldY=gp.tileSize*90;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*77;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*80;
//        gp.monster[i].worldY=gp.tileSize*73;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*77;
//        gp.monster[i].worldY=gp.tileSize*69;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*86;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*76;
//        gp.monster[i].worldY=gp.tileSize*90;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*77;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*80;
//        gp.monster[i].worldY=gp.tileSize*73;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*77;
//        gp.monster[i].worldY=gp.tileSize*69;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*86;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*76;
//        gp.monster[i].worldY=gp.tileSize*90;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*75;
//        gp.monster[i].worldY=gp.tileSize*77;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*80;
//        gp.monster[i].worldY=gp.tileSize*73;
//        i++;
//        gp.monster[i]= new MON_GreenSlime(gp);
//        gp.monster[i].worldX=gp.tileSize*77;
//        gp.monster[i].worldY=gp.tileSize*69;
    }
    //For Interactive Tile
    public void setInteractiveTile(){
        int i=0;
        gp.iTile[i]=new IT_DryTree(gp,79,83);i++;
        gp.iTile[i]=new IT_DryTree(gp,81,83);i++;
        gp.iTile[i]=new IT_DryTree(gp,69,54);i++;
        gp.iTile[i]=new IT_DryTree(gp,69,56);i++;
//        gp.iTile[i]=new IT_DryTree(gp,29,12);
//
//        i++;
//        gp.iTile[i]=new IT_DryTree(gp,30,12);
//
//        i++;
//        gp.iTile[i]=new IT_DryTree(gp,31,12);
//
//        i++;
//        gp.iTile[i]=new IT_DryTree(gp,32,12);
//
//        i++;
//        gp.iTile[i]=new IT_DryTree(gp,33,12);
//
//
//
//        i++;
//        gp.iTile[i]=new IT_DryTree(gp,30,20);i++;
//        gp.iTile[i]=new IT_DryTree(gp,30,21);i++;
//        gp.iTile[i]=new IT_DryTree(gp,30,22);i++;
//        gp.iTile[i]=new IT_DryTree(gp,20,20);i++;
//        gp.iTile[i]=new IT_DryTree(gp,20,21);i++;
//        gp.iTile[i]=new IT_DryTree(gp,20,22);i++;
//        gp.iTile[i]=new IT_DryTree(gp,22,24);i++;
//        gp.iTile[i]=new IT_DryTree(gp,23,24);i++;
//        gp.iTile[i]=new IT_DryTree(gp,24,24);
    }
}
