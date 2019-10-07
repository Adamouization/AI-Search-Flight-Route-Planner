/**
 *
 * Data structure representing a state in the world, including its location (parallel and meridian), it's index in the
 * world data structure, and it's status (S for start, G for goal, P for pole).
 *
 * @author adam jaamour (agj6)
 *
 */
public class State {

    // Declare variables.
    private int d; // Parallel - can be used as index for outer LinkedList (to identify which parallel).
    private int index; // Index in inner LinkedList (to identify which meridian/angle).
    private int angle; // Meridian.
    private char status; // Status of the current location: S for start, G for goal, P for pole.

    /**
     * Class constructor.
     *
     * @param d The parallel, which can be used as index for the outer LinkedList.
     * @param index The index in inner LinkedList (to identify which meridian/angle).
     * @param angle The meridian, expressed in degrees.
     * @param status The possible states for the current point: S for start, G for goal, P for pole.
     */
    public State(int d, int index, int angle, char status) {
        this.d = d;
        this.index = index;
        this.angle = angle;
        this.status = status;
    }

    /**
     * Generates a String representation of a Point object.
     *
     * @return The String representation of a Point object.
     */
    @Override
    public String toString() {
        return "(" + this.d + ", " + this.angle + ")";
    }

    /**
     * @return The parallel, which can be used as index for the outer LinkedList.
     */
    public int getD() {
        return d;
    }

    /**
     * @return The index in inner LinkedList (to identify which meridian/angle).
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return The meridian, expressed in degrees
     */
    public int getAngle() {
        return angle;
    }

    /**
     * @return The possible states for the current point: S for start, G for goal, P for pole.
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param d The parallel, which can be used as index for the outer LinkedList.
     */
    public void setD(int d) {
        this.d = d;
    }

    /**
     * @param index The index in inner LinkedList (to identify which meridian/angle).
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @param angle The meridian, expressed in degrees
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     * @param status The possible states for the current point: S for start, G for goal, P for pole.
     */
    public void setStatus(char status) {
        this.status = status;
    }
}
