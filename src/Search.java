import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Abstract method working as a template for specific search algorithms. Contains methods common to all search
 * algorithms, and methods specific to each search algorithm which are developed in their respective classes.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class Search {

    // Declare and initialise variables.
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

    /*
    public int pathCost(State state1, State state2) {
        int cost = 0;
        System.out.println("pathCost");
        return cost;
    }
    */

    /**
     * Creates a new Node, linking it to the parent node.
     *
     * @param curNode The current Node.
     * @param d The current node's parallel.
     * @param angle The current node's meridian.
     * @return The new created Node.
     */
    public Node makeNode(Node curNode, int d, int angle) {
        Node node = new Node();

        // Set node state.
        node.setState( new State(d, Helper.getPointIndex(angle), angle, ' ') );

        // Set node predecessor.
        try {
            node.setParentNode(curNode);
        }
        catch (NullPointerException e) {
            node.setParentNode(null);
        }

        // Determine and set node's action.
        if (node.getParentNode() != null) {
            node.setAction(Helper.getActionFromState(node));
        }

        // Set node depth.
        try {
            node.setDepth(curNode.getDepth() + 1);
        }
        catch (NullPointerException e) {
            node.setDepth(0);
        }

        return node;
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

    abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet);

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(LinkedList<Node> frontier);

    abstract public boolean isNodeInFrontier(LinkedList<Node> frontier, State state);

    abstract public boolean isNodeInExploredSet(ArrayList<Node> exploredSet, State state);
}
