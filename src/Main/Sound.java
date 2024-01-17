package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

//Part 9 - sound start
public class Sound {
    Clip clip;
    URL soundUrl[]= new URL[30];
    public Sound(){
        soundUrl[0]=getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundUrl[1]=getClass().getResource("/sound/coin.wav");
        soundUrl[2]=getClass().getResource("/sound/powerup.wav");
        soundUrl[3]=getClass().getResource("/sound/unlock.wav");
        soundUrl[4]=getClass().getResource("/sound/fanfare.wav");
        soundUrl[5]=getClass().getResource("/sound/hitmonster.wav");
        soundUrl[6]=getClass().getResource("/sound/receivedamage.wav");
        soundUrl[7]=getClass().getResource("/sound/swordswing.wav");
        soundUrl[8]=getClass().getResource("/sound/levelup.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
