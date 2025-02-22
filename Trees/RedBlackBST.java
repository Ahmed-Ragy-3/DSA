package Trees;

interface IRedBlackNode {
   public boolean isRed();
}

interface IRedBlackBST<K, V> {
   public void rotateRight(RedBlackNode<K, V> node);
   public void rotateLeft(RedBlackNode<K, V> node);
   public void flipColors(RedBlackNode<K, V> node);
}

class RedBlackNode<K, V> {
   private K key;
   private V value;
   private boolean color;
   
   public boolean isRed() {
      return color;
   }
}

public class RedBlackBST<K, V> implements IRedBlackBST<K, V> {

   @Override
   public void rotateRight(RedBlackNode<K, V> node) {
      throw new UnsupportedOperationException("Unimplemented method 'rotateRight'");
   }

   @Override
   public void rotateLeft(RedBlackNode<K, V> node) {
      throw new UnsupportedOperationException("Unimplemented method 'rotateLeft'");
   }

   @Override
   public void flipColors(RedBlackNode<K, V> node) {
      throw new UnsupportedOperationException("Unimplemented method 'flipColors'");
   }

}
