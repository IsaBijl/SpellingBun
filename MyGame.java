import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyGame extends JPanel implements KeyListener {

    int score = 0;
    int foxIndex = 0; // focus on the first fox
    int spawnCooldown = 3 * 60;
    int livesLost = 0;
    int gameTick = 0;
    int wasLastTyping = -100;
    final int idleDelay = 30;
    Dictionary dictionary = new Dictionary();
    private ArrayList<FoxSprite> foxes = new ArrayList<FoxSprite>();
    boolean isTyping = false;
    private BufferedImage grass = null;
    private BufferedImage bush = null;
    private BufferedImage stump = null;
    private BufferedImage background = null;
    private BufferedImage bunnyUI = null;
    private BufferedImage carrot3 = null;
    private BufferedImage carrot2 = null;
    private BufferedImage carrot1 = null;
    private BufferedImage carrot0 = null;
    private final BunnySprite bunny = new BunnySprite();
    
    public int spawnRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public MyGame() /* throws FontFormatException, IOException */ {
        
        grass = loadImage("grass.png");
        bush = loadImage("bush.png");
        stump = loadImage("stump.png");
        background = loadImage("background.png");
        bunnyUI = loadImage("bunnyUI.png");
        carrot3 = loadImage("carrot3.png"); 
        carrot2 = loadImage("carrot2.png"); 
        carrot1 = loadImage("carrot1.png"); 
        carrot0 = loadImage("carrot0.png"); 
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
        g.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 50));
        g.setColor(Color.white);
        g.drawImage(background, 0, 0, this);
        g.drawImage(bunnyUI, 0, 0, 525, 217, this);
        g.drawString("Score", 200, 85);
        g.drawImage(bush, 0, 420, this);
        g.drawImage(stump, 1400, 275 + (livesLost * 129), 140, 386, this);
        g.drawImage(grass, 0, 590, this);       
        g.drawString(String.valueOf(score), 215, 153);
        spawnCooldown--;
        
        bunny.paintComponent(g);
        
        if (spawnCooldown <= 0 || foxes.isEmpty()) {
            // https://klipy.com/gifs/fop-fox
            foxes.add(new FoxSprite(-300, 500, new String(dictionary.getWord())));
            spawnCooldown = 4 * 60;
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
                } else if (fox.getXAxis() >= 2000) {
                    foxes.remove(i);
                }
            }
        }   
        if (livesLost >= 3) {
            gameOver();
        }     
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        wasLastTyping = gameTick;
        if (!foxes.isEmpty()) {
            foxes.get(foxIndex).testKey(e.getKeyChar());
            if (foxes.get(foxIndex).isKill()) {
                foxes.remove(foxIndex);
                score = score + 100;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                if (foxIndex == foxes.size() - 1) {
                    foxIndex = 0;
                } else {
                    foxIndex += 1;
                }
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (foxIndex == 0) {
                    foxIndex = foxes.size() - 1;
                } else {
                    foxIndex += -1;
                }
                break;
            }
            default: {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void selectionUpdate() {
        if (!foxes.isEmpty()) {
            for (int index = 0; index < foxes.size(); index++) {
                foxes.get(index).isSelected = false;
            }
            foxes.get(foxIndex).isSelected = true;
        }
    }
    
    public void typingCheck() {
        if (gameTick > idleDelay) {
            isTyping = wasLastTyping + idleDelay > gameTick;
        }
    }
    
    private void gameOver() {
        SpellingBun.endScreen.score = String.format("%06d", score);
        SpellingBun.frame.add(SpellingBun.endScreen);
        SpellingBun.endScreen.visualUpdate();
        SpellingBun.frame.remove(SpellingBun.game);
        SpellingBun.frame.removeKeyListener(SpellingBun.game);
        SwingUtilities.updateComponentTreeUI(SpellingBun.frame);
        SpellingBun.frame.requestFocus();
        SpellingBun.gameOngoing = false;
    }
    
    public void gameReset() {
        score = 0;
        foxIndex = 0;
        livesLost = 0;
        dictionary.usedWords.clear();
        foxes.clear();
    }
}
