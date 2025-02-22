package Trees;

public interface BinaryTree {
    LinkedTreeNode Root();

    int size();

    void preOrder();

    void depthFirstTraversal();

    void postOrder();

    void inOrder();

    void levelOrder();

    void insert(int[] values);

    void insert(int value);

    void clear();

    int height();

    int sum();

    int min();

    int max();

    void print();

    void visualize();

    void toBinarySearchTree();

    boolean binarySearch(int value);

    Integer[] toArray();

    boolean contains(int value);

    boolean isEmpty();

    boolean isBalanced();

    boolean isFull();

    boolean isSorted();
}
