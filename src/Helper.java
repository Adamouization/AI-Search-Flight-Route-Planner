import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * Helper class containing common helper methods used across multiple classes.
 *
 * @author adam jaamour (agj6)
 *
 */
public class Helper {

    /**
     * Determines the action based on the current Node's State and its parent Node's State.
     *
     * @param node The current Node.
     * @return The action used to move from the parent Node's State to the current Node's State.
     */
    public static String getActionFromState(Node node) {
        // Same parallel, so different meridian (either E or W).
        if (node.getState().getD() == node.getParentNode().getState().getD()) {
            // Current node angle is bigger than parent node angle, so moved E.
            if (node.getState().getAngle() - node.getParentNode().getState().getAngle() > 0) {
                // Take care of special case looping from 0 to 315 degrees, which is going Wget.
                if (node.getState().getAngle() == 315 && node.getParentNode().getState().getAngle() == 0) {
                    return "H270";
                }
                return "H90";
            }
            // Current node angle is smaller than parent node angle, so moved W.
            else {
                // Take care of special case looping from 315 to 0 degrees, which is going E.
                if (node.getState().getAngle() == 0 && node.getParentNode().getState().getAngle() == 315) {
                    return "H90";
                }
                return "H270";
            }
        }
        // Different parallel (either N or S).
        else {
            // Current node parallel is bigger than parent node parallel, so moved S.
            if (node.getState().getD() - node.getParentNode().getState().getD() > 0) {
                return "H180";
            }
            // Current node parallel is smaller than parent node parallel, so moved N.
            else {
                return "H360";
            }
        }
    }

    /**
     * Takes a meridian (angle) and returns its expected (ordered) index in a LinkedList.
     *
     * @param angle meridian of a point expressed in degrees.
     * @return matching index of the meridian (angle) in a LinkedList.
     */
    public static int getPointIndex(int angle) {
        int index = 0;
        switch (angle) {
            case 0:
                index = 0;
                break;
            case 45:
                index = 1;
                break;
            case 90:
                index = 2;
                break;
            case 135:
                index = 3;
                break;
            case 180:
                index = 4;
                break;
            case 225:
                index = 5;
                break;
            case 270:
                index = 6;
                break;
            case 315:
                index = 7;
                break;
        }
        return index;
    }

    /**
     * Prints the entire world to the command line.
     *
     * @param world the current world.
     */
    public static void printWorld(LinkedList<LinkedList<State>> world) {
        System.out.println("[");
        for (LinkedList<State> w: world) {
            System.out.println("    "+ w);
        }
        System.out.println("]\n");
    }

    /**
     * Prints the current Frontier (LinkedList) to the command line.
     *
     * @param frontier A LinkedList of Nodes representing the Nodes to expand next.
     */
    public static void printFrontierList(LinkedList<Node> frontier) {
        System.out.println("Frontier: " + frontier.toString());
    }

    /**
     * Prints the current Frontier (PriorityQueue) to the command line.
     *
     * @param frontier A PriorityQueue of Nodes representing the Nodes to expand next.
     */
    public static void printFrontierQueue(PriorityQueue<Node> frontier) {
        System.out.println("Frontier: " + frontier.toString());
    }

    /**
     * Prints an error message to the command line and terminates the program with an error code.
     */
    public static void errorMessage() {
        System.err.println("usage: java A1main <DFS|BFS|AStar|BestF|...> <N> <d_s,angle_s> <d_g,angle_g> [params]");
        System.exit(1); // System exit with an error code.
    }

}
