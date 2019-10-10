import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Breadth-First Search implementation. Extends the UninformedSearch abstract class. Uses LinkedLists to represent the
 * Frontier.
 *
 * @author adam jaamour (agj6)
 *
 */
public class BFS extends UninformedSearch {

    /**
     * Implements a FIFO queue by pushing new Nodes at the end of the queue.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param nodes The Nodes to push to the Frontier.
     * @return The updated Frontier with the new Nodes to explore in the future.
     */
    @Override
    public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(nodes); // Add nodes at the end of the queue (FIFO).
        return frontier;
    }

}
