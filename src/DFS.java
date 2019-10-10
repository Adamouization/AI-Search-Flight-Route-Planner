import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Depth-First Search implementation. Extends the UninformedSearch abstract class. Uses LinkedLists to represent the
 * Frontier.
 *
 * @author adam jaamour (agj6)
 *
 */
public class DFS extends UninformedSearch {

    /**
     * Implements a LIFO queue by pushing new Nodes at the beginning of the queue.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param nodes The Nodes to push to the Frontier.
     * @return The updated Frontier with the new Nodes to explore in the future.
     */
    @Override
    public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(0, nodes); // Add nodes at the end of the queue (LIFO).
        return frontier;
    }

}
