package Labs.Lab4.Part1;

public class DoubleLinkedList implements ILinkedList{

    @Override
    public void add(int index, Object element) {
        
        if(index < 0 || index > size){
            System.out.println("There is no index " + index);
            return ;
        }
        DoubleNode newNode = new DoubleNode(value);
        if(index == 0) {
            addFirst(value);
            return ;
        }
        if(index == size) {
            addLast(value);
            return ;
        }
        DoubleNode temp = head;
        for(int i = 0; i < index - 1; i++){
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

    @Override
    public void add(Object element) {
        DoubleNode new_Node = new DoubleNode(element , tail , null) ;
        tail = new_Node ;
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
        
        if(this.head == null) {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
        }
        if(index == 0) {
            this.remove() ;
            return;
        }
        SingleNode temp;
        if(index <= size - 1 && index >= 0) {
            temp = this.head;
            for(int i = 0; i < index-1; i++) {
                temp = temp.getNext();
            }
        }
        else {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
        }
        SingleNode temp2 = temp.getNext();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sublist'");
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

}
