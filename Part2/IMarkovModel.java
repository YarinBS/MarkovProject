public interface IMarkovModel {
    public void setTraining(String text); // string manipulations

    public String getRandomText(int numChars);

    void setSeed(int seed);
}
