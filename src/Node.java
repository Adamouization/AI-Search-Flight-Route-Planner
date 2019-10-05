public class Node {

    private State state; // Includes the parallel and the meridian.
    private String action;
    private Node parentNode;
    private int depth;

    public void setState(State state) {
        this.state = state;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public State getState() {
        return state;
    }

    public String getAction() {
        return action;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getDepth() {
        return depth;
    }
}
