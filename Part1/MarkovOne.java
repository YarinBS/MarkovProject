import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myText.length(); i++) {
            ArrayList<String> char_list = getFollows(String.valueOf(myText.charAt(i)));
            for (int k = 0; k < char_list.size(); k++) {
                int index = myRandom.nextInt(myText.length());
                sb.append(myText.charAt(index));
            }
        }
        return sb.toString();
    }

    // 1.3
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> chars_list = new ArrayList<String>();
        for (int i = 0; i < myText.length(); i++) {
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