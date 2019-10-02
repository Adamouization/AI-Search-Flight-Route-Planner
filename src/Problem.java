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
    private Point startPoint;
    private Point endPoint;

    /**
     * Prints the command line arguments passed to the program. Used for debugging purposes.
     */
    public void printArguments() {
        System.out.println("Size of world N: " + N);
        System.out.println("Start point: " + startPoint);
        System.out.println("End point: " + endPoint);
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
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * @return The goal point of the search.
     */
    public Point getEndPoint() {
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
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @param endPoint The goal point of the search.
     */
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
