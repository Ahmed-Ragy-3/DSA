package Labs.Lab4.Part1;

class DoubleNode {
    
    private Object value;
    private DoubleNode next;
    private DoubleNode prev;

    private static int numberOfNodes = 0;

    // -------------------------------Constructors---------------------------------
    public DoubleNode(Object value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
    public DoubleNode(Object value, DoubleNode next, DoubleNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public DoubleNode() {
        this.value = null;
        this.next = null;
        this.prev = null;
    }
    // -------------------------------endConstructors-------------------------------

    // -------------------------------setterAndGetters------------------------------
    public Object getValue() {
        return value;
    }

    public DoubleNode getNext() {
        return next;
    }

    public DoubleNode getPrev() {
        return prev;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public void setPrev(DoubleNode prev) {
        this.prev = prev;
    }
    // -----------------------------endSetterAndGetters-------------------------------

    public static int getNumOfNodes() {
        return numberOfNodes;
    }

}