package com.mycompany.spellingbun;

//import com.mycompany.spellingbun.Soundtrack;
//import com.mycompany.spellingbun.FoxSprite;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;

public class SpellingBun {

    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;

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
        f.addKeyListener(game);
        f.pack();
        f.setVisible(true);
        
        // do NOT put this into a while loop
        // but I might as well, I paid for the whole CPU and I will use the whole CPU
        Soundtrack.loadSound();


        Toolkit toolkit = Toolkit.getDefaultToolkit(); /* get AWT toolkit */
        
//        new Timer("Drawer", true).scheduleAtFixedRate(new TimerTask(){
//                @Override
//                public void run() {
//                    /* Logic goes here */
//                    game.update();
//
//                    /* Drawing goes here */
//                    f.repaint();
//                    toolkit.sync();
//                }
//            },
//            100, // Start in 100 ms
//            (int) (1000 / 60)
//        );
        
        int delay = 1000 / 60;

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                game.update();
                f.repaint();
                toolkit.sync();
                }
        };
        new Timer(delay, taskPerformer).start();    
    }
}


class MyGame extends JPanel implements KeyListener {

    // private BufferedImage fox1 = null;
    int foxIndex = 0; // focus on the first fox
    int spawnCooldown = 3 * 60;
    int livesLost = 0;
    Dictionary dictionary = new Dictionary();
    private ArrayList<FoxSprite> foxes = new ArrayList<FoxSprite>();
    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    boolean isTyping = false;
    private BufferedImage grass = null;
    private BufferedImage bush = null;
    private BufferedImage stump = null;
    private BufferedImage bunny1 = null;
    private BufferedImage background = null;
    private BufferedImage bunnyUI = null;
    private BufferedImage carrot3 = null;
    private BufferedImage carrot2 = null;
    private BufferedImage carrot1 = null;
    private BufferedImage carrot0 = null;
    private BufferedImage arrow = null;
    
    public int spawnRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public MyGame() throws FontFormatException, IOException {
        
        File retroFont = new File("retroFont.ttf");
        grass = loadImage("grass.png");
        bush = loadImage("bush.png");
        stump = loadImage("stump.png");
        bunny1 = loadImage("bunny1.png");
        background = loadImage("background.png");
        bunnyUI = loadImage("bunnyUI.png");
        carrot3 = loadImage("carrot3.png"); 
        carrot2 = loadImage("carrot2.png"); 
        carrot1 = loadImage("carrot1.png"); 
        carrot0 = loadImage("carrot0.png");
        arrow = loadImage("arrow.png"); 
    }

    public static BufferedImage loadImage(String name) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            return ImageIO.read(classloader.getResource(name));
        } catch (IOException e) {
            System.out.println("An error happened: " + e.toString());
            return null;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SpellingBun.WINDOW_WIDTH, SpellingBun.WINDOW_HEIGHT);
    }

    public void update() {
    }
    
    public int getYAxis() {
        // return bunny Y location
        return (175 + (livesLost * 129));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        g.drawImage(bunnyUI, 0, 0, 525, 217, this);
        g.drawImage(bush, 0, 420, this);
        g.drawImage(stump, 1400, 275 + (livesLost * 129), 140, 386, this); // 275
        g.drawImage(grass, 0, 590, this);
        g.drawImage(bunny1, 1400, 175 + (livesLost * 129), 128, 128, this);
        g.drawImage(arrow, 200, 675, 64, 64, this); // redo this sprite
        
        spawnCooldown--;
        
        if(isTyping = true) {
            g.drawImage()
        }
        
        if(spawnCooldown <= 0) {
            // https://klipy.com/gifs/fop-fox
            foxes.add(new FoxSprite(-300, 500, new String (dictionary.getWord())));
            spawnCooldown = 3 * 60;
        }        
        if (livesLost <= 0) {
            g.drawImage(carrot3, 0, 0, 525, 217, this);
        } else if (livesLost == 1) {
            g.drawImage(carrot2, 0, 0, 525, 217, this);
        } else if (livesLost == 2) {
            g.drawImage(carrot1, 0, 0, 525, 217, this);
        } else {
            g.drawImage(carrot0, 0, 0, 525, 217, this);
        }
        for (int i = 0; i < foxes.size(); i++) {
            FoxSprite fox = foxes.get(i);
            fox.move();
            fox.paintComponent(g);
            if (livesLost < 3) {          
                if (fox.getXAxis() >= SpellingBun.WINDOW_WIDTH - 400) {
                    // fox kill
                    foxes.remove(i);
                    // https://klipy.com/gifs/fop-fox-1
                    livesLost++;
//                    try {
//                        Soundtrack.loadHurt();
//                    } catch (Exception ex) {
//                        System.getLogger(MyGame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//                    }
                } else if (fox.getXAxis() >= 2000) {
                    foxes.remove(i);
                }
            }
        }   
        if (livesLost >= 3) {
            Font font = new Font("retroFont.ttf", 0, 100);
            String gameOver = "Game over";
            g.setFont(font);
            g.drawChars(gameOver.toCharArray(), 0, gameOver.length(), 800, 450);
        }     
    }

    @Override
    public void keyTyped(KeyEvent e) {       
        if (foxes.isEmpty() == false) {
            foxes.get(foxIndex).testKey(e.getKeyChar());
            // font style change goes here
            if (foxes.get(foxIndex).isKill() == true) {
                foxes.remove(foxIndex);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // foxes.add(new FoxSprite(-300, 500));
        // https://klipy.com/gifs/fop-fox    
//        if (e.KeyEvent.KEY_PRESSED = true) {
//            isTyping = true;
//        }
        if (foxIndex > foxes.size()) {
            foxIndex = foxes.size();
        } else if (foxIndex < 0) {
            foxIndex = 0;
            
        } if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            foxIndex = foxIndex - 1;
            System.out.println("Left");
        } if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            foxIndex = foxIndex + 1;
            System.out.println("Right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {       
    }   
}
