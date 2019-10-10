import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
     *
     * @param args command line arguments.
     * @throws NumberFormatException if an invalid input is passed for the world size.
     */
    public static void main(String[] args) {

        // Declare and initialise variables.
        long startTime, endTime;
        double runTime;
        Problem problem = new Problem();
        LinkedList<LinkedList<State>> world;
        LinkedList<Node> uninformedSearchFrontier;
        PriorityQueue<Node> informedSearchFrontier;
        ArrayList<State> obstacles = new ArrayList<>();

        // Parse command line arguments.
        if (args.length >= 4) {

            // Type of search algorithm to use.
            problem.setSearchType(args[0]);

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

            // If the start point is located at the pole, then terminate program.
            if (problem.getStartPoint().getAngle() == 0 && problem.getStartPoint().getD() == 0) {
                System.err.println("invalid start located at pole (0,0) - flight is grounded");
                Helper.errorMessage();
            }

            // If the goal point is located at the pole, then terminate program.
            if (problem.getEndPoint().getAngle() == 0 && problem.getEndPoint().getD() == 0) {
                System.err.println("invalid goal located at pole (0,0) - flight cannot reach it");
                Helper.errorMessage();
            }

            // Parse user-selected obstacles (can accept any number).
            if (args.length - 4 > 0) {
                for (int i = 4; i < args.length; i++) {
                    obstacles.add(
                            new State(
                                Integer.parseInt(args[i].split(",")[0]),
                                Helper.getPointIndex(Integer.parseInt(args[i].split(",")[1])),
                                Integer.parseInt(args[i].split(",")[1]),
                                'X'
                            )
                    );
                }
            }

            // Debugging: print command line arguments.
            System.out.println("Search type: " + problem.getSearchType());
            problem.printArguments();
            System.out.println("Obstacles located at: " + obstacles.toString() + "\n");

        }
        // Invalid number of arguments.
        else {
            Helper.errorMessage();
        }

        // Represent the world, made up of N parallels, each made up of 8 meridians/angles.
        world = new LinkedList<>();
        LinkedList<State> parallel = new LinkedList<>();


        // Add the other parallels.
        for (int i = 0; i < problem.getN(); i++) {
            parallel = new LinkedList<>();
            int index = 0;
            for (int j = 0; j <= 315; j += 45) {
                parallel.add(new State(i , index, j, 'E'));
                index++;
            }
            world.add(parallel);
        }

        // Add Start, Goal and Pole points on the world.
        world.get(0).get(0).setStatus('P');
        world.get(problem.getStartPoint().getD()).get(problem.getStartPoint().getIndex()).setStatus('S');
        world.get(problem.getEndPoint().getD()).get(problem.getEndPoint().getIndex()).setStatus('G');

        // Add obstacles on the world.
        for (State o: obstacles) {
            world.get(o.getD()).get(o.getIndex()).setStatus('X');
        }

        // Print the entire world.
        //Helper.printWorld(world);

        // Start measuring time.
        startTime = System.nanoTime();

        // Start the search.
        Node currentNode;
        switch (problem.getSearchType()) {
            case "BFS":
                System.out.println("Starting Breadth-First Search...");
                uninformedSearchFrontier = new LinkedList<>();
                BFS bfs = new BFS();
                currentNode = bfs.treeSearch(problem, uninformedSearchFrontier, obstacles);
                endTime = System.nanoTime();
                runTime = (double) TimeUnit.NANOSECONDS.toMicros(endTime - startTime) / 1000;
                bfs.printSolution(currentNode, problem, runTime);
                break;
            case "DFS":
                System.out.println("Starting Depth-First Search...");
                uninformedSearchFrontier = new LinkedList<>();
                DFS dfs = new DFS();
                currentNode = dfs.treeSearch(problem, uninformedSearchFrontier, obstacles);
                endTime = System.nanoTime();
                runTime = (double) TimeUnit.NANOSECONDS.toMicros(endTime - startTime) / 1000;
                dfs.printSolution(currentNode, problem, runTime);
                break;
            case "BestF":
                System.out.println("Starting Best-First Search...");
                informedSearchFrontier = new PriorityQueue<>();
                BestF bestF = new BestF();
                currentNode = bestF.treeSearch(problem, informedSearchFrontier, obstacles);
                endTime = System.nanoTime();
                runTime = (double) TimeUnit.NANOSECONDS.toMicros(endTime - startTime) / 1000;
                bestF.printSolution(currentNode, problem, runTime);
                break;
            case "AStar":
                System.out.println("Starting A* Search...");
                informedSearchFrontier = new PriorityQueue<>();
                AStar aStar = new AStar();
                currentNode = aStar.treeSearch(problem, informedSearchFrontier, obstacles);
                endTime = System.nanoTime();
                runTime = (double) TimeUnit.NANOSECONDS.toMicros(endTime - startTime) / 1000;
                aStar.printSolution(currentNode, problem, runTime);
                break;
        }

    }

}
