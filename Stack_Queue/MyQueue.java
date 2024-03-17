package Stack_Queue;

public interface MyQueue<T> {
    int size();
    boolean isEmpty();
    void enqueue(T value);
    T dequeue();
    T peek();
    void display();
    void clear();
    boolean isFull();
}

