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
     * The main search method for informed algorithms such as BestF or A*.
     *
     * @param problem The problem data structure, containing initialisation parameters.
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param obstacles An ArrayList with obstacles present on the world.
     * @return The current Node when a goal State is reached.
     */
    public Node treeSearch(Problem problem, PriorityQueue<Node> frontier, ArrayList<State> obstacles) {

        // Local variables used throughout the search.
        Node curNode;
        ArrayList<Node> exploredSet = getExploredSet();

        // Create and add the root node to the frontier.
        frontier.add(
                makeNode(null, problem.getStartPoint().getD(), problem.getStartPoint().getAngle(), problem)
        );

        while (!frontier.isEmpty()) {
            iteration++;
            curNode = removeFrontierNode(frontier);
            exploredSet.add(curNode);
            if (goalTest(curNode, problem.getEndPoint())) {
                setExploredSet(exploredSet);
                printInformedSearchStatus(curNode, frontier, exploredSet, iteration);
                return curNode;
            }
            else {
                insertFrontierNodes(frontier, expand(curNode, problem, frontier, exploredSet, obstacles));
            }
            printInformedSearchStatus(curNode, frontier, exploredSet, iteration);
        }

        return null; // If frontier is empty, return null (no solution found).
    }

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
            node.setAction(getActionFromState(node));
        }

        // Calculate and set path cost.
        try {
            double heuristicH = estimateCostFromNodeToGoal(curNode.getState(), problem.getEndPoint());
            // A* Search.
            if (problem.getSearchType().equals("AStar")) {
                double heuristicG = findSolutionPathCost(curNode);
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
     * Adds new Nodes to the PriorityQueue based on their path cost.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param nodes The Nodes to push to the Frontier.
     * @return The updated Frontier with the new Nodes to explore in the future.
     */
    private PriorityQueue<Node> insertFrontierNodes(PriorityQueue<Node> frontier, ArrayList<Node> nodes) {
        frontier.addAll(nodes);
        return frontier;
    }

    /**
     * Popping a Node from the front of the queue, which is the the one with the highest priority.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @return The updated Frontier without the Node being currently explored.
     */
    private Node removeFrontierNode(PriorityQueue<Node> frontier) {
        return frontier.poll(); // Remove the node with the highest priority.
    }

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

    /**
     * Prints the current status of the informed search algorithm to the command line, including the iteration, the
     * Frontier (PriorityQueue) and the set of explored states.
     *
     * @param node The current Node being expanded.
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     * @param exploredSet An ArrayList of Nodes representing the Nodes that have already been expanded.
     * @param iteration The current search loop number.
     */
    private static void printInformedSearchStatus(Node node, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet, int iteration) {
        System.out.println("Iteration #" + iteration + " -------------------------");
        System.out.println("Current node: " + node.toString());
        printFrontierWithCosts(frontier);
        System.out.println("Explored States: " + exploredSet.toString());
    }

    /**
     * Prints the Frontier with the costs associated to each Node.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     */
    private static void printFrontierWithCosts(PriorityQueue<Node> frontier) {
        System.out.print("Frontier: [");
        for (Node n: frontier) {
            System.out.print(n.getState().toString() + " " + Math.round(n.getPathCost() * 1000.0) / 1000.0 + ", "); // Round to 3 decimal places.);
        }
        System.out.print("]\n");
    }

    /* Abstract Method Declarations ********************************************************************************* */

    abstract public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet, ArrayList<State> obstacles);

}
