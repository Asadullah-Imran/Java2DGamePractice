1. in game when we see a character moving it is actually not like video but pic updates. FPS 60, means a screen updates 60 times within a second then it will not look like a picture but a animation moving. So your character looks moving but its actually a series of 60 static images. 
    <br>so to do this animation in our game we need to create a time in our game we need to start this game clock. and to do that we will use a class called Thread and i will mane this gameThread. 
<br> Thread is something you can start and stop and once a thread started, it keeps your program running until you stop it. 



# The Game Start here


## contents
- [All Parts ](#all-parts)
    - [Part 1](#part-1)
    - [Part 6 Collision Detection](#part-6---collision-detection)
    - [Part 7 Object Placement](#part-7---object-placement) 
    - [Part 8 Object Interaction](#part-8---object-interaction)
    - [Part 9 Sound](#part-9---sound)
    - [Part 10 first game done with tresure hunt](#part-10---the-first-game-done-treasure-hunting)
    - [Part 11 improving render performance](#part-11---improving-renderperformance)
    - [Part 12 Update Tile and map](#part-12---update-tiles-and-map)
      - 
  

## All Parts

### Part 1
1. Firstly created Main class 
 and also added intantiate JFrame
 ```java
package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        //Adding game panel
        GamePanel gamePanel= new GamePanel();

        //adding components
        window.add(gamePanel);

        window.pack();//Cause this window to be sized to fit the preferred size and layouts of its subcomponents (GamePanel)


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
```

2. Then i created another class named GamePanel which extend JPanel .
this GamePanel will works as a kind of our game screen. so we will adding our screen settings


### Part 6 - Collision Detection:
1. firstly set collision false as default in my Tile class
```java
package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
}

```
2. after that we will look into our TileManager class. in tile array if there is something solid like wall, tree etc we will set collision true. so that our collision part settings of tiles are completed. 

```java
public class TileManager{
    tile[0]= new Tile();
    tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
    tile[1]= new Tile();
    tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
    tile[1].collision=true;
}
```

3. now we will look in to set players solid area. we will not fully make our player solid of 48x48 pixel full.

in `Entity` class
 - firstly we will create a Rectangle class (predefined java class) named `solidArea`.
    with this class we can create an invisible or abstract rectange and we can store data such as x,y,width,and height.
 - also we will create a boolean called `collisionOn` and the default is `false`.
 - 
```java
public class Entity {
    //part 6 collision;
    public Rectangle solidArea;
    public boolean collisionOn= false;
}
```
in `Player` class
- in Player class intantiate the Rectangle(solidArea) and set the value of x,y, width and height.
- since we want to make the rectangel a bit smaller than the player character.
- we want to set our character should have 32x32 collision part . as our player character has 48x48 area so we have to adjust some
- [i will add picture later]
- we will set our 32x32 collision part will be bottom middle point so. top left corner is x=8,and y=16 and width is 32 and height is also 32.
- so now we can set our `areaSolid` object's value
```java
class Player {
//part 6 collision part starts
    solidArea=new Rectangle();
    solidArea.x=8;
    solidArea.y=16;
    solidArea.width=32;
    solidArea.height=32;
}
```
Player class collision setting are set ,
4. Now we are going to create a class named `CollionChecker` in main package
- now create a constructor with taking GamePanel as constructor
- and create a method of `checkTile` and we recieve(or take parameter) Entity here. we take `Entity but not player` because we will use this method to check not onlu player collision but also monster and NPC collision as well.
- now instantiate it in the `GamePanel` class
```java
public class GamePanel{
    CollisionChecker cChecker= new CollisionChecker(this);
}
```
- now we are go to `Player` class and int the update method we will add some code [cz we will check there the collision part]
<br> firstly we will set this collisionOn false ; then call this checkTile method from CollionChecker Class . and pass this player class as parameter;
```java
class Player{
    collisionOn = false;
    gp.cChecker.checkTile(this);
}
```



5. CheckCollision 
    Now comeback to `CollisionChecker` and in `checkTile` method we will add some code to check if the player is hitting a solid tile or not . there are 4 points to be checked.
    `Left X, Right X, Top Y, and Bottom Y.`
    <br> now we can find all these 4 numbers like this
    <br>
    first of all here ` solidArea.x=8; solidArea.y=16; solidArea.width=32; solidArea.height=32;`
    <br> so here 
    ```java
    public void checkTile(Entity entity) {
            int entityLeftWorldX = entity.worldX + entity.solidArea.x;
            int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
            int entityTopWorldX = entity.worldY + entity.solidArea.y;
            int entityBottomWorldX = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        }
    ```
    here is we get the co-orditate or 4 points of player collision position in map . then based on these coordinates we c=will find out their column and row numbers.
    
    ```java
    public void checkTile(Entity entity) {
            int entityLeftCol=entityLeftWorldX/gp.tileSize;
            int entityRightCol= entityRightWorldX/gp.tileSize;
            int entityTopRow= entityTopWorldY/gp.tileSize;
            int entityBottomRow= entityBottomWorldY/gp.tileSize;
        }
    ```
    - now we will need to create two variables (tileNum1,tileNum2) to check tile. like when it will move up then it will check two tiles of its left and right columns tile and as it si on up direction so it will check ahead two tiles.
    `entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;`  // in above case what will happen is that we kind of predict where the player will be after he movied
      // in there subtract the speed because in up direction y value will be less as much we go up.
    - now we know the row number so we can find out what tile the player is trying to step in. the tileNum1 and tileNum2.
   -  *** Ending part is left to write...

6. Summery: 
   - set the tile collision true which a character can not move
   - set the collisionOn true when player is about to cross the collision tiles.
   - set the movement only when his front tiles are not collision .

### Part 7 - Object placement
1. in res folder create an another folder namded objects and upload the neccessary image for objects (obviously think about the size)
2. now create a package in src folder named `object` and in this package create a class named `SuperObject` . This class is parent class of all object classes. it is kind of simmilar of Entity class. and added needed properties variable
3. now create another class named `OBJ_Key` for key object which will inherited SuperObject. add name , image etc.
4. Now in `GamePanel` class we create array of objects like we created for our Tile class. 
5. Now create a new class named `AssetSetter` in main package (which take GamePanes as parameter) where we will handle the placement for our objects // (we do GamePanel as a parameter or something else it actually for when we do not want to write in that classs {GamePanel} but somwwhere else); and intantiate in GamePanel.
6. So now we are going to instantiate some default objects and place them on the map. so now we are gonna use the `obj` array.
    - created a method named `setObject` in `AssetSetter` to set the object in the map
    - in this method we are gonna intialized the obj array and set the object in different places . using x position and y position.
7. In `GamePanel` class create a method named `setUpGame` and in that class call the `setObject` method from assetSetter and call the `setupGame` method before the game start so we need to call this method in main class before the gamethread start.
8. All position are set.! Now we need to draw the object.
    - create a draw method in SuperObject Method.
    - now we do the same thing we did before for Tile drawing. so we need to find out objects image and screenX and screenY .
    - Finally Implement the draw method in `GamePanel` class
    - 

### Part 8 - Object Interaction
1. first go to Entity class and then create a new two variables ``public int solidAreaDefaultX,solidAreaDefaultY;``
2. and go to `Player` class and the solidAreaDefaultX and solidAreaDefaultY values as the solidArea x and y. we are just saving it because we need to change the value of solid area x and y.
3. now in `SuperObject` class intantiate `Rectangle` class named `solidArea` and  add default value of as (0,0 and the height and width) as i need to make solid whole object area. and also add defaultX and defaultY values .
4. set collision true of solid object. 
5. now in CollisionChecker class we will create a method called checkObject which has integer return type and it will caught two parameters entity and boolean data. so first we recieve entity so player or maybe other character we will check also that this entity is player or not . and it returns the index number of object.
6. add the `checkObject` method in the player class;
6. now create a new method named `pickUpObject` which take index number as parameter.



### Part 9 - Sound
1. create a new folder in res folder named `sound` and add music in there. we need to add `wav` extension music in java `mp3    ` dosen't work here.
2. create a new class named `Sound` in main package and create a Clip (which is used to open audio file in java)
   - create a an array of URL  to store the path of music we will also set the size of the array is 30
   - create a constructor and set all the audio file path in `soundURL` array.
   - now create four method of `setFile`,`play`,`loop`,`stop` and add functionablity
3. Now intantiate the `Sound` class in `GampePanel` class
    - create methods of `playMusic` `stopMusic` and `playSE`
    - now call the `playMusic` method in `setupGame` method. 
4. and also added Soundeffects in Player class when player is hitting key or some other object. 

### Part 10 - The First Game Done (Treasure hunting)
Now we are creating a UI design for our game
1. create a `UI` class in main package and add necessary code
2. intantiate it in `GamePanel` class.

### Part 11 - Improving RenderPerformance
As after the 10 part we will gonna handle a bit more heavy stuff so we need to optimize our code a little bit
1. First we are gonna check how long it take to draw these all objects.
2. Try not to instantiate object in gameloop.
3. `g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null); ` in here after the x and y postion we used to add width and height to scale the image  in every frame . so if we scale it beforehand then it will be very time-saving process.
like that 
```java
public class TileManager{
    tile[0]= new Tile();
    tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

    BufferedImage scaledImage = new BufferedImage(gp.tileSize,gp.tileSize,tile[0].image.getType());
    Graphics2D g2= scaledImage.createGraphics();
    g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
    tile[0].image=scaledImage;
}
```
like that you can create a scaled Image which will save the height and width.
4. now we will not obviouly scale the image like that . but we will create a class 
5. In TileManager class we created a function named `setup` which will set the image for tiles. 
6. tile scaling is done ! now we will scale Player image . as like as tile but a little bit different because we are not use array in player char but tiles.  
7. now the last part of Object scaling.now open superObject and instantiate our UtilityTool there as its the parent class; 

### Part 12 - update tiles and map;
here we update our tiles and map 
#### Now our Second part will begun for the game
here we will have
- Game State
- NPC and Dialog system
- Player Status (Life,Level,Aattack, Defense , Exp etc.)
- Monsters (Simple AI) and Battle System (Hit detection)
- Inventory
- Merchant NPC (Buy and sell items)
- Tile Screen , Game Over Screen
- Environment Interaction (Cutting trees with axe)
- Item Drop
- In-game Menu
- Advance UI
- New Area (map transition)
- Events(Boss fight etc)
- save/load
- Ending Screen

### Part 13 -  Game State
In this part we will 
- set Game State
- Implement Pause Screen 
- Do some house-keeping 

1. in `GamePanel` we created Gamestate variable and implent it on update method
2. now in `KeyHandler` we will create a shortcut for pausing the game; but first we need to create a constructor and recieve gamePanel and add a code for pressing `P` key.
```java
    class KeyHandler{
    if(code==KeyEvent.VK_P){ //VK_W means if user press D then
        if(gp.gameState==gp.playState){
            gp.gameState=gp.pauseState;
        } else if (gp.gameState==gp.pauseState) {
            gp.gameState=gp.playState;
        }
    }
}
```
3. Now in `UI` class we implement pause state UI and remove others.

### Part 14 - NPC creation
Now we will create NPC Character 
1. First in `res` folder we will create a new Folder named `NPC` and move NPC image 
2. Now we will do some HouseKeeping before we do ready our NPC character
    - In `Entity` class we will create a Constructer which recieve GamePanel as parameter
    - Change a little bit in Player class of its constructor.. do super call and add gp in super.
3. in `Entity` package we will create a class named `NPC_OldMan` and necessary code
4. now in `GamePanel` we will create an object array of entity and in `object setter` we will create array and initialize our `npc` character. and also we create `draw` method in `entity` class and use this draw method in `gamepanel` `panint` method
5. now we will create two method in `Entity` class named `setAction` and `update` to move our character 
6. 


### Part 15 - NPC Dialouge

### Part 16 - Object Interaction
### Part 17 - Object Interaction
### Part 18 - Object Interaction
### Part 19 - Object Interaction
### Part 20 - Object Interaction
### Part 21 - Object Interaction
### Part 22 - Object Interaction
### Part 23 - Object Interaction
### Part 24 - Object Interaction
### Part 25 - Object Interaction
### Part 26 - Object Interaction
### Part 27 - Object Interaction
### Part 28 - Object Interaction
### Part 29 - Object Interaction

