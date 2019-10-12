@Override
public ArrayList<Node> expand(Node node, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> exploredSet, ArrayList<State> obstacles) {
    ArrayList<Node> successorsSet = new ArrayList<>();
    ArrayList<State> nextStates = successor(node.getState(), problem, obstacles);

    for (State state: nextStates) {
        Node newNode = makeNode(node, state.getD(), state.getAngle(), problem);
        if (isNodeInQueueFrontier(frontier, state)) {
            for (Node n: frontier) {
                if (newNode.getPathCost() < n.getPathCost()) {
                    frontier.remove(n);
                    break;
                }
            }
        }
        if (!(isNodeInQueueFrontier(frontier, state)) && !(isNodeInExploredSet(exploredSet, state))) {
            successorsSet.add(newNode);
        }
    }
    return successorsSet;
}