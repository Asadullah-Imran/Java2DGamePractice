package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
//    public int x,y;
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,down1, down2,left1,left2,right1,right2;
    public String direction;
    public int spriteCounter=0;
    public  int spriteNum=1;

    //part 6 collision
    public Rectangle solidArea;
    //part 8 Object Interaction part starts
    public int solidAreaDefaultX,solidAreaDefaultY;
    //part 8 Object Interaction part  ends
    public boolean collisionOn= false;
}
