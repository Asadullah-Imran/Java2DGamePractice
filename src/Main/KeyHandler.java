package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { //KeyListenr=> this listener interface for recieving keyboard events (Keystrokes);
    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed,shotKeyPressed;
    GamePanel gp;

    boolean showDebugText=false;


    //creating Constructor
    public KeyHandler (GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //we will not do anything in keyType method  because we do not need to handle any key type listener for our application
    }

    @Override
    public void keyPressed(KeyEvent e) {
    int code= e.getKeyCode(); //returns ascii value of key pressed
        //TITLE STATE
        if(gp.gameState== gp.titleState){
            titleState(code);
        }
        //This is for PlayState
        else if(gp.gameState== gp.playState){
           playState(code);
        }
        //PAUSE state
        else if(gp.gameState==gp.pauseState) {
            pauseState(code);
        }
        //DIALOGUE state
        else if(gp.gameState==gp.dialogueState){
            dialogueState(code);
        }
        //CHARACTER STATE
        else if (gp.gameState==gp.characterState) {
            characterState(code);
        }
        //Option STATE
        else if (gp.gameState==gp.optionState) {
            optionsState(code);
        }
        //Game Over STATE
        else if (gp.gameState==gp.gameOverState) {
            gameOverState(code);
        }
    }

    //METHOD for ALL GAME STATE
    public void titleState(int code){
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
    public void playState(int code){
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

        }if(code==KeyEvent.VK_C){ //VK_C means if user press C then
            gp.gameState=gp.characterState;
        }
        if(code==KeyEvent.VK_ENTER){ //VK_ENTER means if user press ENTER then
            //gp.gameState=gp.playState;
            enterPressed=true;
        }
        if(code==KeyEvent.VK_F){
            shotKeyPressed=true;
        }
        if(code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.optionState;
        }

        //DEBUG
        if(code==KeyEvent.VK_T) { //VK_T means if user press T then the action will call.
            if(showDebugText==false) {
                showDebugText = true;
            }else if(showDebugText==true) {
                showDebugText = false;
            }

        }
        if(code==KeyEvent.VK_R) { //VK_T means if user press T then the action will call.
            gp.tileM.loadMap("/maps/worldV2.txt");
        }



    }
    public void pauseState(int code){
        if(code==KeyEvent.VK_P){ //VK_P means if user press P then
            gp.gameState=gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code==KeyEvent.VK_ENTER){ //VK_ENTER means if user press ENTER then
            gp.gameState=gp.playState;
        }
    }
    public void characterState(int code){
        if(code==KeyEvent.VK_C){ //VK_C means if user press C then
            gp.gameState=gp.playState;
        }if(code==KeyEvent.VK_W){ //VK_W means if user press W then
            if(gp.ui.slotRow!=0){
            gp.ui.slotRow--;
            gp.playSE(9);
            }
        }
        if(code==KeyEvent.VK_S){ //VK_S means if user press S then
            if(gp.ui.slotRow!=3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if(code==KeyEvent.VK_A){ //VK_A means if user press A then
            if(gp.ui.slotCol!=0) {
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if(code==KeyEvent.VK_D){ //VK_D means if user press D then
            if(gp.ui.slotCol!=4) {
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
        if(code==KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }

    }


    public void optionsState(int code){
        if(code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.playState;
        }
        if(code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
        int maxCommandNum=0;
        switch (gp.ui.subState){
            case 0: maxCommandNum=5;break;
            case 2: maxCommandNum=1;break;
        }
        if(code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(9);
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=maxCommandNum;
            }
        }
        if(code==KeyEvent.VK_S){

            gp.ui.commandNum++;
            gp.playSE(9);
            if(gp.ui.commandNum>maxCommandNum){
                gp.ui.commandNum=0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState==0){
                if(gp.ui.commandNum==1 &&gp.music.volumeScale>0){
                    gp.music.volumeScale--;
                    gp.playSE(9);
                }
                if(gp.ui.commandNum==2 &&gp.se.volumeScale>0){
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState==0){
                if(gp.ui.commandNum==1 &&gp.music.volumeScale<5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum==2 &&gp.se.volumeScale<5){
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }

    }

    public void gameOverState(int code){
        if(code==KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=1;
            }
            gp.playSE(9);
        }
        if(code==KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1){
                gp.ui.commandNum=0;
            }
            gp.playSE(9);
        }
        if(code==KeyEvent.VK_ENTER){
            if(gp.ui.commandNum==0){
                gp.gameState=gp.playState;
                gp.retry();
                gp.playMusic(0);
            }
            else if(gp.ui.commandNum==1){
                gp.gameState=gp.titleState;
                gp.restart();
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
        if(code==KeyEvent.VK_F){

            shotKeyPressed=false;
        }
    }

}
