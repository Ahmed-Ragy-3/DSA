package Stack_Queue;
class SingleNode {
    static private int numberOfNodes = 0;
    private Object value;
    private SingleNode next;
    
    
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

    static int getNumOfNodes() {
        return numberOfNodes;
    }
}


public class LinkedQueue<T> implements MyQueue<T>{
    int size;
    SingleNode front;
    SingleNode rear;
    
    public LinkedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(front == null && rear == null)
        {
            return true;
        }
        return false;
    }

    @Override
    public void enqueue(T value) {
        SingleNode newNode = new SingleNode(value);
        if(isEmpty()) {
            front = newNode;
            rear = newNode;
            size++;
            return;
        }
        rear.setNext(newNode);
        rear = newNode;
        size++;
        return;
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        @SuppressWarnings("unchecked")
        T temp = (T)front.getValue();
        front = front.getNext();
        size--;
        return temp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        return (T)this.front.getValue();
    }

    @Override
    public void display() {
        SingleNode temp = front;
        if(temp == null) {System.out.println("[]");return;}
        System.out.print("[");
        while(temp.getNext()!= null)
        {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
        System.out.println(temp.getValue() + "]");

    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }
    //----------------------------------------------------------------
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.display();
        queue.enqueue(1);
        queue.display();
        queue.enqueue(2);
        queue.display();
        queue.enqueue(3);
        queue.display();
        queue.enqueue(4);
        queue.display();
        queue.enqueue(5);
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        
        
    }
}
