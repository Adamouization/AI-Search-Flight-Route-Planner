public class Search {

    /**
     * Creates a node from a state. Returns a node.
     */
    public static void makeNode(/*Node node, State state*/) {
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
     * Returns a solution, or failure.
     * @param problem
     */
    public static void treeSearch(Problem problem /* Frontier frontier */) {
        System.out.println("treeSearch");
        /*
        initial node ← MAKE-NODE(null,initial state)
        frontier ← INSERT( initial node, frontier)
        explored ← empty set
        loop do
            if frontier is empty return failure
            nd ← REMOVE(index, frontier)
            Add nd to explored
            if GOAL-TEST(STATE[nd], goal)
                return nd
            else
                frontier ← INSERT-ALL (EXPAND(nd, problem, frontier, explored ))
        end loop
        */
    }

    /**
     * Returns a set of nodes.
     * @param problem
     */
    public static void expand(Problem problem /* Node node, Frontier frontier*/) {
        System.out.println("expand");
        /*
        next states ← SUCCESSOR-FN(STATE[node],problem)
        successors ← empty set
            for each state in next states
            if state is not contained in a node of explored or frontier
                nd ← MAKE-NODE(node,state)
                add nd to successors
            end for
        return successors
        */
    }

    /**
     * Returns true if state is a goal state.
     * @return
     */
    public static boolean goalTest(/* State state, State goal */) {
        System.out.println("goalTest");
        return false;
    }

    /**
     * Implements the successor function, i.e. expands a set of new nodes given all actions applicable in the state.
     * @param problem
     */
    public static void successor(/* State node */ Problem problem) {
        System.out.println("successor");
    }

    /**
     * Returns the cost for executing action in state.
     */
    public static int pathCost(/* State state1, State state2 */) {
        int cost = 0;
        System.out.println("successor");
        return cost;
    }

    /**
     * Inserts a new node into the frontier.
     */
    public static void insertFrontierNode(/* Node node, Frontier frontier */) {
        System.out.println("successor");
    }

    /**
     * Returns the ﬁrst node from the frontier.
     * @param index
     */
    public static void removeFrontierNode(int index /*, Frontier frontier */) {
        System.out.println("successor");
    }

}
