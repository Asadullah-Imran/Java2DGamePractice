1. in game when we see a character moving it is actually not like video but pic updates. FPS 60, means a screen updates 60 times within a second then it will not look like a picture but a animation moving. So your character looks moving but its actually a series of 60 static images. 
    <br>so to do this animation in our game we need to create a time in our game we need to start this game clock. and to do that we will use a class called Thread and i will mane this gameThread. 
<br> Thread is something you can start and stop and once a thread started, it keeps your program running until you stop it. 



# The Game Start here


## contents
- [All Parts ](#all-parts)
    - [Part 1](#part-1)
    - [Part 6 Collision Detection](#part-6---collision-detection)
    - [Part 7 Object Placement](#part-7---object-placement)
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
2. now create a package in src folder named `object` and in this package create a class named `SuperObject` 
2. 
