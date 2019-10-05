public class Node {

    private int d; // The parallel - part of the state.
    private int angle; // The meridian - part of the state.
    private String action;
    private Node parentNode;
    private int depth;

    public void setD(int d) {
        this.d = d;
    }

    public void setAngle(int angle) {
        this.angle = angle;
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

    public int getD() {
        return d;
    }

    public int getAngle() {
        return angle;
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
