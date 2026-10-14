package com.mycompany.spellingbun;

import com.mycompany.spellingbun.Soundtrack;
// import com.mycompany.spellingbun.FoxSprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;

public class SpellingBun {

    static final int WINDOW_WIDTH = 1600;
    static final int WINDOW_HEIGHT = 900;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                runGui();
            } catch (Exception ex) {
                System.getLogger(SpellingBun.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }

    private static void runGui() throws Exception {
        JFrame f = new JFrame("SpellingBun");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        MyGame game = new MyGame();
        f.add(game);
        f.pack();
        f.setVisible(true);
        
        // do NOT put this into a while loop
        // but I might as well, I paid for the whole CPU and I will use the whole CPU
        Soundtrack.loadSound();


        Toolkit toolkit = Toolkit.getDefaultToolkit(); /* get AWT toolkit */
        
        new Timer("Drawer", true).scheduleAtFixedRate(new TimerTask(){
                @Override
                public void run() {
                    /* Logic goes here */
                    game.update();

                    /* Drawing goes here */
                    f.repaint();
                    toolkit.sync();
                }
            },
            100, // Start in 100 ms
            (int) (1000 / 60)
        );    
    }
}

class MyGame extends JPanel {

    private BufferedImage fox1 = null;
    
    private BufferedImage grass = null;
    private BufferedImage bush = null;
    private BufferedImage stump = null;
    private BufferedImage bunny1 = null;
    
    private BufferedImage background = null;
    private BufferedImage bunnyUI = null;

    public MyGame() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        // loading fox sprites, cycle of 3 frames
        fox1 = loadImage("fox1.png");
        
        grass = loadImage("grass.png");
        bush = loadImage("bush.png");
        stump = loadImage("stump.png");
        bunny1 = loadImage("bunny1.png");
        
        background = loadImage("background.png");
        bunnyUI = loadImage("bunnyUI.png");
        
        
        
    }

    BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(name));
        } catch (IOException e) {
            System.out.println("An error happened: " + e.toString());
            return null;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(SpellingBun.WINDOW_WIDTH, SpellingBun.WINDOW_HEIGHT);
    }

    public void update() {
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // fox drawn last otherwise it gets hidden behind other images
        
        g.drawImage(background, 0, 0, this);
        g.drawImage(bunnyUI, 0, 0, 525, 217, this);
        
        g.drawImage(bush, 0, 420, this);
        g.drawImage(stump, 1400, 275, 140, 386, this);
        g.drawImage(grass, 0, 590, this);
        
        g.drawImage(fox1, 0, 485, 320, 160, this);
        g.drawImage(bunny1, 1400, 175, 128, 128, this);
        
        
    }
}
