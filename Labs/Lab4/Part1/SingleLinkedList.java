package Labs.Lab4.Part1;
public class SingleLinkedList implements ILinkedList{

    private SingleNode head;
    private SingleNode tail;
    private int size;

    //constructors
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
        if(this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, Object element) {
        if(head == null) {
            if(index != 0) {
                throw new IllegalStateException("There is no index " + index + ", the size is " + size);
            }
            else {
                this.add(element);
                return;
            }
        }
        if(index == 0) {
            this.add(element);
            return;
        }
        else if(index == size) {
            this.addAtEnd(element);
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
        SingleNode newnNode = new SingleNode(element);
        newnNode.setNext(temp.getNext());
        temp.setNext(newnNode);
        size++;
    }

    @Override
    public void add(Object element) {
        SingleNode newNode = new SingleNode(element);
        newNode.setNext(this.head);
        if(head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.head = newNode;
        }
        size++;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index >= size)
        {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
        }
        SingleNode temp = head;
        for(int i = 0; i < index; i++)
        {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public void set(int index, Object element) {
        
        if(index < 0 || index >= size)
        {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
        }
        SingleNode temp = head;
        for(int i = 0; i < index; i++)
        {
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
        if(this.head != null) {
            SingleNode temp = this.head;
            this.head = this.head.getNext();
            temp.setNext(null);
            temp = null;
            size--;
        }
    }

    @Override
    public void remove(int index) {
        if(this.head == null) {
            throw new IllegalStateException("There is no index " + index + ", the size is " + size);
        }
        if(index == 0) {
            this.remove();
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
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > size || fromIndex > toIndex)
        {
            throw new IllegalStateException("There is an error in indexing, the size is " + size);
        }
        if(fromIndex == 0 && toIndex == size)
        {
            return this;
        }
        SingleNode temp = this.head;
        SingleNode temp2 = this.head;
        for(int i = 0; i < fromIndex; i++)
        {
            temp = temp.getNext();
        }
        for(int i = 0; i < toIndex + 1; i++)
        {
            temp2 = temp2.getNext();
        }
        SingleLinkedList newList = new SingleLinkedList();
        while(temp != temp2)
        {
            newList.addAtEnd(temp.getValue());
            temp = temp.getNext();
        }
        return newList;
    }

    @Override
    public boolean contains(Object o) {
        SingleNode temp = head;
        while(temp != null) {
            if(temp.getValue() == o) return true;
            temp = temp.getNext();
        }
        return false;
    }

    public void print() {
        if(this.isEmpty())
        {
            System.out.println("[]");
            return;
        }
        SingleNode temp = head;
        System.out.print("[");
        while(temp.getNext()!= null)
        {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
        System.out.print(temp.getValue() + "]");
        System.out.println();
    }
}
