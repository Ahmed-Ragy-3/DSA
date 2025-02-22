package Stack_Queue;


import java.util.Arrays;
import java.util.Scanner;

class SingleNode<T> {
    private static int numberOfNodes = 0;
    private T value;
    private SingleNode<T> next;
    
    
    public SingleNode(T value) {
        this.value = value;
        this.next = null;
        numberOfNodes++;
    }
    public SingleNode(T value, SingleNode<T> next) {
        this.value = value;
        this.next = next;
        numberOfNodes++;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
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


public class LinkedQueue<T> {
    int size;
    SingleNode<T> front;
    SingleNode<T> rear;
    
    public LinkedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return front == null && rear == null;
    }

    public void enqueue(T value) {
        SingleNode<T> newNode = new SingleNode<T>(value);
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

    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        T temp = (T)front.getValue();
        front = front.getNext();
        size--;
        return temp;
    }

    public T peek() {
        return (T)this.front.getValue();
    }

    public void display() {
        System.out.println(Arrays.toString(reverseArray(toArray())));
    }

    public int[] toArray() {
        int[] arr = new int[size];
        SingleNode<T> temp = front;
        int i = 0;
        while(temp!= null) {
            arr[i] = (int)temp.getValue();
            temp = temp.getNext();
            i++;
        }
        return arr;
    }

    public int[] reverseArray(int[] array) {
        for(int i=0; i< array.length/2 ; i++)
        {
            int temp = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = temp;
        }
        return array;
    }

    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isFull() {
        return false;
    }
    //----------------------------------------------------------------
       public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cleanInput = input.nextLine();
        LinkedQueue<Integer> queue = new LinkedQueue<>();
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
