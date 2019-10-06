import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Breadth-First Search implementation.
 *
 * @author adam jaamour (agj6)
 *
 */
public class BFS extends Search {

    @Override
    public Node treeSearch(Problem problem, LinkedList<Node> frontier) {
        System.out.println("Starting BFS search");

        // Local variables used throughout the search.
        ArrayList<Node> exploredSet = new ArrayList<>();
        Node curNode;

        // Create and add the root node to the frontier.
        frontier.add(
                makeNode(null, problem.getStartPoint().getD(), problem.getStartPoint().getAngle())
        );

        while (!frontier.isEmpty()) {
            curNode = removeFrontierNode(frontier);
            exploredSet.add(curNode);
            if (goalTest(curNode, problem.getEndPoint())) {
                return curNode;
            }
            else {
                insertFrontierNodes(frontier, expand(curNode, problem, frontier, exploredSet));
            }
            System.out.println(frontier.toString());
        }

        return null; // If frontier is empty, return null.
    }

    @Override
    public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet) {

        // Local variables.
        ArrayList<Node> successorsSet = new ArrayList<>();
        Node newNode;

        ArrayList<State> nextStates = successor(node.getState(), problem);

        for (State state: nextStates) {
            if (!(isNodeInFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
                newNode = makeNode(node, state.getD(), state.getAngle());
                successorsSet.add(newNode);
            }
        }
        return successorsSet;
    }

    @Override
    public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(nodes); // Add nodes at the end of the queue (FIFO).
        return frontier;
    }

    @Override
    public Node removeFrontierNode(LinkedList<Node> frontier) {
        return frontier.removeFirst(); // Remove the first node from the queue (FIFO).
    }

    @Override
    public boolean isNodeInFrontier(LinkedList<Node> frontier, State state) {
        for (Node n: frontier) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNodeInExploredSet(ArrayList<Node> exploredSet, State state) {
        for (Node n: exploredSet) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

}
