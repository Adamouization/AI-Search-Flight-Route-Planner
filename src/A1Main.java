/**
 *
 * Contains the program entry point. Parses the command line arguments.
 *
 * @author adam jaamour (agj6)
 *
 */
public class A1Main {

    /**
     * Program entry point. Parses command line input.
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        // Declare and initialise variables.
        String searchType = "";
        Problem problem = new Problem();

        // Parse command line arguments.
        if (args.length == 4) {

            // Type of search algorithm to use.
            searchType = args[0];

            // World size - check that it is a valid Integer.
            try {
                int N = Integer.parseInt(args[1]);
                // Check that it is a positive Integer.
                if (N > 0) {
                    problem.setN(N);
                }
                else {
                    errorMessage();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                errorMessage();
            }

            // Parse start goal point.
            int[] startPoint = new int[2];
            startPoint[0] = Integer.parseInt(args[2].split(",")[0]);
            startPoint[1] = Integer.parseInt(args[2].split(",")[1]);
            problem.setStartPoint(startPoint);

            // Parse end goal point.
            int[] endPoint = new int[2];
            endPoint[0] = Integer.parseInt(args[3].split(",")[0]);
            endPoint[1] = Integer.parseInt(args[3].split(",")[1]);
            problem.setEndPoint(endPoint);

            // Debugging: print command line arguments.
            System.out.println("Search type: " + searchType);
            problem.printArguments();

        }
        // Invalid number of arguments
        else {
            errorMessage();
        }

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

}
