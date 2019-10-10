import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * A* Search implementation. Extends the InformedSearch abstract class. Uses PriorityQueues to represent the Frontier.
 *
 * @author adam jaamour (agj6)
 *
 */
public class AStar extends InformedSearch {

    /**
     * Finds the valid moves from the current State, and creates new children Nodes to add to the Frontier if they
     * aren't in the Frontier yet and if they haven't been visited yet.
     *
     * @param node The current Node to expand to find children Nodes.
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @return An ArrayList of Nodes with the children Nodes to add to the Frontier.
     */
    @Override
    public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet) {

        // Local variables.
        ArrayList<Node> successorsSet = new ArrayList<>();
        Node newNode;

        ArrayList<State> nextStates = successor(node.getState(), problem);

        for (State state: nextStates) {

            newNode = makeNode(node, state.getD(), state.getAngle(), problem);

            // If state is in a node in frontier but with a higher path cost, then old node is replaced with new/cheaper one.
            if (isNodeInQueueFrontier(frontier, state)) {
                for (Node n: frontier) {
                    if (newNode.getPathCost() < n.getPathCost()) {
                        frontier.remove(n);
                        successorsSet.add(newNode);
                        break;
                    }
                }
            }
            if (!(isNodeInQueueFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
                newNode = makeNode(node, state.getD(), state.getAngle(), problem);
                successorsSet.add(newNode);
            }
        }
        return successorsSet;
    }

}
