public class SpellingBun {

    public static void main(String[] args) {
        
        boolean isBunnyAlive = true;
        // level difficulty starts at 1
        int difficulty; // declaring difficulty, will be modified later on
        
        class Bunny {
            
            // defining bunny life
            int lifeBunny = 3;
            
            while (isBunnyAlive == true) {
                if (bunnyIsHurt == true) {
                    lifeBunny = lifeBunny - 1;
                    bunnyIsHurt = false;
                } else {
                    if (newLevelCycle == true) {
                        lifeBunny = lifeBunny + 1;
                        newLevelCycle = false;
                    }
                } else {
                    if (lifeBunny == 0) {
                        isBunnyAlive = false;
                        // putting false so it doesn't overflow into negative
                        bunnyIsHurt = false;
                        // boolean that ends the game
                        gameOver = true;
                        break;
                    }
                }
            }
        }
        
        class Score {
            
          // starting score is always set to 0
          int score = 0;
          int foxDefeat = 
          
          while (isBunnyAlive == true) {
            
            
        }
      }  
        
    }
}
