package Main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heartFull,heartHalf,heartBlank,crystalFull,crystalBlank;
    public boolean messageOn=false;
//    public String message="";
//    int messageCounter=0; //to set timer so that the message will be disappear after some moment
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public boolean gameFinished=false; // if game is finished then the message will be shown
    public String currentDialogue=""; //for setting the dialogue
    public int commandNum=0; // this is for showing our menu specific commands

    //INVENTORY
    public int slotCol=0;
    public int slotRow=0;
    public int subState=0; //

    //CONSTRUCTOR START

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
 //       OBJ_Key key = new OBJ_Key(gp);
   //     keyImage =key.image;

        //Create Heart Object
        Entity heart= new OBJ_Heart(gp);
        heartFull=heart.image;
        heartHalf=heart.image2;
        heartBlank=heart.image3;
        Entity crystal=new OBJ_ManaCrystal(gp);
        crystalFull=crystal.image;
        crystalBlank=crystal.image2;
    }
    public void addMessage(String text){
//        message = text;
//        messageOn = true;
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){
        //we did this because we need to use this g2 in other methods also
        this.g2=g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        //TITLE STATE
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //Do PlayState stuff
            //Drawing Heart for player life
            drawPlayerLife();
            drawMana();
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            //Do pauseState stuff
            drawPauseScreen();
            drawPlayerLife();
            drawMana();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawMana();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        //OPTION STATE
        if(gp.gameState == gp.optionState){
            drawOptionsScreen();

        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();

        }

    }
    public void drawPlayerLife(){
        //gp.player.life=4;
        int x= gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        //DRAW BLANK HEART
        while(i<(gp.player.maxLife/2)){
            g2.drawImage(heartBlank,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        //RESET THE values
         x= gp.tileSize/2;
         y=gp.tileSize/2;
         i=0;

        //Draw Current LIFE
        while(i<gp.player.life){
            g2.drawImage(heartHalf,x,y,null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heartFull,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }

    }
    public void drawMana(){
       int x=(gp.tileSize/2)-5;
       int y=(int)(gp.tileSize*1.5);
       int i=0;
       while(i<gp.player.maxMana){
           g2.drawImage(crystalBlank,x,y,null);
           i++;
           x+=35;
       }
       //Draw Mana;
         x=(gp.tileSize/2)-5;
         y=(int)(gp.tileSize*1.5);
         i=0;
        while(i<gp.player.mana){
            g2.drawImage(crystalFull,x,y,null);
            i++;
            x+=35;
        }
    }
    public void drawMessage(){
        int messageX=gp.tileSize;
        int messageY=gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        for(int i=0;i<message.size();i++){
            if(message.get(i)!=null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2,messageY+2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX,messageY);
                int counter= messageCounter.get(i)+1;
                messageCounter.set(i,counter); //set the counter to the array;
                messageY+=50;
                if(messageCounter.get(i)>180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen(){
        //to color Background [THough default is black but by chance if we want to change the background color]
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text= "Blue Boy Adventure";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x=gp.screenWidth/2-(gp.tileSize);
        y+=gp.tileSize*2;

        g2.drawImage(gp.player.down1, x, y,gp.tileSize*2, gp.tileSize*2,null);

        //MENU
        //changing font for menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text="New Game";
        x= getXforCenterText(text);
        y+=gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text="Load Game";
        x= getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text="Quit Game";
        x= getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x=getXforCenterText(text);
        int y =gp.screenHeight/2 ;
        //now drawing
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //  CREATING A DIALOGUE WINDOW
        //set parameter for window
        int x=gp.tileSize*2;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x+= gp.tileSize;
        y+= gp.tileSize;

        //to create new line
        for(String line: currentDialogue.split("\n")){
        g2.drawString(line,x,y);
        y+=40;
        }

    }

    public void drawCharacterScreen(){
        //CREATE A FRAME
        final int frameX=gp.tileSize;
        final int frameY=gp.tileSize;
        final int frameWidth=gp.tileSize*5;
        final int frameHeight=(gp.tileSize*10)+20;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //text
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));

        int textX= frameX+20;
        int textY= frameY+ gp.tileSize;
        final int lineHeight=36;

        //NAMES
        g2.drawString("Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Life",textX,textY);
        textY+=lineHeight;

        g2.drawString("Mana",textX,textY);
        textY+=lineHeight;

        g2.drawString("Strength",textX,textY);
        textY+=lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY+=lineHeight;
        g2.drawString("Attack",textX,textY);
        textY+=lineHeight;
        g2.drawString("Defense",textX,textY);
        textY+=lineHeight;
        g2.drawString("Exp",textX,textY);
        textY+=lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Coin",textX,textY);
        textY+=lineHeight+10;
        g2.drawString("Weapon",textX,textY);
        textY+=lineHeight+15;
        g2.drawString("Sheild",textX,textY);

        int tailX=(frameX+ frameWidth)-30;
        //Reset TextY
        textY=frameY+gp.tileSize;

        String value;
        //drawing start
        value= String.valueOf(gp.player.level);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value= String.valueOf(gp.player.life +"/"+gp.player.maxLife);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value= String.valueOf(gp.player.mana +"/"+gp.player.maxMana);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value= String.valueOf(gp.player.strength);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.dexterity);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.attack);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.defense);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.exp);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.nextLevelExp);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        value= String.valueOf(gp.player.coin);
        textX=getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;
        //Drawing Weapon Image
        g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize,textY-24,null);
        textY+=gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1,tailX-gp.tileSize,textY-24,null);

        textY+=lineHeight;

    }

    public void drawInventory(){
        //FRAME
        int frameX=gp.tileSize*9;
        int frameY=gp.tileSize;
        int frameWidth=gp.tileSize*6;
        int frameHeight=gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXStart=frameX+20;
        final int slotYStart=frameY+20;
        int slotX=slotXStart;
        int slotY=slotYStart;
        int slotSize=gp.tileSize+3;

        //Draw Players new Items
        for(int i=0; i<gp.player.inventory.size(); i++){
            //EQUIP CURSOR
            if(gp.player.inventory.get(i)==gp.player.currentWeapon||gp.player.inventory.get(i)==gp.player.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY,null);
            slotX+=slotSize;
            if(i==4 ||i==9||i==14){
                slotY+=slotSize;
                //reset SlotX
                slotX=slotXStart;
            }
        }

        //CURSOR
        int cursorX=slotXStart+(slotSize*slotCol);
        int cursorY=slotYStart+(slotSize*slotRow);
        int cursorWidth=gp.tileSize;
        int cursorHeight=gp.tileSize;
        //Draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10,10);

        //Another subwindow to show the description
        //DESCRIPTION FRAME
        int dFrameX= frameX;
        int dFrameY= frameY+ frameHeight;
        int dFrameWidth= frameWidth;
        int dFrameHeight= gp.tileSize*3;
        //DRAW DESCRIPTION TEXT
        int textX=dFrameX+20;
        int textY=dFrameY+gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(28F));
        int itemIndex=getItemIndexOnSlot();
        if(itemIndex<gp.player.inventory.size()) {
        drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("\n")) {

            g2.drawString(line, textX,textY);
            textY+=32;
            }
        }
    }
    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(28F));

        //SUB WINDOW
        int frameX= gp.tileSize*4;
        int frameY= gp.tileSize;
        int frameWidth= gp.tileSize*8;
        int frameHeight= gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0:optionTop(frameX,frameY); break;
            case 1:options_control(frameX,frameY); break;
            case 2:options_endGameConfirmation(frameX,frameY); break;
        }
        gp.keyHandler.enterPressed=false;
    }
    public void optionTop(int frameX,int frameY){
        int textX;
        int textY;

        //TITLE
        String text="Option";
        textX =getXforCenterText(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text,textX,textY);

        //FullScreen
        textX = frameX+gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                if(gp.fullScreenOn==false){
                    gp.fullScreenOn=true;
                }else if(gp.fullScreenOn==true){
                    gp.fullScreenOn=false;
                }
            }
        }

        //MUSIC
        textY+=gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(commandNum==1){
            g2.drawString(">",textX-25,textY);
        }
        //SE
        textY+=gp.tileSize;
        g2.drawString("SE",textX,textY);
        if(commandNum==2){
            g2.drawString(">",textX-25,textY);
        }
        //CONTROLS
        textY+=gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(commandNum==3){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                subState=1;
                commandNum=0;
            }
        }
        //END GAME
        textY+=gp.tileSize;
        g2.drawString("End Game",textX,textY);
        if(commandNum==4){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                subState=2;
                commandNum=0;
            }
        }
        //Back to Window
        textY+=gp.tileSize*2;
        g2.drawString("Back",textX,textY);
        if(commandNum==5){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                gp.gameState=gp.playState;
                commandNum=0;
            }
        }

        //Full Screen CheckBox
        textX=frameX+(int)(gp.tileSize*4.5);
        textY=frameY+gp.tileSize*2+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullScreenOn==true){
            g2.fillRect(textX,textY,24,24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24); //120/5=24
        int volumeWidth = 24* gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24);//120/5=24
         volumeWidth = 24* gp.se.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //Save the settings
        gp.config.saveConfig();
    }

    public void options_control(int frameX,int frameY){
        int textX;
        int textY;

        //Title
        String text= "Controls";
        textX=getXforCenterText(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text,textX,textY);

        textX=frameX+gp.tileSize;
        textY+=gp.tileSize;

        g2.drawString("Move",textX,textY); textY+=gp.tileSize;
        g2.drawString("Confirm/Attack",textX,textY); textY+=gp.tileSize;
        g2.drawString("Shoot/Cast",textX,textY); textY+=gp.tileSize;
        g2.drawString("Character Screen",textX,textY); textY+=gp.tileSize;
        g2.drawString("Pause",textX,textY); textY+=gp.tileSize;
        g2.drawString("Options",textX,textY); textY+=gp.tileSize;

        textX=frameX+gp.tileSize*6;
        textY=frameY+gp.tileSize*2;
        g2.drawString("WASD",textX,textY); textY+=gp.tileSize;
        g2.drawString("ENTER",textX,textY); textY+=gp.tileSize;
        g2.drawString("F",textX,textY); textY+=gp.tileSize;
        g2.drawString("C",textX,textY); textY+=gp.tileSize;
        g2.drawString("P",textX,textY); textY+=gp.tileSize;
        g2.drawString("ESC",textX,textY); textY+=gp.tileSize;

        //BACK
        textX=frameX+gp.tileSize;
        textY=frameY+gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                subState=0;
                commandNum=3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX,int frameY){
        int textX = frameX+ gp.tileSize;
        int textY = frameY+ gp.tileSize*3;
        currentDialogue= "Quit the game and \nreturn to the title Screen?";
        for(String line:currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY+=40;
        }

        //YES
        String text = "Yes";
        textX=getXforCenterText(text);
        textY+=gp.tileSize*3;
        g2.drawString(text,textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                subState=0;
                gp.gameState=gp.titleState;
            }
        }

        //NO
         text = "No";
        textX=getXforCenterText(text);
        textY+=gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum==1){
            g2.drawString(">",textX-25,textY);
            if(gp.keyHandler.enterPressed==true){
                subState=0;
                commandNum=4;

            }
        }

    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text="Game Over";
        //SHADOW
        g2.setColor(Color.BLACK);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //MAIN
        g2.setColor(Color.WHITE);
        g2.drawString(text,x-4,y-4);
        //RETRY
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
        text="Retry";
        x=getXforCenterText(text);
        y+= gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-40,y);
        }
        //Back to the Title Screen
        text="Quit";
        x=getXforCenterText(text);
        y+= 55;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-40,y);
        }

    }


    public int getItemIndexOnSlot(){
        int itemIndex=slotCol+(slotRow*5);
        return itemIndex;
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c= new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }


    //creating a method to get the middle position of x co ordinate as dependign the text
    public int getXforCenterText(String text){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.screenWidth/2-length/2;
    }
    public int getXforAlignRightText(String text,int tailX){
        int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=tailX-length;
        return x;
    }
}
