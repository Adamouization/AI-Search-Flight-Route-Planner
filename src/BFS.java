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
        System.out.println("Starting BFS");

        ArrayList<Node> exploredSet = new ArrayList<>();
        Node curNode;

        // Create root node and add it to the frontier.
        Node rootNode = makeNode(null, problem.getStartPoint().getD(), problem.getStartPoint().getAngle());
        frontier.add(rootNode);

        while (!frontier.isEmpty()) {
            curNode = removeFrontierNode(frontier);
            exploredSet.add(curNode);
            if (goalTest(curNode, problem.getEndPoint())) {
                return curNode;
            }
            else {
                insertFrontierNodes(frontier, expand(curNode, problem, frontier));
            }
        }

        return null; // If frontier is empty, return null.
    }

    @Override
    public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier) {
        ArrayList<State> nextStates = new ArrayList<>();
        ArrayList<Node> successorsSet = new ArrayList<>();

        nextStates = successor(node.getState(), problem);

        for (State state: nextStates) {
            if (!(frontier.contains(state))) {
                node = makeNode(node, node.getState().getD(), node.getState().getAngle());
                successorsSet.add(node);
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

}
