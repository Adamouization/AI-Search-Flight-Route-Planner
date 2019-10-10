import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * Abstract method working as a template for informed search algorithm implementations. Contains methods common to all
 * informed search algorithms, and methods specific to each algorithm implementation which are developed in their
 * respective concrete classes.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class InformedSearch extends GeneralSearch {

    /**
     * Creates a new Node, linking it to the parent node. Takes path cost into account to perform informed search. Uses
     * PriorityQueue to represent the Frontier.
     *
     * @param curNode The current Node.
     * @param d The current node's parallel.
     * @param angle The current node's meridian.
     * @return The new created Node.
     * @throws NullPointerException When dealing with the root node, which has no parent node.
     */
    public Node makeNode(Node curNode, int d, int angle, Problem problem) {
        Node node = new Node();

        // Set node state.
        node.setState( new State(d, Helper.getPointIndex(angle), angle, ' ') );

        // Set node predecessor.
        try {
            node.setParentNode(curNode);
        }
        catch (NullPointerException e) {
            node.setParentNode(null);
        }

        // Determine and set node's action.
        if (node.getParentNode() != null) {
            node.setAction(Helper.getActionFromState(node));
        }

        // Calculate and set path cost.
        try {
            double heuristicH = estimateCostFromNodeToGoal(curNode.getState(), problem.getEndPoint());
            // A* Search.
            if (problem.getSearchType().equals("AStar")) {
                double heuristicG = findSolutionPathCost(curNode, problem.getStartPoint());
                double heuristicF = heuristicG + heuristicH;
                node.setPathCost(heuristicF);
            }
            // BestF Search.
            else {
                node.setPathCost(heuristicH);
            }

        }
        catch (NullPointerException e)  {
            node.setPathCost(0.0);
        }

        // Set node depth.
        try {
            node.setDepth(curNode.getDepth() + 1);
        }
        catch (NullPointerException e) {
            node.setDepth(0);
        }

        this.nodesCreated++;
        return node;
    }

    /**
     * Checks that a node is in the Frontier.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     * @param state The State to check that is in the Frontier.
     * @return A Boolean indicating if the Node is already in the Frontier.
     */
    public boolean isNodeInQueueFrontier(PriorityQueue<Node> frontier, State state) {
        for (Node n: frontier) {
            if (n.getState().getD() == state.getD() && n.getState().getAngle() == state.getAngle()) {
                return true;
            }
        }
        return false;
    }

    /* Private Methods  ********************************************************************************************* */

    /**
     * Estimates the Euclidian distance in polar coordinates from the current State to the goal State.
     *
     * @param curState State going to.
     * @param goalState State coming from.
     * @return The Euclidian distance between the current State and the goal State.
     */
    private double estimateCostFromNodeToGoal(State curState, State goalState) {
        double pow = Math.pow(curState.getD(), 2) + Math.pow(goalState.getD(), 2);
        double cos = 2 * curState.getD() * goalState.getD() * Math.cos(goalState.getAngle() - curState.getAngle());
        return Math.sqrt(pow - cos);
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public Node treeSearch(Problem problem, PriorityQueue<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet);

    abstract public PriorityQueue<Node> insertFrontierNodes(PriorityQueue<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(PriorityQueue<Node> frontier);

}
