import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BunnySprite {
    
    final int xAxis = 1400;
    private int currentFrame = 0;
    private int currentDelay = 0;
    private static final int TYPE_DELAY = 7;
    private static BufferedImage bunnyIdle = MyGame.loadImage("bunny1.png");
    private static BufferedImage[] bunnyTyping = {
        MyGame.loadImage("bunny2.png"), // typing 1
        MyGame.loadImage("bunny3.png") // typing 2
    };

    
    public BunnySprite() {
    }
    
    public void paintComponent(Graphics g) {
        if (SpellingBun.game.isTyping) {
            g.drawImage(bunnyTyping[currentFrame], xAxis, 
                    SpellingBun.game.getYAxis(), 128, 128, null);
            currentDelay++;
            if (currentDelay >= TYPE_DELAY) {
                currentFrame++;
                currentDelay = 0;
                if (currentFrame >= bunnyTyping.length) {
                    currentFrame = 0;
                }
            }
        } else {
            g.drawImage(bunnyIdle, xAxis, SpellingBun.game.getYAxis(), 128, 128, null);
        }
    }
    
    
}
