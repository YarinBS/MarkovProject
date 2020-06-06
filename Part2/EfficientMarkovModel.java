import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private String myText;
    private Random myRandom;
    private HashMap<String, ArrayList<Character>> hashMap;

    public EfficientMarkovModel(int num) {
        super(num);
        this.myRandom = new Random();
        this.hashMap = new <String, ArrayList<Character>>HashMap();
    }

    @Override
    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + this.orderOfMarkov;
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
        this.buildMap();
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

    public void buildMap() {
        ArrayList<Character> chars_list = new ArrayList<Character>();
        for (int i = 0; i < myText.length() - this.orderOfMarkov; i++) {
            String key = myText.substring(i, i + this.orderOfMarkov);
            if (this.hashMap.containsKey(key)) {
                // if the key has already been used,
                // we'll just grab the array list and add the value to it
                chars_list = this.hashMap.get(key);
                if (i + key.length() - 1 != myText.length() - 1) {
                    chars_list.add(myText.charAt(i + key.length()));
                }
            } else {
                // if the key hasn't been used yet,
                // we'll create a new ArrayList<String> object, add the value
                // and put it in the array list with the new key
                chars_list = new ArrayList<Character>();
                if (i + key.length() - 1 != myText.length() - 1) {
                    chars_list.add(myText.charAt(i + key.length()));
                }
                this.hashMap.put(key, chars_list);
            }
        }
    }




    // 1.3
    public ArrayList<Character> getFollows(String key) {
        return this.hashMap.get(key);
    }
}