import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    /**
     * contains the Markov one arguments and methods
     */
    private String myText;
    private Random myRandom;

    public MarkovOne() {  /** builder */
        super(1);
        myRandom = new Random();
    }

    @Override
    public void setSeed(int seed) {
        /** @param seed - a number that sets myRandom */
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + this.orderOfMarkov;
    }

    @Override
    public void setTraining(String s) {
        /** string manipulations
         @param seed - a number that sets myRandom */
        myText = s.trim();
    }

    @Override
    public String getRandomText(int numChars) {
        /** given an integer 'numChars', returns a String of text with numChars characters
         * @param numChars - set the number of characters that will be printed in each paragraph
         * @return a String containing the scrambled text*/
        if (this.myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length() - 1);  // change -1 to markovNumber
        StringBuilder sb = new StringBuilder();
        sb.append(myText.charAt(index));
        for (int i = 0; i < numChars - 1; i++) {
            ArrayList<Character> char_list = getFollows(String.valueOf(sb.charAt(i)));
            index = myRandom.nextInt(char_list.size());
            sb.append(char_list.get(index));
        }
        return sb.toString();
    }

    // 1.3
    public ArrayList<Character> getFollows(String key) {
        /** given a String 'key', return an ArrayList containing all charaters that follow the given String 'key'
         * @param key - a character or substring from the text
         * @return an ArrayList containing all chars that follow the given key
         * */
        ArrayList<Character> chars_list = new ArrayList<Character>();
        for (int i = 0; i < myText.length(); i++) {
            if (myText.substring(i, i + key.length()).equals(key)) {
                if (i + key.length() - 1 != myText.length() - 1) {
                    char follow_char = myText.charAt(i + key.length());
                    chars_list.add(follow_char);
                }
            }
        }
        return chars_list;
    }
}
