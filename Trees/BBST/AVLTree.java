package Trees.BBST;

/**
 * AVLTree is a self-balancing Binary Search Tree (BST) that ensures the tree remains balanced
 * after every insertion and deletion operation. It maintains the AVL property:
 * the height difference (balance factor) between the left and right subtrees of any node
 * is at most 1. This ensures the tree's height is logarithmic in relation to the number of nodes.
 *
 * The main operations in an AVL tree include:
 * 1. **Insertion**: Adds a new node while maintaining the balance property of the tree.
 * 2. **Deletion**: Removes a node and ensures that the tree remains balanced.
 * 3. **Balancing**: Ensures that the AVL tree maintains its balance after every insertion and deletion.
 * 4. **Rotations**: Right and left rotations are used to balance the tree. Double rotations (left-right, right-left) are used for more complex imbalances.
 *
 * The time complexity for insertion, deletion, and searching in an AVL tree is O(log n) due to the balanced structure.
 */
public class AVLTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Insert a node into the AVL tree.
     *
     * This method inserts a new key into the tree while ensuring that the tree remains balanced.
     * After each insertion, the height of each ancestor node is updated and rebalanced if necessary.
     *
     * @param node The current node to insert the new key into.
     * @param key The key to be inserted into the tree.
     * @return The new root of the tree or subtree after insertion.
     */
    @Override
    protected Node<T> insert(Node<T> node, T key) {
        // Base case: if the node is null, create a new node and return it
        if (node == null) {
            size++;  // Increment size as we add a new node
            return new Node<>(key);  // Create a new node with the given key
        }

        // Compare the key with the current node's key to decide whether to insert in left or right subtree
        int cmp = compare(key, node.key);

        // If the key is smaller, insert it in the left subtree
        if (cmp < 0) node.left = insert(node.left, key);
            // If the key is larger, insert it in the right subtree
        else if (cmp > 0) node.right = insert(node.right, key);

        // After insertion, update the height of the current node
        updateHeight(node);

        // Balance the tree and return the balanced node
        return balance(node);
    }

    /**
     * Delete a node from the AVL tree.
     *
     * This method deletes a key from the tree while ensuring that the tree remains balanced.
     * If the node to be deleted has two children, it finds the in-order successor or predecessor
     * and replaces the node to be deleted. After deletion, the tree is rebalanced if necessary.
     *
     * @param node The current node to search for the key to delete.
     * @param key The key to be deleted from the tree.
     * @return The new root of the tree or subtree after deletion.
     */
    @Override
    protected Node<T> delete(Node<T> node, T key) {
        // Base case: if the node is null, return null (no node to delete)
        if (node == null) return null;

        // Compare the key to decide where to delete the node
        int cmp = compare(key, node.key);

        // If the key is smaller, move left in the tree
        if (cmp < 0)      node.left = delete(node.left, key);
            // If the key is larger, move right in the tree
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            // If the node has one child or no children, replace the node with its child
            if (node.left == null)  return node.right;
            if (node.right == null) return node.left;
            // If the node has two children, find the successor or predecessor and replace the node
            if (height(node.left) > height(node.right)) {
                Node<T> predecessor = getMaxNode(node.left);
                node.key = predecessor.key;
                node.left = delete(node.left, predecessor.key);
            } else {
                Node<T> successor = getMinNode(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
        }

        // After deletion, update the height of the current node and balance the tree
        updateHeight(node);
        return balance(node);
    }

    /**
     * Calculates the balance factor of a node.
     *
     * The balance factor is the difference between the height of the right and left subtrees.
     * - Positive balance factor indicates the tree is right-heavy.
     * - Negative balance factor indicates the tree is left-heavy.
     * - Zero balance factor indicates the tree is perfectly balanced at that node.
     *
     * @param node The node whose balance factor is to be calculated.
     * @return The balance factor of the node.
     */
    private int balanceFactor(Node<T> node) {
        return height(node.right) - height(node.left);
    }

    /**
     * Balances the AVL tree at a given node.
     *
     * If the node is unbalanced, the method performs the necessary rotation (left or right or both)
     * to restore balance. This ensures that the AVL tree property is maintained.
     *
     * @param node The node to balance.
     * @return The balanced node.
     */
    private Node<T> balance(Node<T> node) {
        int bf = balanceFactor(node);

        // Left-heavy subtree: requires right rotation (LL or LR case)
        if (bf < -1) {
            if (balanceFactor(node.left) <= 0) // LL Case
                return rotateRight(node);
            else // LR Case
            { node.left = rotateLeft(node.left); return rotateRight(node); }
        }
        // Right-heavy subtree: requires left rotation (RR or RL case)
        else if (bf > 1) {
            if (balanceFactor(node.right) >= 0) // RR Case
                return rotateLeft(node);
            else // RL Case
            { node.right = rotateRight(node.right); return rotateLeft(node); }
        }
        // Tree is already balanced
        return node;
    }

    /**
     * Performs a right rotation on the given node to restore balance.
     *
     * Right rotation is used when the left subtree of a node is too tall.
     * The node's left child becomes the new root of the subtree.
     *
     * @param y The node to perform the rotation on.
     * @return The new root of the rotated subtree.
     */
    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;  // x becomes the new root of the subtree
        y.left = x.right;  // Move x's right child to y's left child
        x.right = y;  // Make y the right child of x
        // Update the heights of y and x after rotation
        updateHeight(y);
        updateHeight(x);
        return x;  // Return the new root of the subtree
    }

    /**
     * Performs a left rotation on the given node to restore balance.
     *
     * Left rotation is used when the right subtree of a node is too tall.
     * The node's right child becomes the new root of the subtree.
     *
     * @param y The node to perform the rotation on.
     * @return The new root of the rotated subtree.
     */
    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.right;  // x becomes the new root of the subtree
        y.right = x.left;  // Move x's left child to y's right child
        x.left = y;  // Make y the left child of x
        // Update the heights of y and x after rotation
        updateHeight(y);
        updateHeight(x);
        return x;  // Return the new root of the subtree
    }
}
