public class BFS extends UninformedSearch {
    @Override
    public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(nodes); // Add nodes at the end of the queue (FIFO).
        return frontier;
    }
}

public class DFS extends UninformedSearch {
    @Override
    public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(0, nodes); // Add nodes at the end of the queue (LIFO).
        return frontier;
    }
}