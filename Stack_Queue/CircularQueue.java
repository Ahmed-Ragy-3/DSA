package Stack_Queue;


class SingleNode<T> {
    static private int numberOfNodes = 0;
    private Object value;
    private SingleNode<T> next;
    
    
    public SingleNode(T value) {
        this.value = value;
        this.next = null;
        numberOfNodes++;
    }
    public SingleNode(Object value, SingleNode<T> next) {
        this.value = value;
        this.next = next;
        numberOfNodes++;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public SingleNode<T> getNext() {
        return next;
    }
    public void setNext(SingleNode<T> next) {
        this.next = next;
    }

    static int getNumOfNodes() {
        return numberOfNodes;
    }
}


public class CircularQueue<T> {
    private int size = 0;
    private SingleNode<T> tail;

    public CircularQueue() {
        this.tail = null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void enqueue(T value) {
        SingleNode<T> newNode = new SingleNode<T>(value);
        if(isEmpty()){
            newNode.setNext(newNode);
        }
        else{
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        else {
            SingleNode<T> temp = tail.getNext();
            if(size == 1)
            {
                tail = null;
            }
            else{
                tail.setNext(tail.getNext().getNext());
            }
            size--;
            return (T) temp.getValue();
        }
    }
    
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.size());
        System.out.println(queue.dequeue() + " " + queue.size);
        System.out.println(queue.dequeue() + " " + queue.size);
        System.out.println(queue.dequeue() + " " + queue.size);
        System.out.println(queue.dequeue() + " " + queue.size);
        queue.enqueue(5);
        System.out.println(queue.dequeue() + " " + queue.size);
    }
}
