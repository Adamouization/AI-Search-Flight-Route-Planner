import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Search methods.
 *
 * @author adam jaamour (agj6)
 *
 */
abstract class Search {

    public ArrayList<State> successor(State state, Problem problem) {
        ArrayList<State> childrenNodes = new ArrayList<>();

        // Move East (H90). If angle = 315 degrees, loop back to 0 degrees.
        if (state.getAngle() == 315) {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            0,
                            0,
                            state.getStatus()
                    )
            );
        }
        else {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            state.getIndex() + 1,
                            state.getAngle() + 45,
                            state.getStatus()
                    )
            );
        }

        // Move West (H270). If angle = 0 degrees, loop back to 315 degrees.
        if (state.getAngle() == 0) {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            7,
                            315,
                            state.getStatus()
                    )
            );
        }
        else {
            childrenNodes.add(
                    new State(
                            state.getD(),
                            state.getIndex() - 1,
                            state.getAngle() - 45,
                            state.getStatus()
                    )
            );
        }

        // Move North (H360), but cannot go to pole (N=0).
        if ( !(state.getD() == 1) ) {
            childrenNodes.add(
                    new State(
                            state.getD() - 1,
                            state.getIndex(),
                            state.getAngle(),
                            state.getStatus()
                    )
            );
        }

        // Move South (H180), but cannot go beyond last parallel N.
        if ( !(state.getD() == problem.getN() - 1) ) {
            childrenNodes.add(
                    new State(
                            state.getD() + 1,
                            state.getIndex(),
                            state.getAngle(),
                            state.getStatus()
                    )
            );
        }

        // Return valid moves.
        return childrenNodes;
    }

    public boolean goalTest(Node curPoint, State endPoint) {
        return curPoint.getState().getD() == endPoint.getD() && curPoint.getState().getAngle() == endPoint.getAngle();
    }

    public int pathCost(/* State state1, State state2 */) {
        int cost = 0;
        System.out.println("pathCost");
        return cost;
    }

    public Node makeNode(Node curNode, int d, int angle) {
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

        // Set node's action. todo
        //ACTION[n] ← move from STATE[node] to state

        // Set node depth.
        try {
            node.setDepth(curNode.getDepth() + 1);
        }
        catch (NullPointerException e) {
            node.setDepth(0);
        }

        return node;
    }

    abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);

    abstract public ArrayList<Node> expand(Node node, Problem problem, LinkedList<Node> frontier, ArrayList<Node> exploredSet);

    abstract public LinkedList<Node> insertFrontierNodes(LinkedList<Node> frontier, ArrayList<Node> nodes);

    abstract public Node removeFrontierNode(LinkedList<Node> frontier);

    abstract public boolean isNodeInFrontier(LinkedList<Node> frontier, State state);

    abstract public boolean isNodeInExploredSet(ArrayList<Node> exploredSet, State state);
}
