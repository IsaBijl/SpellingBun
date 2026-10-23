import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FoxSprite {
    
    int wordProgress = 0;
    String word;
    int xAxis;
    int yAxis;
    boolean isKill = false;
    boolean isSelected = false;
    private final int animationLength = 3;
    private static BufferedImage[] foxWalk = {
        MyGame.loadImage("fox1.png"),
        MyGame.loadImage("fox2.png"),
        MyGame.loadImage("fox3.png"),
        MyGame.loadImage("foxH1.png"),
        MyGame.loadImage("foxH2.png"),
        MyGame.loadImage("foxH3.png")
    };
    
    public void testKey(char key) {
        if (isKill) {
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
    
    public FoxSprite(int xAxis, int yAxis, String word) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.word = word;
    }
    
    private int currentFrame = 0;
    private int currentDelay = 0;
    private static final int WALK_DELAY = 7;
    
    public void move() {
        xAxis += 2;
    }
    
    public void paintComponent(Graphics g) {
        
        int arrayOffset = 0;
        if (isSelected) {
            arrayOffset = 3;
        }
        
        int rectStroke = 5;
        int rectWidth = 30 * word.length() + 2 * rectStroke;
        int rectHeigth = 48 + 2 * rectStroke;
        g.setColor(Color.BLACK);
        g.fillRect(xAxis + 50 - rectStroke, yAxis - 155 + 2 * rectStroke, rectWidth, rectHeigth);
        g.setColor(Color.WHITE);
        g.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 48));
        g.drawChars(word.toCharArray(), 0, word.length(), xAxis + 50, yAxis - 100);
        g.drawImage(foxWalk[currentFrame + arrayOffset], xAxis, yAxis, 320, 160, null);
        currentDelay++;
        
        // fox sprite animation
        if (currentDelay >= WALK_DELAY) {
            currentFrame++;
            currentDelay = 0;
            if (currentFrame >= animationLength) {
                currentFrame = 0;
            }
        }
    }
}
