package Linked_List;

class DoublyNode<T> {
    private T value;
    private DoublyNode<T> next;
    private DoublyNode<T> prev;

    private static int numberOfNodes = 0;

    public DoublyNode(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public DoublyNode(T value, DoublyNode<T> next, DoublyNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public DoublyNode() {
        this.value = null;
        this.next = null;
        this.prev = null;
    }

    public T getValue() {
        return value;
    }

    public DoublyNode<T> getNext() {
        return next;
    }

    public DoublyNode<T> getPrev() {
        return prev;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }

    public void setPrev(DoublyNode<T> prev) {
        this.prev = prev;
    }

    public static int getNumOfNodes() {
        return numberOfNodes;
    }

}

public class DoublyLinkedList<T> {
    DoublyNode<T> head;
    DoublyNode<T> tail;
    int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoublyLinkedList(DoublyNode<T> head) {
        this.head = head;
        this.tail = head;
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public DoublyNode<T> getHead() {
        return this.head;
    }

    public DoublyNode<T> getTail() {
        return this.tail;
    }

    public void removeEnd() {
        if (this.tail == null) {
            System.out.println("Empty");
            return;
        }
        if (this.tail == this.head) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }

        this.tail = this.tail.getPrev();
        this.tail.setNext(null);
        this.size--;
    }

    public void addFirst(T value) {
        DoublyNode<T> newNode = new DoublyNode<T>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        size++;
        return;
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("Empty");
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
            return;
        }

        head = head.getNext();
        head.setPrev(null);
        size--;
        return;
    }

    public void addLast(T value) {
        if (tail == null) {
            addFirst(value);
            return;
        }
        DoublyNode<T> newNode = new DoublyNode<T>(value);

        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
        size++;
        return;
    }

    public void addAtIndex(int index, T value) {
        if (index < 0 || index > size) {
            System.out.println("There is no index " + index);
            return;
        }
        DoublyNode<T> newNode = new DoublyNode<T>(value);
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }
        DoublyNode<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        newNode.setPrev(temp);
        temp = temp.getNext().getNext();
        temp.setPrev(newNode);
        size++;
        return;
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("There is no index " + index);
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeEnd();
            return;
        }
        DoublyNode<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        DoublyNode<T> temp2 = temp.getNext();
        temp2.setPrev(temp);
        return;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public T getAtIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("There is no index " + index);
            return null;
        }
        DoublyNode<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    public void setAtIndex(int index, T value) {
        if (index < 0 || index >= size) {
            System.out.println("There is no index " + index);
            return;
        }
        DoublyNode<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        temp.setValue(value);
        return;
    }

    public void reverse() {
        DoublyNode<T> temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    public void concat(DoublyLinkedList<T> list2) {
        if (this.isEmpty()) {
            this.head = list2.head;
            this.tail = list2.tail;
            this.size = list2.size;
            return;
        }
        this.tail.setNext(list2.head);
        this.tail = list2.tail;
        this.size = list2.size + this.size;
    }

    public void copy(DoublyLinkedList<T> list2) {
        if (!this.isEmpty()) {
            this.clear();
        }

        DoublyNode<T> temp = list2.head;
        while (temp != null) {
            this.addLast(temp.getValue());
            temp = temp.getNext();
        }

    }

    public void print() {
        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }
        DoublyNode<T> temp = head;
        System.out.print("[");
        while (temp.getNext() != null) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
        System.out.print(temp.getValue() + "]");
        System.out.println();
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        String result = "";
        DoublyNode<T> temp = head;
        result += "[";
        while (temp.getNext() != null) {
            result += temp.getValue() + ", ";
            temp = temp.getNext();
        }
        result += temp.getValue() + "]\n";
        return result;
    }

    public void printReverse() {
        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }
        DoublyNode<T> temp = tail;
        System.out.print("[");
        while (temp.getPrev() != null) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getPrev();
        }
        System.out.print(temp.getValue() + "]");
        System.out.println();
    }

    public static <T extends Integer> void sort(DoublyLinkedList<T> list) {
        if (list.head == null || list.head.getNext() == null) {
            return;
        }
        DoublyNode<T> temp = list.head;
        while (temp != null) {
            DoublyNode<T> temp2 = temp.getNext();
            while (temp2 != null) {
                if (temp.getValue() > temp2.getValue()) {
                    T tempValue = temp.getValue();
                    temp.setValue(temp2.getValue());
                    temp2.setValue(tempValue);
                }
                temp2 = temp2.getNext();
            }
            temp = temp.getNext();
        }
    }

    public static void main(String[] args) {
        long x = System.currentTimeMillis();
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        list.addLast(5);
        list.addLast(3);
        list.addLast(1);
        list.addLast(5);
        list.addLast(3);
        list.addLast(1);
        list.print();
        sort(list);
        list.printReverse();
        list.print();
        System.out.println(-x + System.currentTimeMillis());
    }
}