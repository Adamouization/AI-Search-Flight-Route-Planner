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
    private State startPoint;
    private State endPoint;

    /**
     * Prints the command line arguments passed to the program. Used for debugging purposes.
     */
    public void printArguments() {
        System.out.println("Size of world N: " + N);
        System.out.println("Start point: " + startPoint);
        System.out.println("End point: " + endPoint);
        System.out.println();
    }

    /**
     * @return The size of the world (number of parallels on the planet).
     */
    public int getN() {
        return this.N;
    }

    /**
     * @return The starting point of the search.
     */
    public State getStartPoint() {
        return startPoint;
    }

    /**
     * @return The goal point of the search.
     */
    public State getEndPoint() {
        return endPoint;
    }

    /**
     * @param N The size of the world (number of parallels on the planet).
     */
    public void setN(int N) {
        this.N = N;
    }

    /**
     * @param startPoint The start point of the search.
     */
    public void setStartPoint(State startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @param endPoint The goal point of the search.
     */
    public void setEndPoint(State endPoint) {
        this.endPoint = endPoint;
    }
}
