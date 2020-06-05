//Employee

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    public int orderOfMarkov;
    public int seed;
    
    public AbstractMarkovModel(int orderOfMarkov) {
        myRandom = new Random();
        this.orderOfMarkov = orderOfMarkov;
    }

    public void setSeed(int seed) { myRandom = new Random(seed); }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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

    public String toString(int orderOfMarkov) {
        return "MarkovModel of order " + orderOfMarkov;
    }

}
