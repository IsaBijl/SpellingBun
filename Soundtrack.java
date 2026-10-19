package com.mycompany.spellingbun;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Soundtrack {
    
    public static synchronized void loadSound() throws Exception {
        File soundFile = new File("C:\\Users\\Sosallina\\Documents\\NetBeansProjects\\SpellingBun\\src\\main\\java\\com\\mycompany\\spellingbun\\Loonboon.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static synchronized void loadHurt() throws Exception {
        File soundFile = new File("C:\\Users\\Sosallina\\Documents\\NetBeansProjects\\SpellingBun\\src\\main\\java\\com\\mycompany\\spellingbun\\hurt.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clipHurt = AudioSystem.getClip();
        clipHurt.open(audioInputStream);
        clipHurt.start();
    }
}


