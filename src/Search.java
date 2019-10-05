/**
 *
 * Search methods.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class Search {

    /**
     * Implements the successor function, i.e. expands a set of new nodes given all actions applicable in the state.
     * @param problem
     */
    public void successor(/* State node */ Problem problem) {
        System.out.println("successor()");
    }

    /**
     * Returns true if state is a goal state.
     * @return
     */
    public boolean goalTest(/* State state, State goal */) {
        System.out.println("goalTest()");
        return false;
    }

    /**
     * Returns the cost for executing action in state.
     */
    public int pathCost(/* State state1, State state2 */) {
        int cost = 0;
        System.out.println("pathCost");
        return cost;
    }

    /**
     * Creates a new Node.
     *
     * @return A new Node.
     */
    public Node makeNode(Node curNode, int d, int angle) {
        Node node = new Node();
        node.setD(d);
        node.setAngle(angle);
        node.setParentNode(curNode);
        //ACTION[n] ← move from STATE[node] to state
        node.setDepth(curNode.getDepth() + 1);
        return node;
    }

    /**
     * Returns a set of nodes.
     * @param problem
     */
    abstract public void expand(Problem problem /* Node node, Frontier frontier*/);

    /**
     * Returns a solution, or failure.
     * @param problem
     */
    abstract public void treeSearch(Problem problem /* Frontier frontier */);

    /**
     * Inserts a new node into the frontier.
     */
    abstract public void insertFrontierNode(/* Node node, Frontier frontier */);

    /**
     * Returns the a node from the frontier.
     * @param index
     */
    abstract public void removeFrontierNode(int index /*, Frontier frontier */);

}
