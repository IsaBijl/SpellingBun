import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private final int memSize = 5; // maximum length of repetition ban list
    private final int minLength = 4; // how long a word needs to be to be stored
    
    private final ArrayList<String> wordList;
    private final int wListLength;
    private final boolean longEnough; // used to bypass repetition ban list if wordList is too short
    public ArrayList<Integer> usedWords = new ArrayList<>(); // repetition ban list
    
    public Dictionary() {
        wordList = compileDictionary();
        wListLength = wordList.size();
        longEnough = wListLength > memSize;
    }
    
    private ArrayList<String> compileDictionary() {
        ArrayList<String> list = new ArrayList<>();
        File source = new File("Word Source.txt");  // retrieve word source file
        
        try (Scanner scanner = new Scanner(source)) { // start reading the file
            
            while (scanner.hasNextLine()) { // run through every line of text
                String line = scanner.nextLine().toLowerCase();
                int lineLength = line.length();
                char currentChar;
                String currentWord = "";
                boolean wordEnd;
                
                for (int i = 0; i < lineLength; i++) { // run through every character in line
                    currentChar = line.charAt(i);
                    wordEnd = i >= lineLength - 1; // check if character is last in line
                    // this method of filtering for words is very simplistic and coarse
                    // but accounting for every
                    // edge case would quickly go far beyond the scope of this project
                    if (currentChar >= 'a' && currentChar <= 'z') {
                        // check if character is in standard alphabet
                        currentWord += currentChar;
                    } else {
                        wordEnd = true;
                    }
                    if (wordEnd) {
                        if (currentWord.length() >= minLength && !list.contains(currentWord)) {
                            // check if word is long enough to use and not already in dictionary
                            list.add(currentWord);
                        }
                        currentWord = "";
                    }
                }
            }
        } catch (FileNotFoundException error)  {
            System.out.println("Word Source.txt not found");
        }
        
        if (list.isEmpty()) { // fallback for empty or nonexistent "Word Source.txt"
            list.add("vacuum");
        }
        
        return list;
    }
    
    public char[] getWord() {
        // used to bypass repetition restriction if wordList is too short
        int wListIndex;
        String word;
        char[] array;
        
        do { // choose random index from wordList that isn't on repetition list
            wListIndex = (int) (wListLength * Math.random());
        } while (longEnough && usedWords.contains(wListIndex));
        if (longEnough) {
            markUsed(wListIndex);
        }
        
        word = wordList.get(wListIndex);
        array = new char[word.length()];
        for (int index = 0; index < word.length(); index++) { // convert String to char array
            array[index] = word.charAt(index);
        }
        
        return array;
    }
    
    private void markUsed(int index) {
        usedWords.add(0, index);
        if (usedWords.size() > memSize) {
            usedWords.remove(memSize);
        }
    }
}
