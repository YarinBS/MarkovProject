import java.util.Random;

public class MarkovZero extends AbstractMarkovModel { /** subclass of AbstractMarkovModel, contains the MarkovZero arguments and methods */
    private String myText;
	private Random myRandom;
	
	public MarkovZero() { /** Class Constructor */
		super(0);
		myRandom = new Random();
	}

	public void setSeed(int seed){
		/** @param seed - a number that sets myRandom */
		myRandom = new Random(seed);
	}

	@Override
	public String toString() {
		/**
		 * @return a String containing the order of the Markov object
		 */
		return "MarkovModel of order " + this.orderOfMarkov;
	}

	@Override
	public void setTraining(String s){
		/** removes whitepaces from the String 's'
		 * @param s - a text from the given file
		 */
		myText = s.trim();
	}

	@Override
	public String getRandomText(int numChars){
		/** given an integer 'numChars', returns a String of text with numChars characters
		 * @param numChars - set the number of characters that will be printed in each paragraph
		 * @return a String containing the scrambled text
		 */

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