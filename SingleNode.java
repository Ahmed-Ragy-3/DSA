



public class SingleNode {
    static private int numberOfNodes = 0;
    public Object value;
    public SingleNode next;

    // -------------------------------Constructors---------------------------------
    public SingleNode(Object value) {
        this.value = value;
        this.next = null;
        numberOfNodes++;
    }

    public SingleNode(Object value, SingleNode next) {
        this.value = value;
        this.next = next;
        numberOfNodes++;
    }

    public SingleNode() {
    }
    // -------------------------------endConstructors-------------------------------

    // -------------------------------setterAndGetters------------------------------

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }
    // -----------------------------endSetterAndGetters-------------------------------

    static int getNumOfNodes() {
        return numberOfNodes;
    }

}
