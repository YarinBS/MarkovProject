import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    /** shape holder for a Makarov extenders */
    protected String myText;
    protected Random myRandom;
    public int orderOfMarkov;
    
    public AbstractMarkovModel(int orderOfMarkov) {
        /**@param orderOfMarkov- holds the number of chars to set random by */
        myRandom = new Random();
        this.orderOfMarkov = orderOfMarkov;
    }

    public void setSeed(int seed) { myRandom = new Random(seed); }

    
    public void setTraining(String s) {
        /** given string removes white spaces
         * @param s- holds the text   */
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    /** given an integer 'numChars', returns a String of text with numChars character*/


    protected ArrayList<Character> getFollows(String key) {
        /** given a String 'key', return an ArrayList containing all charaters that follow the given String 'key'
         * @param key - a character or substring from the text
         * @return an ArrayList containing all chars that follow the given key
         * */
        ArrayList<Character> chars_list = new ArrayList<Character>();
        for (int i = 0; i < myText.length() - 4; i++) {
            if (myText.substring(i, i + key.length()).equals(key)) {
                if (i + key.length() - 1 != myText.length() - 1) {
                    char follow_char = Character.valueOf(myText.charAt(i + key.length()));
                    chars_list.add(follow_char);
                }
            }
        }
        return chars_list;
    }

    public String toString(int orderOfMarkov) {
        return "MarkovModel of order " + orderOfMarkov;
    }

}
