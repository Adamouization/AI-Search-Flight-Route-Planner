/**
 *
 * Data structure representing a Node in the search tree.
 *
 * @author adam jaamour (agj6)
 *
 */
public class Node {

    // Declare variables.
    private State state; // Includes the parallel and the meridian.
    private String action;
    private double pathCost;
    private Node parentNode;
    private int depth;

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
