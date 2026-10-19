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
        
        Font font = new Font("retroFont.ttf" , 0, 60);
        g.setFont(font);
        g.drawChars(word.toCharArray(), 0, word.length(), xAxis + 50, yAxis - 50);
        
        g.drawImage(foxWalk[currentFrame], xAxis, yAxis, 320, 160, null);
        currentDelay++;
      
        if (currentDelay >= walkDelay) {
            currentFrame++;
            currentDelay = 0;
            if (currentFrame >= foxWalk.length) {
                currentFrame = 0;
            }
        }
        

        
//    FoxSprite foxSprite = new FoxSprite();
//    foxSprite.paintComponent(g);
        
//    ActionListener frameSwitch = new ActionListener() {
//        
//        public void actionPerformed(ActionEvent evt) {       
//            int xAxis = -300; // spawn off-screen
//            int reset = 1; // animation resets when 3 frames are drawn
//                
//            for(reset = 1; reset < 4; reset = reset + 1) {
//                    switch(reset) {
//                        case 1:
//                            g.drawImage(fox1, xAxis, 590, 320, 160, (ImageObserver) this);
//                            xAxis = xAxis + 3; // move three pixels to the right
//                        case 2:
//                            g.drawImage(fox2, xAxis, 590, 320, 160, (ImageObserver) this);
//                            xAxis = xAxis + 3; // move three pixels to the right
//                        case 3:
//                            g.drawImage(fox3, xAxis, 590, 320, 160, (ImageObserver) this);
//                            xAxis = xAxis + 3; // move three pixels to the right
//                    }
//                    if (reset == 3) {
//                        reset = 1;
//                    }     
//                }      
//            // new Timer(delay, frameSwitch).start();
//        }
//    }; 
//    Timer timer = new Timer (125, frameSwitch);
//    timer.restart();
    }
}




//            Timer timer = new Timer(40, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    updatePosition();
//                    repaint();
//                }
//            });
//            timer.start();