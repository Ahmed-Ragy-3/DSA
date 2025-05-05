package Trees;

import java.util.*;

// red black tree visualization: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html

/**
 * Implementation of Red Black Binary Search Tree
 * Red Black Tree is a BST such that:
 * - No node has two red links connected to it.
 * - Every path from root to null link has the same number of black links.
 * (Perfect Balance)
 * - Red links lean left.
 */
public class RedBlackBST<K extends Comparable<K>, V> implements Iterable<K> {

   private class RedBlackNode {
      public static final boolean RED = true;
      public static final boolean BLACK = false;

      public K key;
      public V value;

      public RedBlackNode left, right;
      public boolean color; // color of parent link

      public RedBlackNode(K key, V value) {
         this.key = key;
         this.value = value;
         this.color = RED;
         this.left = null;
         this.right = null;
      }

      public boolean isRed() {
         return color == RED;
      }
   }

   public RedBlackNode root;

   public V get(K key) {
      RedBlackNode temp = root;
      while (temp != null) {
         int cmp = key.compareTo(temp.key);
         if (cmp < 0)
            temp = temp.left;

         else if (cmp > 0)
            temp = temp.right;

         else
            return temp.value;
      }
      return null;
   }

   /**
    * Orient a left-leaning red link to (temporarily) lean right
    * Called when there is 2 red left links in a row
    */
   private RedBlackNode rotateRight(RedBlackNode node) {
      assert node.left != null && node.left.isRed(); // fixed condition
      RedBlackNode x = node.left;
      node.left = x.right;
      x.right = node;

      x.color = node.color;
      node.color = RedBlackNode.RED;

      return x;
   }

   /**
    * Orient a (temporarily) right-leaning red link to lean left
    * Called when there is a red right link
    */
   private RedBlackNode rotateLeft(RedBlackNode node) {
      assert node.right != null && node.right.isRed(); // null-check added
      RedBlackNode x = node.right;
      node.right = x.left;
      x.left = node;

      x.color = node.color;
      node.color = RedBlackNode.RED;

      return x;
   }

   private void flipColors(RedBlackNode node) {
      assert node != null && node.left != null && node.right != null;
      assert !node.isRed() && node.left.isRed() && node.right.isRed(); // updated logic

      node.color = RedBlackNode.RED;
      node.left.color = RedBlackNode.BLACK;
      node.right.color = RedBlackNode.BLACK;
   }

   public void insert(K key, V value) {
      root = insert(root, key, value); // fixed: assign to root
      root.color = RedBlackNode.BLACK; // root must always be black
   }

   private RedBlackNode insert(RedBlackNode node, K key, V value) {
      if (node == null)
         return new RedBlackNode(key, value);

      int cmp = key.compareTo(node.key);
      if (cmp < 0)
         node.left = insert(node.left, key, value); // fixed recursion
      else if (cmp > 0)
         node.right = insert(node.right, key, value);
      else
         node.value = value;

      if (node.right != null && node.right.isRed() && (node.left == null || !node.left.isRed()))
         node = rotateLeft(node);

      if (node.left != null && node.left.isRed() && node.left.left != null && node.left.left.isRed())
         node = rotateRight(node);

      if (node.left != null && node.left.isRed() && node.right != null && node.right.isRed())
         flipColors(node);

      return node;
   }

   /**
    * This deletion is implemented by Hibbard deletion
    * Which is much more simpler
    * Although this may not guarantee balance of the BST
    *
    * @param key of the node to be deleted
    * @return node deleted from the RedBlack BST
    */
   public V delete(K key) {
      RedBlackNode deleted = delete(root, key);
      if (deleted == null) return null;
      return deleted.value;
   }

   private RedBlackNode delete(RedBlackNode node, K key) {
      if (node == null)
         return null;

      int cmp = key.compareTo(node.key);
      if (cmp < 0)
         node.left = delete(node.left, key);

      else if (cmp > 0)
         node.right = delete(node.right, key);

      else {
         if (node.left == null)
            return node.right;
         if (node.right == null)
            return node.left;

         RedBlackNode maxLeft = findMax(node.left);
         node.key = maxLeft.key;
         node.value = maxLeft.value;
         node.left = deleteMax(node.left);
      }

      return node;
   }

   private RedBlackNode findMax(RedBlackNode node) {
      while (node.right != null)
         node = node.right;
      return node;
   }

   private RedBlackNode deleteMax(RedBlackNode node) {
      if (node.right == null)
         return node.left;
      node.right = deleteMax(node.right);
      return node;
   }

   /**
    * Returns an iterator that traverses keys in-order (ascending).
    */
   @Override
   public Iterator<K> iterator() {
      return new Iterator<K>() {
         private Stack<RedBlackNode> stack = new Stack<>();

         {
            pushLeft(root);
         }

         private void pushLeft(RedBlackNode node) {
            while (node != null) {
               stack.push(node);
               node = node.left;
            }
         }

         @Override
         public boolean hasNext() {
            return !stack.isEmpty();
         }

         @Override
         public K next() {
            RedBlackNode node = stack.pop();
            pushLeft(node.right);
            return node.key;
         }
      };
   }
}
