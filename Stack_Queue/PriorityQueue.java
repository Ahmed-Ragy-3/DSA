package Stack_Queue;
class Node<T> {
    private T value;
    private int key;
     Node<T> next;
    public Node(T value, int key) {
        this.value = value;
        this.key = key;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    @SuppressWarnings("rawtypes")
    public Node getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
}

public class PriorityQueue<T> {
    Node<T> front;
    int size = 0;
    public PriorityQueue() {
        this.front = null;
    }
    int size() {
        return size;
    }
    boolean isEmpty() {
        return front == null;
    }
    public void insert(T value, int key) {
        Node<T> newnode = new Node<>(value, key);
        if(isEmpty() || key < front.getKey())
        {
            newnode.setNext(front);
            front = newnode;
            size++;
            return;
        }
        Node<T> temp = front;
        while(temp != null)
        {
            if(temp.next == null || key < temp.next.getKey())
            {
                newnode.setNext(temp.next);
                temp.next = newnode;
                break;
            }
            temp = temp.next;
        }
        size++;
        return;
    }
    public T min() {
        if(isEmpty()) 
        {
            throw new IllegalStateException("Queue is Empty");
        }
        return front.getValue();
    }
    @SuppressWarnings("unchecked")
    public T removeMin() {
        if(isEmpty())
        {
            throw new IllegalStateException("Queue is Empty");
        }
        T temp = front.getValue();
        front = front.getNext();
        size--;
        return temp;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.insert(4, 0);
        queue.insert(3, 6);
        queue.insert(2, 1);
        queue.insert(1, 2); 
        queue.insert(0, 1);
        queue.insert(5, 4);
        queue.insert(6, 5);
        queue.insert(7, 7);
        queue.insert(10, 0);
        queue.insert(11, 0);
        queue.insert(12, 2);
        queue.insert(13, -3);
        
        while(!queue.isEmpty())
        {
            System.out.println(queue.removeMin());
        }
    }
}