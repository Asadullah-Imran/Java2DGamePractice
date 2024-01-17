package Main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heartFull,heartHalf,heartBlack;
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
        heartBlack=heart.image3;
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
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            //Do pauseState stuff
            drawPauseScreen();
            drawPlayerLife();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }

    }
    public void drawPlayerLife(){
        //gp.player.life=4;
        int x= gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        //DRAW BLANK HEART
        while(i<(gp.player.maxLife/2)){
            g2.drawImage(heartBlack,x,y,null);
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
        final int frameHeight=gp.tileSize*10;
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
        textY+=lineHeight+20;
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
        g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize,textY-14,null);
        textY+=gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1,tailX-gp.tileSize,textY-14,null);

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
        drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
        //DRAW DESCRIPTION TEXT
        int textX=dFrameX+20;
        int textY=dFrameY+gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex=getItemIndexOnSlot();
        if(itemIndex<gp.player.inventory.size()) {
            for(String line : gp.player.inventory.get(itemIndex).description.split("\n")) {

            g2.drawString(line, textX,textY);
            textY+=32;
            }
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
