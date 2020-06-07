import util.SEFileUtil;

public class MarkovRunnerWithInterfaceEfficient {
    /** The class is use to generate random text */
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        /**
         * @param markov - a Markov object instance from the current run
         * @param text - text from the given file
         * @param size - the number of characters that will be printed in each paragraph
         * @param seed - a number that sets myRandom
         * */
        markov.setSeed(seed);
        System.out.println("running with " + markov.toString());
        markov.setTraining(text);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    private void printOut(String s) {
        /** prints the scrambled text that was created with two separating lines between paragraphs
         * @param s - text from the given file
         * */
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap(String trainingFilePath, int seed) {
        /** tests the credibility of the hashing process
         * @param trainingFilePath - local path to text file
         * @param seed - a number that sets myRandom
         * */
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        EfficientMarkovModel emFive = new EfficientMarkovModel(5);
        runModel(emFive, st, size, seed);
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Please pass two arguments: 1.input_file 2.seed");
            System.exit(1);
        }
        MarkovRunnerWithInterfaceEfficient markov = new MarkovRunnerWithInterfaceEfficient();
        try {
            markov.testHashMap(args[0], Integer.parseInt(args[1]));
        } catch (NumberFormatException exc) {
            System.out.println("The second argument must be an integer");
            System.exit(1);
        }
    }
}



