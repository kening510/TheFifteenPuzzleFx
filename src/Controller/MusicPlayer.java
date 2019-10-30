package Controller;

import javafx.scene.media.AudioClip;

import java.net.URL;

class MusicPlayer {

    private AudioClip audioClip;

    public void loadSound() {
        URL url;
        try {
            url = MusicPlayer.class.getResource("/sounds/button_sound.mp3");
            String realPath = url.toExternalForm();
            audioClip = new AudioClip(realPath);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void playSound() {
        audioClip.play();
    }
}
