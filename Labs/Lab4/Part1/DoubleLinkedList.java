package Labs.Lab4.Part1;

import java.util.Scanner;

public class DoubleLinkedList implements ILinkedList {
    
    private DoubleNode head ;
    private DoubleNode tail ;
    private int size ;
    Object value;
    
    //constructors
    public DoubleLinkedList() {
        this.head = null ;
        this.tail = null ;
        this.size = 0 ;
    }
    public DoubleLinkedList(DoubleNode head) {
        this.head = head ;
        this.tail = head ;
        this.size++ ;
    }

    @Override
    public void add(int index, Object element) {
        if(index < 0 || index > size)
        {
            System.out.println("Error") ;
            System.exit(0) ;
        }
        DoubleNode newNode = new DoubleNode(element);
        if(index == 0)
        {
            addFirst(element);
            return;
        }
        if(index == size)
        {
            add(element);
            return;
        }
        DoubleNode temp = head;
        for(int i = 0; i < index - 1; i++)
        {
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
    public void addFirst(Object value) {
        DoubleNode newNode = new DoubleNode(value);
        if(head == null) {
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
    @Override
    public void add(Object element) {
        
        if(tail == null) {
            addFirst(element);
            return;
        }
        DoubleNode newNode = new DoubleNode(element);

        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
        size++;
        return;
    }

    @Override
    public Object get(int index) {

        if(index < 0 || index >= size) {
            System.out.println("Error") ;
            System.exit(0) ;
        }
        
        DoubleNode temp = head ;

        for(int i = 0 ; i < index ; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public void set(int index, Object element) {
        
        if(index < 0 || index >= size){
            System.out.println("Error") ;
            System.exit(0) ;
        }
        
        DoubleNode temp = head ;
        for(int i = 0 ; i < index ; i++){
            temp = temp.getNext();
        }

        temp.setValue(element);
    
    }

    @Override
    public void clear() {
        this.head = null ;
        this.tail = null ;
        this.size = 0 ;
    }

    @Override
    public boolean isEmpty() {
        return (head == null)  ;
    }

    public void remove() {
        if (this.head != null) {
            DoubleNode temp = this.head ;
            this.head = this.head.getNext();
            temp.setNext(null);
            temp = null;
            size--;
        }
    }

    @Override
    public void remove(int index) {
        if (this.head == null) {
            System.out.println("Error");
            System.exit(0);
        }
        if (index == 0) {
            this.remove();
            return;
        }
        DoubleNode temp = head;
        if (index <= size - 1 && index >= 0) {
            temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
        } else {
            System.out.println("Error");
            System.exit(0);
        }
        DoubleNode temp2 = temp.getNext();
        temp.setNext(temp2.getNext());
        temp2.setNext(null);
        temp2 = null;
        size--;
    }
    

    @Override
    public int size() {
        return this.size ;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            System.out.println("Error");
            System.exit(0);
        }
        DoubleNode temp = this.head ;
        DoubleNode temp2 = this.head ;
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.getNext();
        }
        for (int i = 0; i < toIndex + 1; i++) {
            temp2 = temp2.getNext();
        }
        DoubleLinkedList newList = new DoubleLinkedList();

        while(temp != temp2)
        {
            newList.add(temp.getValue()) ;
            temp = temp.getNext() ;
        }
        return newList ;
    }

    @Override
    public boolean contains(Object o) {
        DoubleNode temp = head ;
        while (temp != null) {
            if (temp.getValue() == o)
                return true ;
            temp = temp.getNext();
        }
        return false ;
    }

    public void print() {

        if(this.isEmpty()) {
            System.out.println("[]");
            return;
        }
        DoubleNode temp = head ;
        System.out.print("[") ;

        while(temp.getNext() != null) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }

        System.out.println(temp.getValue() + "]");
        //System.out.println();
    }


    public static void main(String[] args) {

        DoubleLinkedList list = new DoubleLinkedList();
        Scanner input = new Scanner(System.in) ;
            
            String str = input.nextLine().replaceAll("\\[|\\]", "");
            if(str.compareTo("") == 0) {
                list.clear();
            }else{
            String[] str_array = str.split(", ") ;
            list.clear();
            for (String s : str_array) {
                list.add(Integer.parseInt(s));
            }
            
        }
            
            
        str = input.nextLine();
    
        if(str.compareTo("isEmpty") == 0) {
            if(list.isEmpty()) {
                System.out.println("True");
            }else {
                System.out.println("False");
            }
            input.close();
            return;
        }else if(str.compareTo("add") == 0) {
            int num = input.nextInt();
            list.add(num);
            list.print();
            input.close();
            return;
        }else if(str.compareTo("addToIndex") == 0) {
            int index = input.nextInt();
            int num = input.nextInt();
            list.add(index, num);
            list.print();
            input.close();
            return;
        }else if(str.compareTo("set") == 0) {
            int index = input.nextInt();
            int num = input.nextInt();
            list.set(index, num);
            list.print();
            input.close();
            return;
        }else if(str.compareTo("get") == 0) {
            int index = input.nextInt();
            System.out.println(list.get(index));
            input.close();
            return;
        }else if(str.compareTo("size") == 0) {
            System.out.println(list.size());
            input.close();
            return;
        }else if(str.compareTo("contains") == 0) {
            int index = input.nextInt();
            if(list.contains(index)){
                System.out.println("True");
            }else{
                System.out.println("False");
            }
            input.close();
            return;
        }else if(str.compareTo("sublist") == 0) {
            int fromIndex = input.nextInt();
            int toIndex = input.nextInt();
            DoubleLinkedList list2 = new DoubleLinkedList();
            list2 = (DoubleLinkedList) list.sublist(fromIndex, toIndex);
            list2.print();
            input.close();
            return;
        }else if(str.compareTo("remove") == 0) {
            
            int index = input.nextInt();
            list.remove(index);
            list.print();
            input.close();
            return;
        }else if(str.compareTo("clear") == 0) {
            list.clear();
            list.print();
            
        }
        
        input.close();
    }
    
}