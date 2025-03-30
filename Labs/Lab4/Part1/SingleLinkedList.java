package Labs.Lab4.Part1;


import java.util.Scanner;

public class SingleLinkedList implements ILinkedList {

    public SingleNode head;
    public SingleNode tail;
    public int size;

    // constructors
    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SingleLinkedList(SingleNode head) {
        this.head = head;
        this.tail = head;
        this.size++;
    }

    public void addAtEnd(Object value) {
        SingleNode newNode = new SingleNode(value);
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, Object element) {
        if (head == null) {
            if (index != 0) {
                System.out.println("ErrorA");
                System.exit(0);
            } else {
                this.add(element);
                return;
            }
        }
        if (index == 0) {
            this.add(element);
            return;
        } else if (index == size) {
            this.addAtEnd(element);
            return;
        }
        SingleNode temp = head;
        if (index <= size - 1 && index >= 0) {
            temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
        } else {
            System.out.println("ErrorB");
            System.exit(0);
        }
        SingleNode newNode = new SingleNode(element);
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        size++;
    }

    @Override
    public void add(Object element) {
        SingleNode newNode = new SingleNode(element);
        newNode.setNext(this.head);
        if (head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head = newNode;
        }
        size++;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("ErrorC");
            System.exit(0);
        }
        SingleNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public void set(int index, Object element) {

        if (index < 0 || index >= size) {
            System.out.println("ErrorD");
            System.exit(0);
        }
        SingleNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        temp.setValue(element);
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void remove() {
        if (this.head != null) {
            SingleNode temp = this.head;
            this.head = this.head.getNext();
            temp.setNext(null);
            temp = null;
            size--;
        }
    }

    @Override
    public void remove(int index) {
        if (this.head == null) {
            System.out.println("ErrorE");
            System.exit(0);
        }
        if (index == 0) {
            this.remove();
            return;
        }
        SingleNode temp = head;
        if (index <= size - 1 && index >= 0) {
            temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
        } else {
            System.out.println("ErrorF");
            System.exit(0);
        }
        SingleNode temp2 = temp.getNext();
        temp.setNext(temp2.getNext());
        temp2.setNext(null);
        temp2 = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            System.out.println("ErrorG");
            System.exit(0);
        }
        SingleNode temp = this.head;
        SingleNode temp2 = this.head;
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.getNext();
        }
        for (int i = 0; i < toIndex + 1; i++) {
            temp2 = temp2.getNext();
        }
        SingleLinkedList newList = new SingleLinkedList();

        while(temp != temp2)
        {
            newList.addAtEnd(temp.getValue()) ;
            temp = temp.getNext() ;
        }
        return newList ;
    }

    @Override
    public boolean contains(Object o) {
        SingleNode temp = head;
        while (temp != null) {
            if (temp.getValue() == o)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    public void print() {
        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }
        SingleNode temp = head;
        System.out.print("[");
        while (temp.getNext() != null) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
        System.out.print(temp.getValue() + "]");
        System.out.println();
    }

    public void take() {
        Scanner input = new Scanner(System.in) ;
        
        String str = input.nextLine().replaceAll("\\[|\\]", "");
        input.close();
        if(str.compareTo("") == 0)
        {
            this.clear();
            return;
        }
        //input.close() ;
        String[] str_array = str.split(", ") ;
        this.clear();
        for (String s : str_array) {
            this.addAtEnd(Integer.parseInt(s));
        }
    }
public static void main(String[] args){
    SingleLinkedList list = new SingleLinkedList();
    Scanner input = new Scanner(System.in) ;
        
        String str = input.nextLine().replaceAll("\\[|\\]", "");
        if(str.compareTo("") == 0)
        {
            list.clear();
        }
    else{
        String[] str_array = str.split(", ") ;
        list.clear();
        for (String s : str_array) {
            list.addAtEnd(Integer.parseInt(s));
        }
        
    }
        
        
    str = input.nextLine();

    if(str.compareTo("isEmpty") == 0) {
        if(list.isEmpty())
        {
            System.out.println("True");
        }   
        else
        {
            System.out.println("False");
        }
        input.close();
        return;
    }
    else if(str.compareTo("add") == 0){
        int num = input.nextInt();
        list.addAtEnd(num);
        list.print();
        input.close();
        return;
    }
    else if(str.compareTo("addToIndex") == 0){
        int index = input.nextInt();
        int num = input.nextInt();
        list.add(index, num);
        list.print();
        input.close();
        return;
    }
    else if(str.compareTo("set") == 0)
    {
        int index = input.nextInt();
        int num = input.nextInt();
        list.set(index, num);
        list.print();
        input.close();
        return;
    }
    else if(str.compareTo("get") == 0)
    {
        int index = input.nextInt();
        System.out.println(list.get(index));
        input.close();
        return;
    }
    else if(str.compareTo("size") == 0)
    {
        System.out.println(list.size());
        input.close();
        return;
    }
    else if(str.compareTo("contains") == 0)
    {
        int index = input.nextInt();
        if(list.contains(index))
        {
            System.out.println("True");
        }   
        else
        {
            System.out.println("False");
        }
        input.close();
        return;
    }
    else if(str.compareTo("sublist") == 0)
    {
        int fromIndex = input.nextInt();
        int toIndex = input.nextInt();
        SingleLinkedList list2 = new SingleLinkedList();
        list2 = (SingleLinkedList) list.sublist(fromIndex, toIndex);
        list2.print();
        input.close();
        return;
    }
    else if(str.compareTo("remove") == 0)
    {
        
        int index = input.nextInt();
        list.remove(index);
        list.print();
        input.close();
        return;
    }
    else if(str.compareTo("clear") == 0)
    {
        list.clear();
        list.print();
        
    }
    
    input.close();
}

public int[] toArray() {
    int[] array = new int[this.size];
    SingleNode temp = head;

    for(int i = 0; i < array.length; i++)
    {
        array[i] = (Integer)temp.getValue();
        temp = temp.getNext();
    }
    return array;
}

}