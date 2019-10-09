import java.util.ArrayList;

/**
 *
 * Abstract method working as a template for different types of search algorithms (uninformed and informed). Contains
 * general methods common to all search algorithms, and methods specific to both types of search algorithm which are
 * developed in their respective classes.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class GeneralSearch {

    // Declare and initialise variables.
    int nodesCreated = 0;
    private ArrayList<Node> exploredSet = new ArrayList<>(); // List of explored nodes.

    /**
     * Determines the next valid move that can be made from the current Node's state. Checks that the agent can move
     * North, South, East and West from the current node's state.
     *
     * @param state The state of the current node.
     * @param problem The problem structure.
     * @return A set of valid states that can be expanded next.
     */
    public ArrayList<State> successor(State state, Problem problem) {
        ArrayList<State> childrenNodes = new ArrayList<>();

        // Move East (H90). If angle = 315 degrees, loop back to 0 degrees.
        if (state.getAngle() == 315) {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            0,
                            0,
                            state.getStatus()
                    )
            );
        }
        else {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            state.getIndex() + 1,
                            state.getAngle() + 45,
                            state.getStatus()
                    )
            );
        }

        // Move West (H270). If angle = 0 degrees, loop back to 315 degrees.
        if (state.getAngle() == 0) {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            7,
                            315,
                            state.getStatus()
                    )
            );
        }
        else {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            state.getIndex() - 1,
                            state.getAngle() - 45,
                            state.getStatus()
                    )
            );
        }

        // Move North (H360), but cannot go to pole (N=0).
        if ( !(state.getD() == 1) ) {
            childrenNodes.add(
                    new State(
                            state.getD() - 1,
                            state.getIndex(),
                            state.getAngle(),
                            state.getStatus()
                    )
            );
        }

        // Move South (H180), but cannot go beyond last parallel N.
        if ( !(state.getD() == problem.getN() - 1) ) {
            childrenNodes.add(
                    new State(
                            state.getD() + 1,
                            state.getIndex(),
                            state.getAngle(),
                            state.getStatus()
                    )
            );
        }

        // Return valid moves.
        return childrenNodes;
    }

    /**
     * Test to check that the current node's state is the goal state.
     *
     * @param curPoint The current node.
     * @param endPoint The goal state.
     * @return A boolean indicating whether the goal state has been reached.
     */
    public boolean goalTest(Node curPoint, State endPoint) {
        return curPoint.getState().getD() == endPoint.getD() && curPoint.getState().getAngle() == endPoint.getAngle();
    }

    /**
     * Checks that a node is in the set of explored Nodes.
     *
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @param state The State to check that is in the exploredSet.
     * @return A Boolean indicating if the Node is already in the set of explored Nodes.
     */
    public boolean isNodeInExploredSet(ArrayList<Node> exploredSet, State state) {
        for (Node n: exploredSet) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the states followed to get from the start State to the goal State to an ordered ArrayList of Strings.
     *
     * @param goalNode: the goal Node.
     * @return The ArrayList of Strings representing the states followed from start to goal State.
     */
    public ArrayList<String> findSolutionPath(Node goalNode) {
        ArrayList<String> solutionPath = new ArrayList<>();
        Node n = goalNode;
        solutionPath.add(n.getState().toString());
        while (n.getParentNode() != null) {
            solutionPath.add(0, n.getParentNode().toString());
            n = n.getParentNode();
        }
        return solutionPath;
    }

    /**
     * Adds the flight instructions followed to get from the start State to the goal State to an ordered ArrayList of
     * Strings.
     *
     * @param goalNode: the goal Node.
     * @return The ArrayList of Strings representing the states followed from start to goal State.
     * @see Helper#getActionFromState(Node)
     */
    public ArrayList<String> findFlightInstructions(Node goalNode) {
        ArrayList<String> flightInstruction = new ArrayList<>();
        Node n = goalNode;
        flightInstruction.add(n.getAction());
        while (n.getParentNode() != null) {
            flightInstruction.add(0, n.getAction());
            n = n.getParentNode();
        }
        flightInstruction.remove(flightInstruction.size() - 1); // Remove last element, invalid action.
        return flightInstruction;
    }

    /**
     * Calculates the cost of the path found from start to goal States.
     *
     * @param curNode: the current Node with Goal state.
     * @param startPoint: the starting State of the search.
     * @return The ArrayList of Strings representing the states followed from start to goal State.
     */
    public double findSolutionPathCost(Node curNode, State startPoint) {
        double pathCost = 0.0;
        Node n1, n2;
        n1 = curNode;
        n2 = n1.getParentNode();
        while (n1.getState().getD() != startPoint.getD() && n1.getState().getAngle() != startPoint.getAngle()) {
            if (n1.getState().getD() == n2.getState().getD()) {
                pathCost += (2 * Math.PI * n1.getState().getD()) / 8;
            }
            else {
                pathCost += 1.0;
            }
            n1 = n2;
            n2 = n1.getParentNode();
        }
        return pathCost;
    }

    /**
     * Prints the solution to the command line, including the complete flight instructions, the current Node, the path
     * followed to get from the start State to the goal State, and the States explored during the search.
     *
     * @param currentNode: the current Node, which matches the goal Node.
     */
    public void printSolution(Node currentNode, Problem problem, String searchType, double runTime) {
        System.out.println("\nPath found using " + searchType + " in " + runTime + " ms!");
        System.out.println("Flight instructions: " + findFlightInstructions(currentNode).toString());
        ArrayList<String> solutionPath = findSolutionPath(currentNode);
        System.out.println("Path followed (" + solutionPath.size() + "): " + solutionPath.toString());
        System.out.println("Solution path cost: " + findSolutionPathCost(currentNode, problem.getStartPoint()));
        System.out.println("\nNodes created: " + nodesCreated);
        System.out.println(getExploredSet().size() + "/" + ((problem.getN() - 1) * 8) + " states expanded: " + getExploredSet().toString());
    }

    /* Getters & Setters ******************************************************************************************** */

    /**
     * @return An ArrayList of Nodes containing all the explored Nodes.
     */
    public ArrayList<Node> getExploredSet() {
        return exploredSet;
    }

    /**
     * @param exploredSet An ArrayList of Nodes containing all the explored Nodes.
     */
    public void setExploredSet(ArrayList<Node> exploredSet) {
        this.exploredSet = exploredSet;
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public Node makeNode(Node curNode, int d, int angle, Problem problem);

}
