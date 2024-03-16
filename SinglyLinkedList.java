class SingleNode {
    static private int numberOfNodes = 0;
    private int value;
    private SingleNode next;
    
    
    public SingleNode(int value) {
        this.value = value;
        this.next = null;
        numberOfNodes++;
    }
    public SingleNode(int value, SingleNode next) {
        this.value = value;
        this.next = next;
        numberOfNodes++;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public SingleNode getNext() {
        return next;
    }
    public void setNext(SingleNode next) {
        this.next = next;
    }

    static int getNumOfNodes() {
        return numberOfNodes;
    }
}


public class SinglyLinkedList { //this is a singly linked list that has head and tail pointers
    //attributes
    private SingleNode head;
    private SingleNode tail;
    private int size;

    //constructors
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public SinglyLinkedList(SingleNode head) {
        this.head = head;
        this.tail = head;
        this.size++;
    }

    //size queries
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }

    //adding nodes
    public void addAtBeginning(int value) {
        SingleNode newNode = new SingleNode(value);
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

    public void addAtEnd(int value) {
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
    public void addInSortedOrder(int value)
    {
        SingleNode newNode = new SingleNode(value);
        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            SingleNode temp = this.head;
            while(temp.getNext()!= null && temp.getNext().getValue() < value) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
        size++;
    }

    void deleteAllOccurances(int value)
    {
        if(this.head == null) {
            return;
        }
        if(head.getValue() == value) {
            head = head.getNext();
        }


        SingleNode temp = this.head;
        while(temp != null && temp.getNext() != null) {
            if(temp.getNext().getValue() == value) {
                temp.setNext(temp.getNext().getNext());
            }
            else {
                temp = temp.getNext();
            }
            
        }
    }
    public void addAtIndex(int index, int value) {
        if(head == null) {
            if(index != 0) {
                System.out.println("Error");
                return;
            }
            else {
                this.addAtBeginning(value);
                return;
            }
        }
        if(index == 0) {
            this.addAtBeginning(value);
            return;
        }
        else if(index == size) {
            this.addAtEnd(value);
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
            System.out.println("Error");
            return;
        }
        SingleNode newnNode = new SingleNode(value);
        newnNode.setNext(temp.getNext());
        temp.setNext(newnNode);

        size++;
    }

    //deleting nodes
    public void deleteAtBegining() {
        if(this.head != null) {
            SingleNode temp = this.head;
            this.head = this.head.getNext();
            temp.setNext(null);
            temp = null;
            size--;
        }
    }
    public void deleteAtEnd() {
        if(this.head != null) {
            SingleNode temp = this.head;
            while(temp.getNext() != tail) {
                temp = temp.getNext();
            }
            temp.setNext(null);
            temp = null;
        }
    }
    public void deleteAtIndex(int index) {
        if(this.head == null) {
            System.out.println("Error");
            return;
        }
        if(index == 0) {
            this.deleteAtBegining();
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
            System.out.println("Error");
            return;
        }
        SingleNode temp2 = temp.getNext();
        temp.setNext(temp2.getNext());
        temp2.setNext(null);
        temp2 = null;
        size--;
    }

    //clearing
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //printing
    public void printValues() {
        SingleNode temp = this.head;
        int counter = 0;
        if(temp == null) {
            System.out.println("Empty.");
            return;
        }
        while(temp!= null) {
            System.out.println("IndexAt " + counter + ": " + temp.getValue());
            counter++;
            temp = temp.getNext();
        }
        
    }

    //copying
    public void copy(SinglyLinkedList source) {
        this.clear();
        SingleNode temp = source.head;
        while(temp!= null) {
            this.addAtEnd(temp.getValue());
            temp = temp.getNext();
        }
    }

    public static SingleNode copyRecurise(SingleNode f1, SingleNode f2)
    {
        if(f1 == null)
        {
            return null;
        }
        if(f2 == null)
        {
            f2 = new SingleNode(f1.getValue());
            f2.setNext(copyRecurise(f1.getNext(), f2));
        }
        else{
            f2.setNext(copyRecurise(f1.getNext(), f2.getNext()));
            return new SingleNode(f1.getValue());
        }
        return null;
    }

    //joining
    public void join(SinglyLinkedList source) {
        if(this.head == null) {
            this.copy(source);
            return;
        }
        if(source.head == null) {
            return;
        }
        SingleNode temp = source.head;
        while(temp != null) {
            this.addAtEnd(temp.getValue());
            temp = temp.getNext();
        }
    }

    //comparing
    public boolean compare(SinglyLinkedList source) {
        if(source.size != this.size)
        {
            return false;
        }
        SingleNode temp1 = this.head;
        SingleNode temp2 = source.head;
        while(temp1 != null)
        {
            if(temp1.getValue() != temp2.getValue()){
                return false;
            }
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        return true;
    }
    
    //accessing
    public int getNodeAt(int index) {
        if(this.head == null) {
            System.out.println("Error");
            return -1;
        }
        if(index == 0) {
            return this.head.getValue();
        }
        SingleNode temp;
        if(index <= size - 1 && index >= 0) {
            temp = this.head;
            for(int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
        }
        else {
            System.out.println("Error");
            return -1;
        }
        return temp.getValue();
    }
    public static void main(String[] args) {
        
        SingleNode node = new SingleNode(1);
        node.setNext(new SingleNode(4));
        node.getNext().setNext(new SingleNode(2));
        node.getNext().getNext().setNext(new SingleNode(3));
        node.getNext().getNext().getNext().setNext(new SingleNode(5));
        SingleNode node2 = null;
        copyRecurise(node, node2);
        SingleNode temp = node2;
        while(temp != null) {
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }

        

        
    }
}