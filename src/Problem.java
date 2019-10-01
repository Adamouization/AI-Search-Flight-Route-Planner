import java.util.Arrays;

/**
 *
 * Problem data structure.
 *
 * @author adam jaamour (agj6)
 *
 */
public class Problem {

    // Declare variables.
    private int N;
    private String searchAlgorithm;
    private int[] startPoint = new int[2];
    private int[] endPoint = new int[2];

    /**
     * Class constructor.
     *
     * @param searchAlgorithm The type of search algorithm to use.
     * @param N The size of the world (number of parallels on the planet).
     * @param startPoint The start point of the search.
     * @param endPoint The goal point of the search.
     */
    public Problem(String searchAlgorithm, int N, int[] startPoint, int[] endPoint) {
        this.searchAlgorithm = searchAlgorithm;
        this.N = N;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * Prints the command line arguments passed to the program. Used for debugging purposes.
     */
    public void printArguments() {
        System.out.println("Type of search algorithm: " + searchAlgorithm);
        System.out.println("Size of world N: " + N);
        System.out.println("Start point: " + Arrays.toString(startPoint));
        System.out.println("End point: " + Arrays.toString(endPoint));
    }

    /**
     * @return The size of the world (number of parallels on the planet).
     */
    public int getN() {
        return this.N;
    }

    /**
     * @return The type of search algorithm to use.
     */
    public String getSearchAlgorithm() {
        return this.searchAlgorithm;
    }

    /**
     * @return The starting point of the search.
     */
    public int[] getStartPoint() {
        return startPoint;
    }

    /**
     * @return The goal point of the search.
     */
    public int[] getEndPoint() {
        return endPoint;
    }

    /**
     * @param searchAlgorithm The type of search algorithm to use.
     */
    public void setSearchAlgorithm(String searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    /**
     *
     * @param N The size of the world (number of parallels on the planet).
     */
    public void setN(int N) {
        this.N = N;
    }

    /**
     *
     * @param startPoint The start point of the search.
     */
    public void setStartPoint(int[] startPoint) {
        this.startPoint = startPoint;
    }

    /**
     *
     * @param endPoint The goal point of the search.
     */
    public void setEndPoint(int[] endPoint) {
        this.endPoint = endPoint;
    }
}
