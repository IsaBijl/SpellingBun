package com.mycompany.spellingbun;

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

        SwingUtilities.invokeLater(() -> runGui());
    }

    private static void runGui() {
        JFrame f = new JFrame("Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        MyGame game = new MyGame();
        f.add(game);
        f.pack();
        f.setVisible(true);

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
    private BufferedImage fox2 = null;
    private BufferedImage fox3 = null;
    
    private BufferedImage grass = null;
    private BufferedImage bush = null;

    public MyGame() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        // loading fox sprites, cycle of 3 frames
        fox1 = loadImage("fox1.png");
        fox2 = loadImage("fox2.png");
        fox3 = loadImage("fox3.png");
        
        grass = loadImage("grass.png");
        bush = loadImage("bush.png");
        
    }

    BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(name));
        } catch (IOException e) {
            System.out.println("An error happened :( -> " + e.toString());
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
        g.drawImage(fox1, 0, 0, this);
        g.drawImage(fox1, 30, 20, this);
        
        g.drawImage(bush, 0, 420, this);
        g.drawImage(grass, 0, 590, this);
    }
}
