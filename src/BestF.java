import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * Best-First Search implementation. Extends the InformedSearch abstract class. Uses PriorityQueues to represent the
 * Frontier.
 *
 * @author adam jaamour (agj6)
 *
 */
public class BestF extends InformedSearch {

    /**
     * Finds the valid moves from the current State, and creates new children Nodes to add to the Frontier if they
     * aren't in the Frontier yet and if they haven't been visited yet.
     *
     * @param node The current Node to expand to find children Nodes.
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @param obstacles An ArrayList with obstacles present on the world.
     * @return An ArrayList of Nodes with the children Nodes to add to the Frontier.
     */
    @Override
    public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet, ArrayList<State> obstacles) {

        // Local variables.
        ArrayList<Node> successorsSet = new ArrayList<>();
        Node newNode;

        ArrayList<State> nextStates = successor(node.getState(), problem, obstacles);

        for (State state: nextStates) {
            if (!(isNodeInQueueFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
                newNode = makeNode(node, state.getD(), state.getAngle(), problem);
                successorsSet.add(newNode);
            }
        }
        return successorsSet;
    }

}
