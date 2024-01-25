package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
     public Tile[] tile;
    public int mapTileNum[][][];
    ArrayList<String> filenames=new ArrayList<String>();
    ArrayList<String> collisionStatus=new ArrayList<String>();
    public TileManager(GamePanel gp){
        this.gp=gp;

        //Read the tile data file first
        InputStream is= getClass().getResourceAsStream("/maps/map01_titleData.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(is));

        //Getting Tile data and collision q from the file
        String line;
        try{
            while((line=br.readLine())!=null){
                filenames.add(line);
                collisionStatus.add(br.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }


        //ININITIALIZE the ARRAY BASED ON THE FILENAMES SIZE
        tile= new Tile[filenames.size()];
        getTileImage();

        //Get the max world col and row
        is=getClass().getResourceAsStream("/maps/map01.txt");
        br=new BufferedReader(new InputStreamReader(is));
        try{
            String line2 = br.readLine();
            String maxTile[]=line2.split(" ");
            gp.maxWorldCol=maxTile.length;
            gp.maxWorldRow=maxTile.length;
            mapTileNum= new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();

        }catch (IOException e){
          e.printStackTrace();
        }



        loadMap("/maps/map01.txt",0);
        loadMap("/maps/map03.txt",1);
    }

    public void getTileImage(){

        for(int i=0; i<filenames.size();i++){
            String filename;
            boolean collision;

            //get a file name;
            filename= filenames.get(i);
            //get Collision status
            if(collisionStatus.get(i).equals("true")){
                collision=true;
            }else{
                collision=false;
            }
            setup(i,filename,collision);
        }

    }

    //Part 11 starts
    //create a setup funciton to set the tile so that it will saved its height and width.
    public void setup(int index,String imageName,boolean collision){
        UtilityTool uTool= new UtilityTool();
        try {
            tile[index]=new Tile();
            tile[index].image= ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
            tile[index].image=uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision=collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Part 11 ends

    public void loadMap( String filePath,int map){
        try{
            InputStream is= getClass().getResourceAsStream(filePath);
            BufferedReader br= new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while (col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line= br.readLine();
                while(col<gp.maxWorldCol){
                    String numbers[]= line.split(" ");
                    int num= Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row]=num;
                    col++;
                }

                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;
//        int x=0;
//        int y=0;


        while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum= mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX=worldCol* gp.tileSize;
            int worldY=worldRow* gp.tileSize;
            int screenX= worldX-gp.player.worldX + gp.player.screenX;
            int screenY= worldY-gp.player.worldY + gp.player.screenY;


            //this process is for not drawing the whole world map but the map tiles we needed only
            if(worldX+ gp.tileSize>gp.player.worldX-gp.player.screenX&&
                    worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                    worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY&&
                    worldY- gp.tileSize<gp.player.worldY+gp.player.screenY
            ){

            g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }

            worldCol++;
//            x+=gp.tileSize;


            if(worldCol==gp.maxWorldCol){
                worldCol=0;
//                x=0;
                worldRow++;
//                y+=gp.tileSize;
            }
        }
    }


}
