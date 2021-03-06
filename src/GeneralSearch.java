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
    int iteration = 0; // Count number of search loops.
    private ArrayList<Node> exploredSet = new ArrayList<>(); // List of explored nodes.

    /**
     * Determines the next valid move that can be made from the current Node's state. Checks that the agent can move
     * North, South, East and West from the current node's state. Avoids the pole and obstacles.
     *
     * @param state The state of the current node.
     * @param problem The problem structure.
     * @param obstacles An ArrayList with obstacles present on the world.
     * @return A set of valid states that can be expanded next.
     */
    public ArrayList<State> successor(State state, Problem problem, ArrayList<State> obstacles) {
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
        if ( !(state.getD() == 0) ) {
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

        // Cannot go to pole (0, 0).
        for (State s: childrenNodes) {
            if (s.getD() == 0 && s.getAngle() == 0) {
                childrenNodes.remove(s);
                break;
            }
        }

        // Cannot go to obstacles.
        for (State a: obstacles) {
            for (State s: childrenNodes) {
                if (s.getD() == a.getD() && s.getD() == a.getD()) {
                    childrenNodes.remove(s);
                    break;
                }
            }
        }

        // Return valid moves.
        return childrenNodes;
    }

    /**
     * Determines the action based on the current Node's State and its parent Node's State.
     *
     * @param node The current Node.
     * @return The action used to move from the parent Node's State to the current Node's State.
     */
    public String getActionFromState(Node node) {
        // Same parallel, so different meridian (either E or W).
        if (node.getState().getD() == node.getParentNode().getState().getD()) {
            // Current node angle is bigger than parent node angle, so moved E.
            if (node.getState().getAngle() - node.getParentNode().getState().getAngle() > 0) {
                // Take care of special case looping from 0 to 315 degrees, which is going Wget.
                if (node.getState().getAngle() == 315 && node.getParentNode().getState().getAngle() == 0) {
                    return "H270";
                }
                return "H90";
            }
            // Current node angle is smaller than parent node angle, so moved W.
            else {
                // Take care of special case looping from 315 to 0 degrees, which is going E.
                if (node.getState().getAngle() == 0 && node.getParentNode().getState().getAngle() == 315) {
                    return "H90";
                }
                return "H270";
            }
        }
        // Different parallel (either N or S).
        else {
            // Current node parallel is bigger than parent node parallel, so moved S.
            if (node.getState().getD() - node.getParentNode().getState().getD() > 0) {
                return "H180";
            }
            // Current node parallel is smaller than parent node parallel, so moved N.
            else {
                return "H360";
            }
        }
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
     * @param explored An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @param state The State to check that is in the exploredSet.
     * @return A Boolean indicating if the Node is already in the set of explored Nodes.
     */
    public boolean isNodeInExploredSet(ArrayList<Node> explored, State state) {
        for (Node n: explored) {
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
     * @see #getActionFromState(Node)
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
     * Calculates the cost of the path from start to goal States.
     *
     * @param curNode: the current Node with Goal state.
     * @return The cost from the current Node to the root Node.
     */
    public double findSolutionPathCost(Node curNode) {
        double pathCost = 0.0;
        Node n = curNode;
        while (n.getParentNode() != null) {
            if (n.getState().getD() == n.getParentNode().getState().getD()) {
                pathCost += (2 * Math.PI * n.getState().getD()) / 8;
            }
            else {
                pathCost += 1.0;
            }
            n = n.getParentNode();
        }
        return pathCost;
    }

    /**
     * Prints the solution to the command line, including the complete flight instructions, the current Node, the path
     * followed to get from the start State to the goal State, and the States explored during the search.
     *
     * @param currentNode The current Node, which matches the goal Node.
     * @param problem The problem structure.
     * @param runTime the search runtime in ms (milliseconds).
     */
    public void printSolution(Node currentNode, Problem problem, double runTime) {
        System.out.println("\nPath found using " + problem.getSearchType() + " in " + runTime + " ms!");
        ArrayList<String> flightInstructions = findFlightInstructions(currentNode);
        System.out.println("Flight instructions (" + flightInstructions.size() + ") : " + flightInstructions.toString());
        ArrayList<String> solutionPath = findSolutionPath(currentNode);
        System.out.println("Path followed (" + solutionPath.size() + " states): " + solutionPath.toString());
        double pathCost = Math.round(findSolutionPathCost(currentNode) * 1000.0) / 1000.0;
        System.out.println("Solution path cost: " + pathCost);
        System.out.println("\nCurrent node depth: " + currentNode.getDepth());
        System.out.println("Nodes created: " + nodesCreated);
        System.out.println(getExploredSet().size() + " states expanded: " + getExploredSet().toString());
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
