package Trees.BBST;

import java.util.*;

/**
 * This class represents a Binary Search Tree (BST) that supports various tree operations such as insertion, deletion, and traversal.
 * It is a generic class where T is the type of the elements stored in the tree. The tree assumes that the elements are comparable.
 *
 * The class provides basic BST functionality, including:
 * 1. Insertion of elements while maintaining the binary search tree property.
 * 2. Deletion of elements while ensuring the BST property is preserved.
 * 3. Methods for finding the minimum and maximum elements.
 * 4. Height management to track the height of the tree nodes.
 * 5. Several traversal methods (in-order, pre-order, post-order) to visit nodes.
 * 6. An iterator to traverse the BST.
 *
 * The tree can be modified to implement various types of balanced BSTs (e.g., AVL tree, Red-Black tree) by overriding
 * certain methods such as insert and delete.
 *
 * @param <T> The type of elements stored in the tree. It must be Comparable.
 */
public abstract class BST<T extends Comparable<T>> implements Iterable<T> {

    // Constant for the height of an empty tree node
    private static final int EMPTY_HEIGHT = -1;

    // The root node of the tree
    protected Node<T> root;

    // The number of elements in the tree
    protected int size;

    // A comparator for custom sorting (optional)
    protected final Comparator<? super T> comparator;

    /**
     * This abstract method is intended to delete a node from the tree.
     * It is designed to be overridden by subclasses to implement specific deletion logic.
     *
     * @param node The node to delete.
     * @param key The key of the node to delete.
     * @return The new root of the subtree.
     */
    protected abstract Node<T> delete(Node<T> node, T key);

    /**
     * This abstract method is intended to insert a new node into the tree.
     * It is designed to be overridden by subclasses to implement specific insertion logic.
     *
     * @param node The root node of the subtree to insert the element into.
     * @param key The key of the element to insert.
     * @return The new root of the subtree.
     */
    protected abstract Node<T> insert(Node<T> node, T key);

    // Constructors

