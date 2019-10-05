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
        System.out.println("successor");
        return cost;
    }

    public void makeNode() {
        System.out.println("makeNode");
        /*
        n ← a new Node
        STATE[n] ← state
        PARENT-NODE[n] ← node
        ACTION[n] ← move from STATE[node] to state
        PATH-COST[n] ← PATH-COST[node] + COST(STATE[node],state)
        DEPTH[n] ← DEPTH[node] +1
        return n
        */
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
