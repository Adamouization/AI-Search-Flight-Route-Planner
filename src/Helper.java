import java.util.LinkedList;

/**
 *
 * Helper class containing common helper methods used across multiple classes.
 *
 * @author adam jaamour (agj6)
 *
 */
public class Helper {

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
        System.out.println("]");
    }

    /**
     * Prints an error message to the command line and terminates the program with an error code.
     */
    public static void errorMessage() {
        System.err.println("usage: java A1main <DFS|BFS|AStar|BestF|...> <N> <d_s,angle_s> <d_g,angle_g> [params]");
        System.exit(1); // System exit with an error code.
    }

}
