package sample;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class MusicPlayer {

    private AudioClip audioClip;

    public void loadSound(String path){
        URL url;
        try{
            url = MusicPlayer.class.getResource(path);
            String realPath = url.toExternalForm();
            audioClip = new AudioClip(realPath);
        }catch(Error e){
            System.out.println(e.getMessage());
        }
    }

    public void playSound(){
        audioClip.play();
    }
}