    /**
     * Creates a BST with a custom comparator.
     *
     * @param comparator The comparator used for comparing elements in the tree.
     */
    public BST(Comparator<? super T> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Creates a BST with the default comparator (natural ordering).
     */
    public BST() {
        this(null);
    }

    /**
     * Node class that represents each node in the BST.
     * Contains a key (the value), left and right child pointers, and the height of the node.
     */
    protected static class Node<T> {
        T key;
        int height;  // Height of the node in the tree
        Node<T> left, right;

        Node(T key) {
            this.key = key;
            this.height = 0;  // Initially, the height of a leaf node is 0
        }
    }

    /**
     * Iterator for the BST that allows in-order traversal of the tree.
     */
    protected static class BSTIterator<T> implements Iterator<T> {
        private final Deque<Node<T>> stack = new ArrayDeque<>();

        // Initialize the iterator by pushing all left nodes onto the stack
        BSTIterator(Node<T> root) {
            pushLeft(root);
        }

        private void pushLeft(AVLTree.Node<T> n) {
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            AVLTree.Node<T> cur = stack.pop();
            pushLeft(cur.right);
            return cur.key;
        }
    }

    // Public Methods

    /**
     * Returns the number of nodes in the tree.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the tree is empty, false otherwise.
     *
     * @return True if the tree is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Compares two elements using the comparator (if provided) or the natural ordering (if no comparator).
     *
     * @param a The first element.
     * @param b The second element.
     * @return A negative number if a < b, zero if a == b, and a positive number if a > b.
     */
    protected int compare(T a, T b) {
        if (comparator != null) return comparator.compare(a, b);
        Comparable<? super T> ca = (Comparable<? super T>) a;
        return ca.compareTo(b);
    }

    /**
     * Inserts a key into the tree. Throws NullPointerException if the key is null.
     *
     * @param key The key to insert into the tree.
     */
    public void insert(T key) {
        if (key == null) throw new NullPointerException("Key must not be null");
        root = insert(root, key);
    }

    /**
     * Deletes a key from the tree. Returns false if the key is not found.
     *
     * @param key The key to delete from the tree.
     * @return True if the key was deleted, false if the key was not found.
     */
    public boolean delete(T key) {
        if (key == null || !contains(key)) return false;
        root = delete(root, key);
        size--;
        return true;
    }

    /**
     * Checks if the tree contains the specified key.
     *
     * @param key The key to check for.
     * @return True if the key is found, false otherwise.
     */
    public boolean contains(T key) {
        return find(root, key) != null;
    }

    // Helper Methods

    /**
     * Finds the node containing the specified key. Returns null if the key is not found.
     *
     * @param node The root node of the subtree to search.
     * @param key The key to search for.
     * @return The node containing the key, or null if not found.
     */
    protected Node<T> find(Node<T> node, T key) {
        if (node == null || key == null) return null;
        int cmp = compare(key, node.key);
        if (cmp < 0)      return find(node.left, key);
        else if (cmp > 0) return find(node.right, key);
        else              return node;
    }

    /**
     * Returns the maximum key in the tree. Throws NoSuchElementException if the tree is empty.
     *
     * @return The maximum key in the tree.
     */
    public T getMax() {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");
        return getMaxNode(root).key;
    }

    /**
     * Returns the node containing the maximum key.
     *
     * @param node The root node of the subtree to search.
     * @return The node containing the maximum key.
     */
    protected Node<T> getMaxNode(Node<T> node) {
        return node.right == null ? node : getMaxNode(node.right);
    }

    /**
     * Deletes the minimum key in the tree.
     *
     * @return True if the minimum element was deleted, false if the tree is empty.
     */
    public boolean deleteMin() {
        if (isEmpty()) return false;
        delete(getMin());
        return true;
    }

    /**
     * Deletes the maximum key in the tree.
     *
     * @return True if the maximum element was deleted, false if the tree is empty.
     */
    public boolean deleteMax() {
        if (isEmpty()) return false;
        delete(getMax());
        return true;
    }

    /**
     * Returns the minimum key in the tree. Throws NoSuchElementException if the tree is empty.
     *
     * @return The minimum key in the tree.
     */
    public T getMin() {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");
        return getMinNode(root).key;
    }

    /**
     * Returns the node containing the minimum key.
     *
     * @param node The root node of the subtree to search.
     * @return The node containing the minimum key.
     */
    protected Node<T> getMinNode(Node<T> node) {
        return node.left == null ? node : getMinNode(node.left);
    }

    /**
     * Returns the height of a given node. Returns the constant value EMPTY_HEIGHT if the node is null.
     *
     * @param node The node whose height is to be returned.
     * @return The height of the node.
     */
    protected int height(Node<T> node) {
        return node == null ? EMPTY_HEIGHT : node.height;
    }

    /**
     * Updates the height of the given node based on the heights of its left and right children.
     *
     * @param node The node whose height is to be updated.
     */
    protected void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // Traversals

    /**
     * Performs an in-order traversal of the tree and returns the keys in a list.
     *
     * @return A list of the keys in in-order.
     */
    public List<T> inOrderTraversal() {
        List<T> list = new ArrayList<>();
        traverseInOrder(root, list);
        return list;
    }

    private void traverseInOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        traverseInOrder(node.left, list);
        list.add(node.key);
        traverseInOrder(node.right, list);
    }

    /**
     * Performs a pre-order traversal of the tree and returns the keys in a list.
     *
     * @return A list of the keys in pre-order.
     */
    public List<T> preOrderTraversal() {
        List<T> list = new ArrayList<>();
        traversePreOrder(root, list);
        return list;
    }

    private void traversePreOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        list.add(node.key);
        traversePreOrder(node.left, list);
        traversePreOrder(node.right, list);
    }

    /**
     * Performs a post-order traversal of the tree and returns the keys in a list.
     *
     * @return A list of the keys in post-order.
     */
    public List<T> postOrderTraversal() {
        List<T> list = new ArrayList<>();
        traversePostOrder(root, list);
        return list;
    }

    private void traversePostOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        traversePostOrder(node.left, list);
        traversePostOrder(node.right, list);
        list.add(node.key);
    }

    /**
     * Returns an iterator for the AVL Tree, enabling in-order traversal.
     *
     * @return An iterator that iterates through the tree in in-order.
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator<>(root);
    }
}
