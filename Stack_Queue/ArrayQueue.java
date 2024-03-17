package Stack_Queue;
import java.util.Arrays;

public class ArrayQueue<T> implements MyQueue<T> {
    private int size = 0;
    private int capacity = 20;
    private int front = 0;
    private int rear = 0;
    private T[] queue ;

    @SuppressWarnings("unchecked")
    public ArrayQueue(){
        queue = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(T value) {
        if(this.isFull())
        {
            throw new IllegalStateException("Queue is Full");
        }
        queue[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

    @Override
    public T dequeue() {
        if(this.isEmpty())
        {
            throw new IllegalStateException("Queue is Empty");
        }
        T temp = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;
        return temp;
    }

    @Override
    public T peek() {
        if(isEmpty())
        {
            return null;
        }
        return queue[front];
    }

    @Override
    public void display() {
        System.out.println(Arrays.deepToString(queue));
    }

    @Override
    public void clear() {
        rear = front;
    }

    @Override
    public boolean isFull() {
        if(size == capacity) return true;
        return false;
    }
}
