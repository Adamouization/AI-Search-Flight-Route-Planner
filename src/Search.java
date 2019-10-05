import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Search methods.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class Search {

    public ArrayList<State> successor(State state, Problem problem) {
        ArrayList<State> childrenNodes = new ArrayList<>();
        // find all children nodes - try the 4 directions
        return childrenNodes;
    }

    public boolean goalTest(Node curPoint, State endPoint) {
        if (curPoint.equals(endPoint)) {
            return true;
        }
        return false;
    }

    public int pathCost(/* State state1, State state2 */) {
        int cost = 0;
        System.out.println("pathCost");
        return cost;
    }

    public Node makeNode(Node curNode, int d, int angle) {
        Node node = new Node();
        node.setState(
                new State(
                        d,
                        Helper.getPointIndex(angle),
                        angle,
                        ' '
                )
        );

        try {
            node.setParentNode(curNode);
        }
        catch (NullPointerException e) {
            node.setParentNode(null);
            System.err.println("curNode is null");
        }

        //ACTION[n] ← move from STATE[node] to state
        node.setDepth(curNode.getDepth() + 1);
        return node;
    }

    abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier);

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(LinkedList<Node> frontier);

}
