import java.util.LinkedList;

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
        LinkedList<LinkedList<State>> world;
        LinkedList<Node> frontier = new LinkedList<>();

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
                    Helper.errorMessage();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Helper.errorMessage();
            }

            // Parse and add start point to problem.
            problem.setStartPoint(
                    new State(
                            Integer.parseInt(args[2].split(",")[0]),
                            Helper.getPointIndex(Integer.parseInt(args[2].split(",")[1])),
                            Integer.parseInt(args[2].split(",")[1]),
                            'S'
                    )
            );

            // Parse and add goal point to problem.
            problem.setEndPoint(
                    new State(
                            Integer.parseInt(args[3].split(",")[0]),
                            Helper.getPointIndex(Integer.parseInt(args[3].split(",")[1])),
                            Integer.parseInt(args[3].split(",")[1]),
                            'G'
                    )
            );

            // If the goal point is located at the pole, then terminate program.
            if (problem.getStartPoint().getD() == 0 || problem.getEndPoint().getD() == 0) {
                System.err.println("invalid goal located at pole (0,0)");
                Helper.errorMessage();
            }

            // Debugging: print command line arguments.
            System.out.println("Search type: " + searchType);
            problem.printArguments();

        }
        // Invalid number of arguments.
        else {
            Helper.errorMessage();
        }

        // Represent the world, made up of N parallels, each made up of 8 meridians/angles.
        world = new LinkedList<>();
        LinkedList<State> parallel = new LinkedList<>();

        // Add the pole.
        parallel.add(new State(0, 0, 0, 'P'));
        world.add(parallel);

        // Add the other parallels.
        for (int i = 1; i < problem.getN(); i++) {
            parallel = new LinkedList<>();
            int index = 0;
            for (int j = 0; j <= 315; j += 45) {
                parallel.add(new State(i , index, j, 'E'));
                index++;
            }
            world.add(parallel);
        }

        // Add Start and Goal points on the world.
        world.get(problem.getStartPoint().getD()).get(problem.getStartPoint().getIndex()).setStatus('S');
        world.get(problem.getEndPoint().getD()).get(problem.getEndPoint().getIndex()).setStatus('G');

        // Print the entire world.
        //Helper.printWorld(world);

        // Start the search.
        BFS bfs = new BFS();
        Node currentNode = bfs.treeSearch(problem, frontier);

        // Print out the solution.
        System.out.println("Flight instructions: todo");
        System.out.println("\nCurrent node: " + currentNode);
        System.out.println("Path followed: " + bfs.findSolutionPath(currentNode).toString());
        System.out.println(bfs.getExploredSet().size() + " states expanded: " + bfs.getExploredSet().toString());
    }

    public LinkedList<LinkedList<State>> moveAircraft(LinkedList<LinkedList<State>> world, char direction) {
        switch (direction) {
            case 'N':
                // Move towards centre (N -> N-1).
                break;
            case 'S':
                // Move away from centre (N -> N+1).
                break;
            case 'E':
                // Move clockwise (-45 degrees).
                break;
            case 'W':
                // Move anti-clockwise (+45 degrees).
                break;
        }
        return world;
    }

}
