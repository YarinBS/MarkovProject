import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private String myText;
    private Random myRandom;

    public MarkovModel(int num) {
        super(num);
        myRandom = new Random();
    }

    @Override
    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + this.orderOfMarkov;
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length() - this.orderOfMarkov);
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(index, index + this.orderOfMarkov));
        for (int i = 0; i < numChars - this.orderOfMarkov; i++) {
            ArrayList<Character> char_list = getFollows(sb.substring(sb.length() - this.orderOfMarkov, sb.length()));
            if (char_list.size() != 0) {
                index = myRandom.nextInt(char_list.size());
                sb.append(char_list.get(index));
            }
        }
        return sb.toString();
    }

    // 1.3
    public ArrayList<Character> getFollows(String key) {
        ArrayList<Character> chars_list = new ArrayList<Character>();
        for (int i = 0; i < myText.length() - this.orderOfMarkov; i++) {
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