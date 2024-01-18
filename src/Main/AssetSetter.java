package Main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;

//part 7 Object Placement part starts
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp ){
        this.gp = gp;
    }
    public void setObject(){
        int i=0;
        gp.obj[0]=new OBJ_Key(gp);
        gp.obj[0].worldX=gp.tileSize*21;
        gp.obj[0].worldY=gp.tileSize*22;
        i++;
        gp.obj[1]=new OBJ_Key(gp);
        gp.obj[1].worldX=gp.tileSize*23;
        gp.obj[1].worldY=gp.tileSize*25;
        i++;
        gp.obj[i]=new OBJ_Key(gp);
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
}
