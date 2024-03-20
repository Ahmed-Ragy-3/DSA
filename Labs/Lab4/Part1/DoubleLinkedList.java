package Labs.Lab4.Part1;

import java.util.Scanner;

public class DoubleLinkedList implements ILinkedList{

    @Override
    public void add(int index, Object element) {
        
        if(index < 0 || index >= size){
            throw new IllegalStateException("Error") ;
        }else if(index == size-1) {
            add(element);
            return ;
        }

        DoubleNode newNode = new DoubleNode(element) ;
        
        DoubleNode temp = head ;
        for(int i = 0 ; i < index - 1 ; i++){
            temp = temp.getNext();
        }

        newNode.setNext(temp.getNext()) ;
        temp.setNext(newNode) ;
        newNode.setPrev(temp) ;
        //temp = temp.getNext().getNext() ;
        newNode.getNext().setPrev(newNode) ;
        size++ ;
        
    }

    @Override
    public void add(Object element) {
        
        if(head == null && tail == null) {
            DoubleNode new_Node = new DoubleNode(element , null , null) ;
            head = new_Node ;
            tail = new_Node ;
        }else {
            DoubleNode new_Node = new DoubleNode(element , tail , null) ;
            tail = new_Node ;
        }
    }

    @Override
    public Object get(int index) {

        if(index < 0 || index >= size) {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
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
            throw new IllegalStateException("There is no index = " + index + ", the size is " + size) ;
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

    @Override
    
    public void remove(int index) {
        
        /*if(this.head == null || index >= size || index < 0) {
            throw new IllegalStateException("Error") ;
        }else if(index == 0) {
            this.head = head.getNext() ;
            return ;
        }

        DoubleNode temp = this.head ;
        //temp = this.head;
        for(int i = 0; i < index-1; i++) {
            temp = temp.getNext() ;
        }

        DoubleNode temp2 = temp.getNext() ;
        temp.setNext(temp2.getNext()) ;
        //temp2.setNext(null);
        temp2 = null ;
        size-- ;*/
    
    }
    

    @Override
    public int size() {
        return this.size ;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IllegalStateException("Error");
        }else if(fromIndex == 0 && toIndex == size) {
            return this ;
        }

        DoubleNode temp = this.head ;
        DoubleNode temp2 = this.head ;

        for(int i = 0 ; i < fromIndex ; i++) {
            temp = temp.getNext();
        }

        for(int i = 0 ; i < toIndex + 1 ; i++) {
            temp2 = temp2.getNext();
        }

        DoubleLinkedList newList = new DoubleLinkedList();
        
        while(temp != temp2) {
            newList.add(temp.getValue()) ;
            temp = temp.getNext() ;
        }
        return newList ;
    }

    @Override
    public boolean contains(Object o) {
        if(isEmpty()) {
            return false ;
        }

        DoubleNode temp = head ;
        while (temp != null) {
            if(temp.getValue() == o) {
                return true ;
            }
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
    
    public DoubleLinkedList take() {

        Scanner input = new Scanner(System.in) ;
        String str = input.nextLine() ;
        input.close() ;
        str.replaceAll("[|]", "") ;
        String[] str_array = str.split(", ") ;

        DoubleLinkedList list = new DoubleLinkedList() ;
        for (String s : str_array) {
            list.add(Integer.parseInt(s)) ;
        }
        return list ;
    }

    public void main(String[] args) {
        DoubleLinkedList list1 = take() ;
        list1.print() ;
    }
}
