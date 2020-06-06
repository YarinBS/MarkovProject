//1.4
public class Tester {
    public static void testGetFollows() {
        MarkovOne markov1 = new MarkovOne();
        markov1.setTraining("this is a test yes this is a test.");
        System.out.println(markov1.getFollows(String.valueOf('.')));
        System.out.println(markov1.getFollows(String.valueOf('t')));
    }
}
