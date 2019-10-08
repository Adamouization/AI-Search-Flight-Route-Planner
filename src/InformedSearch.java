import java.util.ArrayList;
import java.util.PriorityQueue;

abstract class InformedSearch extends GeneralSearch {

    /**
     * Creates a new Node, linking it to the parent node. Takes path cost into account to perform informed search. Uses
     * PriorityQueue to represent the Frontier.
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

        // Calculate and set path cost.
        try {
            node.setPathCost(curNode.getPathCost() + calculatePathCost(curNode.getState(), curNode.getParentNode().getState()));
        }
        catch (NullPointerException e)  {
            node.setPathCost(0.0);
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
     * Checks that a node is in the Frontier.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param state The State to check that is in the Frontier.
     * @return A Boolean indicating if the Node is already in the Frontier.
     */
    public boolean isNodeInQueueFrontier(PriorityQueue<Node> frontier, State state) {
        for (Node n: frontier) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the path cost from one State to another.
     *
     * @param state1 State going to.
     * @param state2 State coming from.
     * @return The path cost in the form of a Double.
     */
    private double calculatePathCost(State state1, State state2) {
        // Same parallel.
        if (state1.getD() == state2.getD()) {
            return (2 * Math.PI * state1.getD()) / 8;
        }
        // Different parallel.
        else {
            return 1.0;
        }
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public Node treeSearch(Problem problem, PriorityQueue<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet);

    abstract public PriorityQueue<Node> insertFrontierNodes(PriorityQueue<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(PriorityQueue<Node> frontier);

}
