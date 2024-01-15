package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { //KeyListenr=> this listener interface for recieving keyboard events (Keystrokes);
    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed;
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

        //TITLE STATE
        if(gp.gameState== gp.titleState){
            if(code==KeyEvent.VK_W){ //VK_W means if user press W then
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=2;
                }
            }
            if(code==KeyEvent.VK_S){ //VK_S means if user press S then

                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>2){
                        gp.ui.commandNum=0;
                    }

            }
            if(code==KeyEvent.VK_ENTER){ //VK_ENTER means if user press ENTER then
                if(gp.ui.commandNum==0){
                    gp.gameState=gp.playState;
                    gp.playMusic(0);
                }if(gp.ui.commandNum==1){
                    //ADD LATER for LOAD option
                }if(gp.ui.commandNum==2){
                    System.exit(0);
                }
            }
        }

        //This is for PlayState
        if(gp.gameState== gp.playState){
            if(code==KeyEvent.VK_W){ //VK_W means if user press W then
                upPressed=true;
            }
            if(code==KeyEvent.VK_S){ //VK_S means if user press S then
                downPressed=true;
            }
            if(code==KeyEvent.VK_A){ //VK_A means if user press A then
                leftPressed=true;
            }
            if(code==KeyEvent.VK_D){ //VK_D means if user press D then
                rightPressed=true;
            }
            if(code==KeyEvent.VK_P){ //VK_P means if user press P then
                gp.gameState=gp.pauseState;

            }
            if(code==KeyEvent.VK_ENTER){ //VK_ENTER means if user press ENTER then
                //gp.gameState=gp.playState;
                enterPressed=true;
            }
        }

        //PAUSE state
        else if(gp.gameState==gp.pauseState) {
            if(code==KeyEvent.VK_P){ //VK_P means if user press P then
                gp.gameState=gp.playState;
            }
        }
        //DIALOGUE state
        else if(gp.gameState==gp.dialogueState){
            if(code==KeyEvent.VK_ENTER){ //VK_ENTER means if user press ENTER then
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
