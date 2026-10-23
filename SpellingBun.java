import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SpellingBun {

    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    static JFrame frame = new JFrame("SpellingBun");
    static StartScreen startScreen = new StartScreen();
    static MyGame game = new MyGame();
    static EndScreen endScreen = new EndScreen();
    static OurKeyListener listener = new OurKeyListener();
    public static boolean gameOngoing = false;
    public static boolean atStartScreen = true;
    public static final int DELAY = 1000 / 60;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                runGui();
            } catch (Exception ex) {
                System.getLogger(SpellingBun.class.getName())
                        .log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }

    private static void runGui() throws Exception {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.requestFocus();
        frame.addKeyListener(listener);
        frame.add(startScreen);
        
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (gameOngoing) {
                    game.update();
                    game.selectionUpdate();
                    game.typingCheck();
                    game.gameTick++;
                }
                frame.repaint();
                }
        };
        new Timer(DELAY, taskPerformer).start();
    }
}