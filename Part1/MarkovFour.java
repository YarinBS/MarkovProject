import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    //1.5
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        int index = myRandom.nextInt(myText.length() - 4);  // change -1 to markovNumber
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(index, index + 4));
        for (int i = 0; i < numChars; i++) {
            ArrayList<String> char_list = getFollows(sb.substring(i, i + 4));
            index = myRandom.nextInt(char_list.size());
            sb.append(char_list.get(index));
        }
        return sb.toString();
    }

    // 1.3
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> chars_list = new ArrayList<String>();
        for (int i = 0; i < myText.length() - 4; i++) {
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