package Stack_Queue;

import java.util.Arrays;
import java.util.Scanner;



public class ArrayQueue<T>  {
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
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(T value) {
        if(this.isFull())
        {
            throw new IllegalStateException("Queue is Full");
        }
        queue[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

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

    public T peek() {
        if(isEmpty())
        {
            return null;
        }
        return queue[front];
    }

    public void display() {
        
        if(isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = rear-1; i > front; i--) {
            System.out.print(queue[i] + ", ");
        }
        System.out.print(queue[front] + "]");

    }

    public void clear() {
        rear = front;
    }

    public boolean isFull() {
        if(size == capacity) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cleanInput = input.nextLine();
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        if (cleanInput.compareTo("[]") != 0) {
            cleanInput = cleanInput.replaceAll("\\[|\\]|\\s", "");
            String[] elements = cleanInput.split(",");
            for (int i = elements.length-1; i >=0 ; i--) {
                queue.enqueue(Integer.parseInt(elements[i]));
            }
        }

        String operation = input.next();

        if (operation.compareTo("enqueue") == 0) {
            queue.enqueue(input.nextInt());
            queue.display();
        } else if (operation.compareTo("dequeue") == 0) {
            if (queue.isEmpty()) {
                System.out.println("Error");
            } else {
                queue.dequeue();
                queue.display();
            }
        } else if (operation.compareTo("isEmpty") == 0) {
            if (queue.isEmpty()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } else if (operation.compareTo("size") == 0) {
            System.out.println(queue.size());
        }
        input.close();
    }
}
