package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { //KeyListenr=> this listener interface for recieving keyboard events (Keystrokes);
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    GamePanel gp;

    //creating Constructor
    public KeyHandler (GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //we will not do anything in keyType method  because we do not need to handleany key type listener for our application
    }

    @Override
    public void keyPressed(KeyEvent e) {
    int code= e.getKeyCode(); //returns ascii value of key pressed
        if(code==KeyEvent.VK_W){ //VK_W means if user press W then
            upPressed=true;
        }
        if(code==KeyEvent.VK_S){ //VK_W means if user press S then
            downPressed=true;
        }
        if(code==KeyEvent.VK_A){ //VK_W means if user press A then
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D){ //VK_W means if user press D then
            rightPressed=true;
        }
        if(code==KeyEvent.VK_P){ //VK_W means if user press D then
            if(gp.gameState==gp.playState){
                gp.gameState=gp.pauseState;
            } else if (gp.gameState==gp.pauseState) {
                gp.gameState=gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode(); //returns ascii value of key pressed
        if(code==KeyEvent.VK_W){ //VK_W means if user press W then
            upPressed=false;
        }
        if(code==KeyEvent.VK_S){ //VK_W means if user press S then
            downPressed=false;
        }
        if(code==KeyEvent.VK_A){ //VK_W means if user press A then
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){ //VK_W means if user press D then
            rightPressed=false;
        }
    }

}
