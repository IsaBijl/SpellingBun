package com.mycompany.spellingbun;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Soundtrack {
    
    public static synchronized void loadSound() throws Exception {
        File soundFile = new File("C:\\Users\\paula\\Documents\\NetBeansProjects\\SpellingBun\\SpellingBun\\src\\main\\resources\\Loonboon.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

//    public void playSound() {
//        if (clip != null && !clip.isRunning()) {
//            clip.start();
//        }
//    }
//
//    public void loopSound() {
//        if (clip != null) {
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        }
//    }
}

