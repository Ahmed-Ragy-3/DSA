package Stack_Queue;
class node {
   int value ;
   node next ;

   public node (int v , node n) {
      this.value = v ;
      this.next = n ;
   }
}

public class Stack {
   node top ;

   public Stack() {

   }
   boolean isempty() {
      return (top == null) ;
   }

   void push(node n) {
      n.next = top ;
      top = n ;
   }

   node pop() {
      node temp = top ;
      top = top.next ;
      return temp ;
   }

   node Top() {
      return top ;
   }
   void print_stack() {
      node temp = top ;
      System.out.println(" ------ ");
      while(temp != null) {
         System.out.println("|  " + temp.value + "  |") ;
         temp = temp.next ;
      }
      System.out.println(" ------ ");
   }

   public static void main(String[] args) {
      Stack s1 = new Stack() ;
      s1.push(new node(15, null)) ;
      s1.print_stack();

      s1.push(new node(20, null)) ;
      s1.print_stack() ;

      s1.push(new node(50, null)) ;
      s1.print_stack() ;

      s1.pop() ;
      s1.print_stack() ;

      s1.push(new node(90, null)) ;
      s1.print_stack() ;

      System.out.println("Top = " + s1.Top().value) ;
      s1.pop() ;
      s1.print_stack() ;
   }
}
