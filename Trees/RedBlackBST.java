package Trees;

// red black tree visualization: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html

import java.util.Objects;

interface IRedBlackNode {
   public boolean isRed();
}

interface IRedBlackBST<K extends Comparable<K>, V> {
   public V get(K key);
   RedBlackNode<K, V> rotateRight(RedBlackNode<K, V> node);
   RedBlackNode<K, V> rotateLeft(RedBlackNode<K, V> node);
   RedBlackNode<K, V> flipColors(RedBlackNode<K, V> node);
   RedBlackNode<K, V> put(K key, V value);
}

class RedBlackNode<K extends Comparable<K>, V> {

   public static final boolean RED = true;
   public static final boolean BLACK = false;

   private K key;
   private V value;

   public RedBlackNode<K, V> getRight() {
      return right;
   }

   public RedBlackNode<K, V> getLeft() {
      return left;
   }

   private RedBlackNode<K, V> left, right;
   private boolean color;  // color of parent link

   public K getKey() {
      return key;
   }

   public V getValue() {
      return value;
   }

   public boolean isColor() {
      return color;
   }

   public boolean isRed() {
      return color == RED;
   }

   public final int hashCode() {
      return Objects.hashCode(key) ^ Objects.hashCode(value);
   }

   public void setKey(K key) {
      this.key = key;
   }

   public void setValue(V value) {
      this.value = value;
   }

   public void setLeft(RedBlackNode<K, V> left) {
      this.left = left;
   }

   public void setRight(RedBlackNode<K, V> right) {
      this.right = right;
   }

   public void setColor(boolean color) {
      this.color = color;
   }
}


/**
 * implementation of red back binary search tree
 *  Red Black Tree is a BST such that:
 *    • No node has two red links connected to it.
 *    • Every path from root to null link has the same number of black links. (Perfect Balance)
 *    • Red links lean left.
 */
public class RedBlackBST<K extends Comparable<K>, V> implements IRedBlackBST<K, V> {

   private RedBlackNode<K, V> root;

   @Override
   public V get(K key) {
      RedBlackNode<K, V> temp = root;
      while (temp != null) {
         int cmp = key.compareTo(temp.getKey());
         if      (cmp  < 0) temp = temp.getLeft();
         else if (cmp  > 0) temp = temp.getRight();
         else if (cmp == 0) return temp.getValue();
      }
      return null;
   }

   /**
    * Orient a left-leaning red link to (temporarily) lean right
    * Called when there is 2 red left links in a row
    */
   @Override
   public RedBlackNode<K, V> rotateRight(RedBlackNode<K, V> node) {
      assert node.getRight().isRed();

      RedBlackNode<K, V> x = node.getLeft();
      node.setLeft(x.getRight());
      x.setRight(node);

      node.setColor(RedBlackNode.RED);
      x.setColor(node.isColor());

      return x;
   }

   /**
    * Orient a (temporarily) right-leaning red link to lean left
    * Called when there is a red right link
    */
   @Override
   public RedBlackNode<K, V> rotateLeft(RedBlackNode<K, V> node) {
      assert node.getRight().isRed();

      RedBlackNode<K, V> x = node.getRight();
      node.setRight(x.getLeft());
      x.setLeft(node);
      x.setColor(node.isColor());
      node.setColor(RedBlackNode.RED);

      return x;
   }

   @Override
   public RedBlackNode<K, V> flipColors(RedBlackNode<K, V> node) {
      assert node.isRed() && node.getLeft().isRed() && node.getRight().isRed();

      node.setColor(RedBlackNode.RED);
      node.getLeft().setColor(RedBlackNode.BLACK);
      node.getRight().setColor(RedBlackNode.BLACK);

      return node;
   }

   @Override
   public RedBlackNode<K, V> put(K key, V value) {
      RedBlackNode<K, V> node = new RedBlackNode<>();
      return null;
   }


}
