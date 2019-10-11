public ArrayList<State> successor(State state, Problem p, ArrayList<State> o) {
    ArrayList<State> childrenNodes = new ArrayList<>();

    // Move East (H90). If angle = 315 degrees, loop back to 0 degrees.
    if (state.getAngle() == 315) {...}
    else {...}

    // Move West (H270). If angle = 0 degrees, loop back to 315 degrees.
    if (state.getAngle() == 0) {...}
    else {...}

    // Move North (H360), but cannot go to pole (N=0).
    if ( !(state.getD() == 0) ) {...}

    // Move South (H180), but cannot go beyond last parallel N.
    if ( !(state.getD() == problem.getN() - 1) ) {...}

    // Cannot go to pole (0, 0).
    for (State s: childrenNodes) {...}

    // Cannot go to obstacles.
    for (State a: o) {...}

    return childrenNodes; // Return valid moves.
}