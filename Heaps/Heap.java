package Heaps;

public interface Heap<T> {
   public void insert(T data);

   public T deleteTop();

   public T peek();

   public void printHeap();

   public boolean isEmpty();
}
