import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Leaderboard {
    public static final int LIST_LENGTH = 10; // amount of scores saved
    public static final int NAME_LENGTH = 3; // how many letters in player name
    
    private static int[][] lBoard = compileLeaderboard();
    
    private static int[][] compileLeaderboard() {
        int[][] array = new int[LIST_LENGTH][NAME_LENGTH + 1];
        File source = new File("Leaderboard.txt");
        
        for (int x = 0; x < LIST_LENGTH; x++) { // default names to AAA for empty entries
            for (int y = 0; y < NAME_LENGTH; y++) {
                array[x][y] = (int) 'A';
            }
        }
        
        try (Scanner scanner = new Scanner(source)) {
            for (int x = 0; x < LIST_LENGTH; x++) {
                for (int y = 0; y < NAME_LENGTH + 1; y++) {
                    if (scanner.hasNextInt()) {
                        array[x][y] = scanner.nextInt();
                    }
                }
            }
            
        } catch (FileNotFoundException error) {
            System.out.println("Leaderboard.txt not found");
        }
        return array;
    }
    
    public static int[][] getLeaderboard() {
        return lBoard;
    }
    
    public static void submitEntry(String[] name, int score) {
        char[] charArray = new char[NAME_LENGTH];
        for (int index = 0; index < NAME_LENGTH; index++) {
            charArray[index] = name[index].charAt(0);
        }
        int position = getPosition(score);
        if (position < LIST_LENGTH) {
            BoardUpdate(position, charArray, score);
            updateFile();
        }
    }
    
    private static int getPosition(int score) {
        int position = LIST_LENGTH; // default to outside of leaderboard
        boolean greaterFound = false;
        int compareIndex = LIST_LENGTH - 1;
        
        do {
            if (score > lBoard[compareIndex][NAME_LENGTH]) {
                position = compareIndex;
            } else {
                greaterFound = true;
            }
            compareIndex--;
        } while (!greaterFound && position > 0);
        return position;
    }
    
    private static void BoardUpdate(int position, char[] name, int score) {
        for (int x = LIST_LENGTH - 1; x > position; x--) { // shift old entries down
            for (int y = 0; y < NAME_LENGTH + 1; y++) {
                lBoard[x][y] = lBoard[x - 1][y];
            }
        }
        for (int y = 0; y < NAME_LENGTH; y++) { // fill new name
            lBoard[position][y] = (int) name[y];
        }
        lBoard[position][NAME_LENGTH] = score; // fill new score
    }
    
    private static void updateFile() {
        String output = "";
        
        for (int x = 0; x < LIST_LENGTH; x++) {
            for (int y = 0; y < NAME_LENGTH + 1; y++) {
                output += lBoard[x][y];
                output += " ";
            }
        }
        
        try (FileWriter writer = new FileWriter("Leaderboard.txt", false)) {
            writer.write(output);
        } catch (Exception e) {
            System.out.println("Leaderboard.txt not found");
        }
        
    }
    
}
