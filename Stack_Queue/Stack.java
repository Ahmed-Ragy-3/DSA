package Stack_Queue ;
class node<T> {
   T data;
   node<T> next;

   public node(T data, node<T> next) {
       this.data = data;
       this.next = next;
   }
   public node(T data) {
    this.data = data;
    this.next = null;
}
}

public class Stack<T> /*implements IStack<T>*/ { // make the Istack interface as in the Lab
   private node<T> top;

   public boolean isEmpty() {
       return (top == null);
   }

   public void push(node<T> node) {
       node.next = top;
       top = node;
   }

   public node<T> pop() {
       if (isEmpty()) {
           return null; // underflow error
       }
       node<T> temp = top;
       top = top.next;
       return temp;
   }

   public node<T> peek() {
       return top;
   }

   public void clear() {
       top = null;
   }

   public int size() {
       node<T> temp = peek();
       int s = 0;
       while (temp != null) {
           s++;
           temp = temp.next;
       }
       return s;
   }

   public void print_stack() {
      node<T> temp = top ;
      System.out.println(" ------ ");
      while(temp != null) {
         System.out.println("|  " + temp.data + "  |") ;
         temp = temp.next ;
      }
      System.out.println(" ------ ");
   }

   public static void main(String[] args) {
      Stack<Character> s1 = new Stack<>() ;
      s1.push(new node<Character>('o', null)) ;
      s1.print_stack();

      s1.push(new node<Character>('o', null)) ;
      s1.print_stack() ;

      s1.push(new node<Character>('o', null)) ;
      s1.print_stack() ;

      s1.pop() ;
      s1.print_stack() ;

      s1.push(new node<Character>('o', null)) ;
      s1.print_stack() ;

      System.out.println("Top = " + s1.peek().data) ;
      s1.pop() ;
      s1.print_stack() ;
   }

}

