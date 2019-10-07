import java.util.ArrayList;
import java.util.LinkedList;

abstract class UninformedSearch extends GeneralSearch {

    /**
     * Creates a new Node, linking it to the parent node. Ignores path cost (redundant in uninformed search).
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

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet);

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(LinkedList<Node> frontier);

}
