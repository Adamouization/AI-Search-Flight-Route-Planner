import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * Breadth-First Search implementation. Extends the Search abstract class. Uses PriorityQueues to represent the
 * Frontier.
 *
 * @author adam jaamour (agj6)
 *
 */
public class BestF extends InformedSearch {

    /**
     * The main search method for the BFS algorithm.
     *
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @return The current Node when a goal State is reached.
     */
    @Override
    public Node treeSearch(Problem problem, PriorityQueue<Node> frontier) {
        System.out.println("Starting Best-First Search...");

        // Local variables used throughout the search.
        Node curNode;
        ArrayList<Node> exploredSet = getExploredSet();

        // Create and add the root node to the frontier.
        Helper.printFrontierQueue(frontier);
        frontier.add(
                makeNode(null, problem.getStartPoint().getD(), problem.getStartPoint().getAngle(), problem)
        );
        Helper.printFrontierQueue(frontier);

        while (!frontier.isEmpty()) {
            curNode = removeFrontierNode(frontier);
            exploredSet.add(curNode);
            if (goalTest(curNode, problem.getEndPoint())) {
                setExploredSet(exploredSet);
                return curNode;
            }
            else {
                insertFrontierNodes(frontier, expand(curNode, problem, frontier, exploredSet));
            }
            Helper.printFrontierQueue(frontier);
        }

        return null; // If frontier is empty, return null (no solution found).
    }

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
            if (!(isNodeInQueueFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
                newNode = makeNode(node, state.getD(), state.getAngle(), problem);
                successorsSet.add(newNode);
            }
        }
        return successorsSet;
    }

    /**
     * Implements a FIFO queue by pushing new Nodes at the end of Queue.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param nodes The Nodes to push to the Frontier.
     * @return The updated Frontier with the new Nodes to explore in the future.
     */
    @Override
    public PriorityQueue<Node> insertFrontierNodes(PriorityQueue<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(nodes);
        return frontier;
    }

    /**
     * Implements a FIFO queue by popping a Node from the front of the queue.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @return The updated Frontier without the Node being currently explored.
     */
    @Override
    public Node removeFrontierNode(PriorityQueue<Node> frontier) {
        return frontier.poll(); // Remove the node with the highest priority.
    }

}
