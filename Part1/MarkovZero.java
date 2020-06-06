import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {
    private String myText;
	private Random myRandom;
	
	public MarkovZero() {
		super(0);
		myRandom = new Random();
	}

	public void printMe() {System.out.println('i');}

	public void setSeed(int seed){
		myRandom = new Random(seed);
	}

	@Override
	public void setTraining(String s){
		myText = s.trim();
	}

	@Override
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}