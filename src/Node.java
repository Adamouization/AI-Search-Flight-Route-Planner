/**
 *
 * Data structure representing a Node in the search tree. Implements the Comparable interface to be used with
 * PriorityQueues.
 *
 * @author adam jaamour (agj6)
 *
 */
public class Node implements Comparable<Node> {

    // Declare variables.
    private State state; // Includes the parallel and the meridian.
    private String action;
    private double pathCost;
    private Node parentNode;
    private int depth;

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * @param n the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Node n) {
        if (this.pathCost > n.pathCost) {
            return 1;
        }
        else if (this.pathCost < n.pathCost) {
            return -1;
        }
        return 0;
    }

    /**
     * Generates a String representation of a Node object.
     *
     * @return The String representation of a Node object.
     */
    @Override
    public String toString() {
        return this.state.toString();
    }

    /* Getters ****************************************************************************************************** */

    /**
     * @return The State of the Node.
     */
    public State getState() {
        return state;
    }

    /**
     * @return A String representing the action followed to get from the parentNode to this Node.
     */
    public String getAction() {
        return action;
    }

    /**
     * @return
     */
    public double getPathCost() {
        return pathCost;
    }

    /**
     * @return The parent Node of this Node.
     */
    public Node getParentNode() {
        return parentNode;
    }

    /**
     * @return The Node's current depth in the search tree.
     */
    public int getDepth() {
        return depth;
    }

    /* Setters ****************************************************************************************************** */

    /**
     * @param state Sets the State object for this Node.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @param action A String representing the action followed to get from the parentNode to this Node.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @param pathCost
     */
    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    /**
     * @param parentNode Points to the parent Node of this Node.
     */
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * @param depth Sets the Node's current depth in the search tree.
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

}
