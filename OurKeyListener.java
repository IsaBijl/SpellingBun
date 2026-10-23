import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingUtilities;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

public class OurKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case VK_LEFT, VK_RIGHT, VK_UP, VK_DOWN: {
                if (!SpellingBun.gameOngoing && !SpellingBun.atStartScreen) {
                    switch (ke.getKeyCode()) {
                        case VK_LEFT: {
                            SpellingBun.endScreen.arrowLeft();
                            break;
                        }
                        case VK_RIGHT: {
                            SpellingBun.endScreen.arrowRight();
                            break;
                        }
                        case VK_UP: {
                            SpellingBun.endScreen.arrowUp();
                            break;
                        }
                        case VK_DOWN: {
                            SpellingBun.endScreen.arrowDown();
                            break;
                        }
                        default: {
                        }
                    }
                }
                break;
            }
            case VK_ENTER: {
                if (!SpellingBun.gameOngoing) {
                    if (SpellingBun.atStartScreen) {
                        SpellingBun.atStartScreen = false;
                        SpellingBun.gameOngoing = true;
                        
                        SpellingBun.frame.remove(SpellingBun.startScreen);
                        SpellingBun.frame.add(SpellingBun.game);
                        SpellingBun.frame.addKeyListener(SpellingBun.game);
                    } else {
                        SpellingBun.atStartScreen = true;
                        
                        Leaderboard.submitEntry(SpellingBun.endScreen.retrieveName(), 
                                SpellingBun.game.score);
                        SpellingBun.game.gameReset();
                        SpellingBun.startScreen.visualUpdate();
                        
                        SpellingBun.frame.remove(SpellingBun.endScreen);
                        SpellingBun.frame.add(SpellingBun.startScreen);
                    }
                }
                SwingUtilities.updateComponentTreeUI(SpellingBun.frame);
            }
            default: {
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
