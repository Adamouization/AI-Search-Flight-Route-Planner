double heuristicH = estimateCostFromNodeToGoal(curNode.getState(), problem.getEndPoint());
if (problem.getSearchType().equals("AStar")) { // A* Search.
    double heuristicG = findSolutionPathCost(curNode);
    double heuristicF = heuristicG + heuristicH;
    node.setPathCost(heuristicF);
}
else { // BestF Search.
    node.setPathCost(heuristicH);
}

/* Estimates the Euclidian distance in polar coordinates from the current State to the goal State. */
double estimateCostFromNodeToGoal(State curState, State goalState) {
    double pow = Math.pow(curState.getD(), 2) + Math.pow(goalState.getD(), 2);
    double cos = 2 * curState.getD() * goalState.getD() * Math.cos(goalState.getAngle() - curState.getAngle());
    return Math.sqrt(pow - cos);
}