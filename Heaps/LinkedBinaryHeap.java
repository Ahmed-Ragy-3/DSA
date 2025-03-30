package Heaps;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBinaryHeap<T> implements Heap<T> {
   private class Node {
      T data;
      Node left, right, parent;
      
      Node(T data) {
         this.data = data;
         left = right = parent = null;
      }
   }
   
   private Node root;
   private Comparator<T> comparator;
   private int size;

   public LinkedBinaryHeap(Comparator<T> comparator) {
      this.root = null;
      this.comparator = comparator;
      this.size = 0;
   }

   private void swap(Node x, Node y) {
      T temp = x.data;
      x.data = y.data;
      y.data = temp;
   }

   @Override
   public void insert(T data) {
      Node newNode = new Node(data);

      if (root == null) {
         root = newNode;
      } else {
         Node parent = findInsertionParent();
         if (parent.left == null)
            parent.left = newNode;
         else
            parent.right = newNode;
         
         newNode.parent = parent;
         bubbleUp(newNode);
      }
      size++;
   }

   /**
    * Finds the parent node where the next insertion should happen
    * Heap tree is complete except the last level
    * Parent of the next node to be inserted has at least one null child
    * @return parent of the new node to be inserted
    */
   private Node findInsertionParent() {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
         Node current = queue.poll();
         if (current.left == null || current.right == null)
            return current;
         
         queue.add(current.left);
         queue.add(current.right);
      }
      return null; // Should never happen
   }

   @Override
   public T deleteTop() {
      if(root == null) return null;

      T data = root.data;
      if(size == 1) {
         root = null;
      } else {
         Node last = getLastInsertedNode();
         swap(root, last);
         
         if (last.parent.left == last)    // last is a left child
            last.parent.left = null;
         else                             // last is a right child
            last.parent.right = null;

         bubbleDown(last);
      }

      size--;
      return data;
   }

   /** Finds the last inserted node in level-order */
   private Node getLastInsertedNode() {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      Node lastNode = null;

      while (!queue.isEmpty()) {
         lastNode = queue.poll();

         if (lastNode.left != null)
            queue.add(lastNode.left);
         
         if (lastNode.right != null)
            queue.add(lastNode.right);
      }
      return lastNode;
   }

   @Override
   public T peek() {
      return root.data;
   }

   /** Helper to move node up if it's in the wrong position */
   private void bubbleUp(Node node) {
      while (node.parent != null && comparator.compare(node.data, node.parent.data) < 0) {
         // child is smaller than parent, so swap
         swap(node, node.parent);
         node = node.parent;
      }
   }

   /** Helper to move node down if it's in the wrong position */
   private void bubbleDown(Node node) {
      while (node.left != null) {
         Node smallest = node.left;
         if (node.right != null && comparator.compare(node.right.data, node.left.data) < 0) {
            // right < left
            smallest = node.right;
         }

         if (comparator.compare(node.data, smallest.data) > 0) {
            swap(node, smallest);
            node = smallest;
         } else {
            break;
         }
      }
   }

   /**
    * Prints the heap in level order traversal
   */
   @Override
   public void printHeap() {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      
      while (!queue.isEmpty()) {
         Node current = queue.poll();
         System.out.print(current.data + " ");
         if (current.left != null)  queue.add(current.left);
         if (current.right != null) queue.add(current.right);
      }

      System.out.println();
   }

   @Override
   public boolean isEmpty() {
      return this.size == 0;
   }

   // testing main
   public static void main(String[] args) {
      // Min-Heap (smallest element at the root)
      Comparator<Integer> minComparator = Integer::compareTo;
      LinkedBinaryHeap<Integer> minHeap = new LinkedBinaryHeap<>(minComparator);

      minHeap.insert(10);
      minHeap.insert(5);
      minHeap.insert(15);
      minHeap.insert(3);
      minHeap.insert(8);
      minHeap.insert(20);

      System.out.print("Min Heap: ");
      minHeap.printHeap(); // Level-order print

      System.out.println("Removed root (min): " + minHeap.deleteTop());
      System.out.print("After removal: ");
      minHeap.printHeap();

      // Max-Heap (largest element at the root)
      Comparator<Integer> maxComparator = (a, b) -> b.compareTo(a);
      Heap<Integer> maxHeap = new LinkedBinaryHeap<>(maxComparator);

      maxHeap.insert(10);
      maxHeap.insert(5);
      maxHeap.insert(15);
      maxHeap.insert(3);
      maxHeap.insert(8);
      maxHeap.insert(20);

      System.out.print("\nMax Heap: ");
      maxHeap.printHeap();

      System.out.println("Removed root (max): " + maxHeap.deleteTop());
      System.out.print("After removal: ");
      maxHeap.printHeap();
   }

}
