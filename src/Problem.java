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
    private int[] startPoint;
    private int[] endPoint;

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
