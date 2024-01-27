package Main;

import entity.Entity;

public class EventHandler {
    GamePanel gp;
    int previousEventX, previousEventY;
    boolean canTouchEvent=true;
    int tempMap,tempCol,tempRow;
    EventRect eventRect[][][];
    public EventHandler(GamePanel gp){
        this.gp = gp;

        //initialize the array of event Rect
        eventRect=new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map=0;

        int col=0;
        int row=0;
        while(map<gp.maxMap&&col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[map][col][row]= new EventRect();
            eventRect[map][col][row].x=23;
            eventRect[map][col][row].y=23;
            eventRect[map][col][row].width=2;
            eventRect[map][col][row].height=2;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;

            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
                if(row==gp.maxWorldRow){
                    row=0;
                    map++;
                }
            }

        }
    }

    public void checkEvent(){

        //Check if the player character is more than 1 tile away from the last event
        int xDistance= Math.abs(gp.player.worldX-previousEventX);
        int yDistance= Math.abs(gp.player.worldY-previousEventY);
        int distance=Math.max(xDistance, yDistance);
        if(distance> gp.tileSize){
            canTouchEvent=true;
        }

        if(canTouchEvent) {
            //Event happened
            if (hit(0,27, 15, "right")) {damagePit(27, 15, gp.dialogueState);}
            else if (hit(0,23, 12, "up")) {healingPool(23, 12, gp.dialogueState);}
            else if (hit(0, 80, 89, "any")) {teleport(1,79,88,gp.transitionState);}
            else if (hit(1, 79, 88, "any")) {teleport(0,80,89,gp.transitionState);}
            else if(hit(1,59,88,"up")){speak(gp.npc[1][0]);}
        }
    }
    public boolean hit( int map,int col,int row, String reqDirection){
        boolean hit= false;

        if(map==gp.currentMap) { // this effect will only work in the current map.
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;


            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            //Reset the values;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    public void damagePit(int col, int row ,int gameState){
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialogue = "You Fall into a pit!";
        gp.player.life-=1;
 //       eventRect[col][row].eventDone=true;
        canTouchEvent=false;
    }
    public void healingPool(int col, int row ,int gameState){
        if(gp.keyHandler.enterPressed== true){
            gp.gameState=gameState;
            gp.player.attackCanceled=true;
            gp.playSE(2);
            gp.ui.currentDialogue="You dring the water.\nYour life and mana \nhave been recovered";
            gp.player.life= gp.player.maxLife;
            gp.player.mana= gp.player.maxMana;
        }
    }
    public void teleport(int map, int col,int row,int gameState){
        gp.gameState=gameState;
        tempMap=map;
        tempCol=col;
        tempRow=row;

//        gp.currentMap=map;
//        gp.ui.currentDialogue="You teleport";
//        gp.player.worldX=gp.tileSize*col;
//        gp.player.worldY=gp.tileSize*row;
//        previousEventX=gp.player.worldX;
//        previousEventY=gp.player.worldY;
        canTouchEvent=false;
        gp.playSE(13);
    }

    public void speak(Entity entity){
        if(gp.keyHandler.enterPressed==true){
            gp.gameState=gp.dialogueState;
            gp.player.attackCanceled=true;
            entity.speak();
        }
    }
}
