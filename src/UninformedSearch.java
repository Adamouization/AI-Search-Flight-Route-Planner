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
     * Creates a new Node, linking it to the parent node. Ignores path cost (redundant in uninformed search). Uses
     * LinkedLists to represent the Frontier.
     *
     * @param curNode The current Node.
     * @param d The current node's parallel.
     * @param angle The current node's meridian.
     * @return The new created Node.
     * @throws NullPointerException When dealing with the root node, which has no parent node.
     */
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

        return node;
    }

    /**
     * Checks that a node is in the Frontier.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param state The State to check that is in the Frontier.
     * @return A Boolean indicating if the Node is already in the Frontier.
     */
    public boolean isNodeInListFrontier(LinkedList<Node> frontier, State state) {
        for (Node n: frontier) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet);

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(LinkedList<Node> frontier);

}
