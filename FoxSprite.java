package com.mycompany.spellingbun;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FoxSprite {
    
    int wordProgress = 0;
    String word;
    int xAxis;
    int yAxis;
    boolean isKill = false;
    private static BufferedImage[] foxWalk = {
        MyGame.loadImage("fox1.png"),
        MyGame.loadImage("fox2.png"),
        MyGame.loadImage("fox3.png")
    };
    
    public void testKey(char key) {
        if (isKill == true) {
            return; // preventing out of bounds
        }
        char letter = word.charAt(wordProgress); // letter index
        if (letter == key) {
            wordProgress++;
        } else {
            wordProgress = 0;
        }
        if (wordProgress >= word.length()) {
            isKill = true;
        }
    }
    
    public boolean isKill() {
        return isKill;
    }
    
    public int getXAxis() {
        return xAxis;
    }
    
    public FoxSprite (int xAxis, int yAxis, String word) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.word = word;
    }
    
    private int currentFrame = 0;
    private int currentDelay = 0;
    private static int walkDelay = 7;
    
    public void move() {
        xAxis +=2;
    }
    
    public void paintComponent(Graphics g) {
        
        Font font = new Font("retroFont.ttf" , 0, 35);
        g.setFont(font);
        g.drawChars(word.toCharArray(), 0, word.length(), xAxis + 50, yAxis - 50);      
        g.drawImage(foxWalk[currentFrame], xAxis, yAxis, 320, 160, null);
        currentDelay++;
        
        // fox sprite animation
        if (currentDelay >= walkDelay) {
            currentFrame++;
            currentDelay = 0;
            if (currentFrame >= foxWalk.length) {
                currentFrame = 0;
            }
        }
    }
}
