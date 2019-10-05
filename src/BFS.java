/**
 *
 * Breadth-First Search implementation.
 *
 * @author adam jaamour (agj6)
 *
 * 
 */
public class BFS extends Search {

    /**
     * Returns a set of nodes.
     *
     * @param problem
     */
    @Override
    public void expand(Problem problem) {
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

    @Override
    public void treeSearch(Problem problem) {
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
     * Inserts a new node into the frontier.
     */
    @Override
    public void insertFrontierNode() {
        System.out.println("insertFrontierNode");
    }

    /**
     * Removes a node from the frontier.
     *
     * @param index
     */
    @Override
    public void removeFrontierNode(int index) {
        System.out.println("removeFrontierNode");
    }

}
