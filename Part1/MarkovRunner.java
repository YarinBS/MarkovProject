import util.*;

public class MarkovRunner {

    public void runMarkovZero(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setTraining(st);
		//markov.setSeed(42);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

    public void runMarkovOne(String trainingFilePath) {

    	SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
		markov.setSeed(42);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

	public void runMarkovFour(String trainingFilePath) {

		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setSeed(25);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovModel(String trainingFilePath) {

		SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
		String st = seFileUtil.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel();
		markov.setTraining(st);
		markov.setSeed(40);
		markov.setNumOfChars(6);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunner markovRunner = new MarkovRunner();
		//MarkovOne markov1 = new MarkovOne();
		//markov1.setSeed(42);
		//markovRunner.runMarkovZero(args[0]);
		//markovRunner.runMarkovOne(args[0]);
		//markovRunner.runMarkovFour(args[0]);
		Tester.testGetFollows();
		markovRunner.runMarkovModel(args[0]);
	}

}
