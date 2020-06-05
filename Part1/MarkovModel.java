import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int numOfChars;

    public MarkovModel() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setNumOfChars(int n) { numOfChars = n;}

    //1.5
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length() - numOfChars);
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(index, index + numOfChars));
        for (int i = 0; i < numChars; i++) {
            ArrayList<String> char_list = getFollows(sb.substring(i, i + numOfChars));
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
