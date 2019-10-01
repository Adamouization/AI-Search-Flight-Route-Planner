import java.util.Arrays;

public class Problem {

    private int N;
    private String searchAlgorithm;
    private int[] startPoint = new int[2];
    private int[] endPoint = new int[2];

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

    public int getN() {
        return this.N;
    }

    public String getSearchAlgorithm() {
        return this.searchAlgorithm;
    }

    public int[] getStartPoint() {
        return startPoint;
    }

    public int[] getEndPoint() {
        return endPoint;
    }

    public void setSearchAlgorithm(String searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void setN(int N) {
        this.N = N;
    }

    public void setStartPoint(int[] startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(int[] endPoint) {
        this.endPoint = endPoint;
    }
}
