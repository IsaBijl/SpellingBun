package com.mycompany.spellingbun;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BunnySprite {
    
    int xAxis = 1300;
    int yAxis;
    private int currentFrame = 0;
    private int currentDelay = 0;
    private static int typeDelay = 7;
    private static BufferedImage[] bunnyType = {
        MyGame.loadImage("bunny2.png"),
        MyGame.loadImage("bunny3.png")
    };

    
    public BunnySprite (int xAxis, int yAxis, String word) {
    this.xAxis = xAxis;
    this.yAxis = yAxis;
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(bunnyType[currentFrame], xAxis, 175, 128, 128, null);
        currentDelay++;
        if (currentDelay >= typeDelay) {
            currentFrame++;
            currentDelay = 0;
        if (currentFrame >= bunnyType.length) {
            currentFrame = 0;
            }
        }
    }
    
    
}
