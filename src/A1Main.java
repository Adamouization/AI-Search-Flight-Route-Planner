import java.util.Arrays;

public class A1Main {

    public static void main(String[] args) {

        // Declare and initialise variables.
        int N = 0;
        String searchAlgorithm = "";
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];

        // Parse command line arguments.
        if (args.length == 4) {

            // Type of search algorithm to use.
            searchAlgorithm = args[0];

            // World size - check that it is a valid Integer.
            try {
                N = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                errorMessage();
            }

            // World size - check that it is a positive Integer.
            if (N < 0) {
                errorMessage();
            }

            // Parse start goal node.
            startPoint[0] = Integer.parseInt(args[2].split(",")[0]);
            startPoint[1] = Integer.parseInt(args[2].split(",")[1]);

            // Parse end goal node.
            startPoint[0] = Integer.parseInt(args[3].split(",")[0]);
            startPoint[1] = Integer.parseInt(args[3].split(",")[1]);

        }
        // Invalid number of arguments
        else {
            errorMessage();
        }

        // Debugging: print command line arguments.
         printArguments(searchAlgorithm, N, startPoint, endPoint);

        // todo - step 2: save into a data structure called [Problem]

        // todo - step 3: represent world

        // todo - step 4: initialise search algorithm with start/goal and algorithm

        // todo - step 5: start the search
    }

    /**
     * Prints an error message to the command line and terminates the program with an error code.
     */
    public static void errorMessage() {
        System.err.println("usage: java A1main <DFS|BFS|AStar|BestF|...> <N> <d_s,angle_s> <d_g,angle_g> [params]");
        System.exit(1); // System exit with an error code.
    }

    /**
     * Prints the command line arguments passed to the program. Used for debugging purposes.
     *
     * @param searchAlgorithm:
     * @param N:
     * @param startPoint:
     * @param endPoint:
     */
    public static void printArguments(String searchAlgorithm, int N, int[] startPoint, int[] endPoint) {
        System.out.println("Type of search algorithm: " + searchAlgorithm);
        System.out.println("Size of world N: " + N);
        System.out.println("Start point: " + Arrays.toString(startPoint));
        System.out.println("End point: " + Arrays.toString(endPoint));
    }

}
