import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class MarkovModel extends AbstractMarkovModel {
    private String myText;
    private Random myRandom;
    private int numOfChars;

    public MarkovModel() {
        super(orderOfMarkov);
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setNumOfChars(int n) { numOfChars = n; }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length() - numOfChars);
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(index, index + numOfChars));
        for (int i = 0; i < numChars - numOfChars; i++) {
            ArrayList<String> char_list = getFollows(sb.substring(sb.length() - numOfChars, sb.length()));
            if (char_list.size() != 0) {
                index = myRandom.nextInt(char_list.size());
                sb.append(char_list.get(index));
            }
        }
        return sb.toString();
    }

    // 1.3
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> chars_list = new ArrayList<String>();
        for (int i = 0; i < myText.length() - numOfChars; i++) {
            if (myText.substring(i, i + key.length()).equals(key)) {
                if (i + key.length() - 1 != myText.length() - 1) {
                    String follow_char = String.valueOf(myText.charAt(i + key.length()));
                    chars_list.add(follow_char);
                }
            }
        }
        return chars_list;
    }
}
