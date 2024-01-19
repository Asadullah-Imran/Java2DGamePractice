package object;

import Main.GamePanel;
import entity.Entity;
import entity.Projectile;

import java.awt.*;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name ="Fireball";
        speed =5;
        maxLife=80;
        life= maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage() {

        up1=setup( "/projectile/fireball_up_1.png",gp.tileSize,gp.tileSize);
        up2= setup("/projectile/fireball_up_2.png",gp.tileSize,gp.tileSize);
        down1= setup("/projectile/fireball_down_1.png",gp.tileSize,gp.tileSize);
        down2= setup("/projectile/fireball_down_2.png",gp.tileSize,gp.tileSize);
        left1=setup ("/projectile/fireball_left_1.png",gp.tileSize,gp.tileSize);
        left2= setup("/projectile/fireball_left_2.png",gp.tileSize,gp.tileSize);
        right1= setup("/projectile/fireball_right_1.png",gp.tileSize,gp.tileSize);
        right2= setup("/projectile/fireball_right_2.png",gp.tileSize,gp.tileSize);
    }
    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.mana>= useCost){
            haveResource=true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= new Color(240,50,0);
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=10;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=1;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=20;
        return maxLife;
    }
}
