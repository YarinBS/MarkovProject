import util.SEFileUtil;

public class MarkovRunnerWithInterfaceEfficient {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setSeed(seed);
        System.out.println("running with " + markov.toString());
        markov.setTraining(text);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    private void printOut(String s) {
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



