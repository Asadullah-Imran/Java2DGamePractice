package Main;

import entity.Entity;

//in part 6 for making collision detection this class is created

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol= entityRightWorldX/gp.tileSize;
        int entityTopRow= entityTopWorldY/gp.tileSize;
        int entityBottomRow= entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        //checking for the direction
        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                // in above case what will happen is that we kind of predict where the player will be after he movied
                // in there subtract the speed because in up direction y value will be less as much we go up.
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNum1].collision == true|| gp.tileM.tile[tileNum2].collision == true){ // here we are checking the collision of that two tile if they are solid tiles then we will do any action or not
                    //that means it will hit any solid tile so the player can not move in this direction .
                    entity.collisionOn=true;

                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true|| gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true|| gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true|| gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
        }
    }

    //part 8 Object Interaction part starts

    public int checkObject(Entity entity, boolean player){
        int index =999;

        for(int i=0; i<gp.obj.length;i++){
            if(gp.obj[i]!= null){
                //Get entity solid Area Position
                entity.solidArea.x=entity.worldX + entity.solidArea.x;
                entity.solidArea.y=entity.worldY + entity.solidArea.y;
                //Get the object's solid Area position
                gp.obj[i].solidArea.x=gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        break;
                }

                //rectangle class has a method named intersects to check two rectangeles are overlapperd or not
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){

                    //check the object is solid or not
                    if(gp.obj[i].collision==true){
                        entity.collisionOn=true;
                    }
                    //check this is a player or not (it could be a monster or other npc)
                    if(player==true){
                        index=i;
                    }
                }

                // reset the value
                entity.solidArea.x= entity.solidAreaDefaultX;
                entity.solidArea.y= entity.solidAreaDefaultY;

                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY ;
            }
        }

        return index;
    }
    //part 8 Object Interaction part ends
    //

    // This method is for check NPC or Monster Collision
    //this is identical of checkObject method
    public int checkEntity(Entity entity,Entity[] target) {
        int index =999;

        for(int i=0; i<target.length;i++){
            if(target[i]!= null){
                //Get entity solid Area Position
                entity.solidArea.x=entity.worldX + entity.solidArea.x;
                entity.solidArea.y=entity.worldY + entity.solidArea.y;
                //Get the object's solid Area position
                target[i].solidArea.x=target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y=target[i].worldY + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){

                    if(target[i]!= entity){
                        //check the object is solid or not
                        entity.collisionOn=true;
                        index=i;
                    }
                }
                // reset the value
                entity.solidArea.x= entity.solidAreaDefaultX;
                entity.solidArea.y= entity.solidAreaDefaultY;

                target[i].solidArea.x=target[i].solidAreaDefaultX;
                target[i].solidArea.y=target[i].solidAreaDefaultY ;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity){
        boolean contactPlayer=false;
        //Get entity solid Area Position
        entity.solidArea.x=entity.worldX + entity.solidArea.x;
        entity.solidArea.y=entity.worldY + entity.solidArea.y;
        //Get the object's solid Area position
        gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction){
            case "up":
                entity.solidArea.y-=entity.speed;
                break;
            case "down":
                entity.solidArea.y+=entity.speed;
                break;
            case "left":
                entity.solidArea.x-=entity.speed;
                break;
            case "right":
                entity.solidArea.x+=entity.speed;
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn=true;
            contactPlayer=true;
        }
        // reset the value
        entity.solidArea.x= entity.solidAreaDefaultX;
        entity.solidArea.y= entity.solidAreaDefaultY;

        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY ;

        return contactPlayer;
    }


}
