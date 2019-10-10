import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Abstract method working as a template for uninformed search algorithm implementations. Contains methods common to all
 * uninformed search algorithms, and methods specific to each algorithm implementation which are developed in their
 * respective concrete classes.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class UninformedSearch extends GeneralSearch {

    /**
     * The main search method for the BFS algorithm.
     *
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @return The current Node when a goal State is reached.
     */
    public Node treeSearch(Problem problem, LinkedList<Node> frontier) {

        // Local variables used throughout the search.
        Node curNode;
        int iteration = 0; // Count number of loops
        ArrayList<Node> exploredSet = getExploredSet();

        // Create and add the root node to the frontier.
        frontier.add(
                makeNode(null, problem.getStartPoint().getD(), problem.getStartPoint().getAngle(), problem)
        );

        while (!frontier.isEmpty()) {
            curNode = removeFrontierNode(frontier);
            exploredSet.add(curNode);
            if (goalTest(curNode, problem.getEndPoint())) {
                setExploredSet(exploredSet);
                return curNode;
            }
            else {
                insertFrontierNodes(frontier, expand(curNode, problem, frontier, exploredSet));
            }
            iteration++;
            printUninformedSearchStatus(curNode, frontier, exploredSet, iteration);
        }

        return null; // If frontier is empty, return null (no solution found).
    }

    /**
     * Creates a new Node, linking it to the parent node. Ignores path cost (redundant in uninformed search). Uses
     * LinkedLists to represent the Frontier.
     *
     * @param curNode The current Node.
     * @param d The current node's parallel.
     * @param angle The current node's meridian.
     * @return The new created Node.
     * @throws NullPointerException When dealing with the root node, which has no parent node.
     */
    @Override
    public Node makeNode(Node curNode, int d, int angle, Problem problem) {
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

        // No need to setup path cost because performing uninformed search.
        node.setPathCost(0.0);

        // Set node depth.
        try {
            node.setDepth(curNode.getDepth() + 1);
        }
        catch (NullPointerException e) {
            node.setDepth(0);
        }

        this.nodesCreated++;
        return node;
    }

    /* Private Methods  ********************************************************************************************* */

    /**
     * Finds the valid moves from the current State, and creates new children Nodes to add to the Frontier if they
     * aren't in the Frontier yet and if they haven't been visited yet.
     *
     * @param node The current Node to expand to find children Nodes.
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @return An ArrayList of Nodes with the children Nodes to add to the Frontier.
     */
    private ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet) {

        // Local variables.
        ArrayList<Node> successorsSet = new ArrayList<>();
        Node newNode;

        ArrayList<State> nextStates = successor(node.getState(), problem);

        for (State state: nextStates) {
            if (!(isNodeInListFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
                newNode = makeNode(node, state.getD(), state.getAngle(), problem);
                successorsSet.add(newNode);
            }
        }
        return successorsSet;
    }

    /**
     * Checks that a node is in the Frontier.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param state The State to check that is in the Frontier.
     * @return A Boolean indicating if the Node is already in the Frontier.
     */
    private boolean isNodeInListFrontier(LinkedList<Node> frontier, State state) {
        for (Node n: frontier) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pops a Node from the front of the queue.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @return The updated Frontier without the Node being currently explored.
     */
    private Node removeFrontierNode(LinkedList<Node> frontier) {
        return frontier.removeFirst(); // Remove the first node from the queue (FIFO or LIFO).
    }

    /**
     * Prints the current status of the uninformed search algorithm to the command line, including the iteration, the
     * Frontier (LinkedList) and the set of explored states.
     *
     * @param node The current Node being expanded.
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @param iteration The current search loop number.
     */
    private static void printUninformedSearchStatus(Node node, LinkedList<Node> frontier, ArrayList<Node> exploredSet, int iteration) {
        System.out.println("Iteration #" + iteration + " -------------------------");
        System.out.println("Current node: " + node.toString());
        System.out.println("Frontier: " + frontier.toString());
        System.out.println("Explored States: " + exploredSet.toString());
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

}
