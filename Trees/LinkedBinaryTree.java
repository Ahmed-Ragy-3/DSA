package Trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class LinkedTreeNode {
    private int element;
    private LinkedTreeNode left;
    private LinkedTreeNode right;

    public LinkedTreeNode(int element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public LinkedTreeNode(int element, LinkedTreeNode left, LinkedTreeNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public LinkedTreeNode() {
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public boolean hasLeft() {
        return (this.left != null);
    }

    public boolean hasRight() {
        return (this.right != null);
    }

    public boolean isFull() {
        return (this.left != null && this.right != null);
    }

    public int getElement() {
        return this.element;
    }

    public LinkedTreeNode getLeft() {
        return this.left;
    }

    public LinkedTreeNode getRight() {
        return this.right;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public void setLeft(LinkedTreeNode left) {
        this.left = left;
    }

    public void setRight(LinkedTreeNode right) {
        this.right = right;
    }
}

public class LinkedBinaryTree {
    private LinkedTreeNode root;

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(int element) {
        root = new LinkedTreeNode(element);
    }

    public LinkedBinaryTree(int[] elements) {
        for (int i = 0; i < elements.length; i++) {
            insert(elements[i]);
        }
    }

    public LinkedTreeNode Root() {
        return root;
    }

    public int size() {
        return size(this.root);
    }

    private int size(LinkedTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (node.hasLeft()) {
            left = size(node.getLeft());
        }
        if (node.hasRight()) {
            right = size(node.getRight());
        }
        return left + right + 1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrder(this.root);
        System.out.println();
    }

    private void preOrder(LinkedTreeNode node) {
        System.out.print(node.getElement() + " ");
        if (node.hasLeft()) {
            preOrder(node.getLeft());
        }
        if (node.hasRight()) {
            preOrder(node.getRight());
        }
    }

    public void depthFirstTraversal() // Pre-order traversal (iterative)
    {
        System.out.print("DFT: ");
        Stack<LinkedTreeNode> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.isEmpty()) {
            LinkedTreeNode temp = stack.pop();
            if (temp != null) {
                System.out.print(temp.getElement() + " ");
            }

            if (temp.hasRight()) {
                stack.push(temp.getRight());
            }
            if (temp.hasLeft()) {
                stack.push(temp.getLeft());
            }
        }
        System.out.println();
    }

    public void postOrder() {
        System.out.print("PostOrder: ");
        postOrder(this.root);
        System.out.println();
    }

    private void postOrder(LinkedTreeNode node) {
        if (node.hasLeft()) {
            postOrder(node.getLeft());
        }
        if (node.hasRight()) {
            postOrder(node.getRight());
        }
        System.out.print(node.getElement() + " ");
    }

    public void inOrder() {
        System.out.print("InOrder: ");
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(LinkedTreeNode node) {
        if (node.hasLeft()) {
            inOrder(node.getLeft());
        }
        System.out.print(node.getElement() + " ");
        if (node.hasRight()) {
            inOrder(node.getRight());
        }
    }

    public void levelOrder() { // breadth first traversal
        Queue<LinkedTreeNode> queue = new LinkedList<LinkedTreeNode>();
        System.out.print("LevelOrder: ");
        queue.add(this.root);

        while (!queue.isEmpty()) {
            LinkedTreeNode temp = queue.poll();
            System.out.print(temp.getElement() + " ");
            if (temp.hasLeft()) {
                queue.add(temp.getLeft());
            }
            if (temp.hasRight()) {
                queue.add(temp.getRight());
            }
        }
        System.out.println();
    }

    public void insert(int[] values) {
        for (int i = 0; i < values.length / 2; i++) {
            insert(values[values.length / 2 - 1 - i]);
            insert(values[values.length / 2 + i]);
        }
    }

    public void insert(int value) {
        if (this.root == null) {
            this.root = new LinkedTreeNode(value);
        } else {
            insert(this.root, value);
        }
    }

    private void insert(LinkedTreeNode node, int value) {
        if (value < node.getElement()) {
            if (node.hasLeft()) {
                insert(node.getLeft(), value);
            } else {
                node.setLeft(new LinkedTreeNode(value));
            }
        } else {
            if (node.hasRight()) {
                insert(node.getRight(), value);
            } else {
                node.setRight(new LinkedTreeNode(value));
            }
        }
    }

    public boolean contains(int value) {
        return contains(value, root);
    }

    private boolean contains(int value, LinkedTreeNode node) {
        return node.getElement() == value || (node.hasLeft() && contains(value, node.getLeft()))
                || (node.hasRight() && contains(value, node.getRight()));
    }

    public void print() {
        print(this.root);
        System.out.println();
    }

    private void print(LinkedTreeNode node) {
        System.out.print(node.getElement() + " ");
        if (node.hasLeft()) {
            print(node.getLeft());
        }
        if (node.hasRight()) {
            print(node.getRight());
        }
    }

    public void toBinarySearchTree() {
        Queue<LinkedTreeNode> queue = new LinkedList<LinkedTreeNode>();
        LinkedBinaryTree sortedTree = new LinkedBinaryTree();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            LinkedTreeNode temp = queue.poll();
            sortedTree.insert(temp.getElement());
            if (temp.hasLeft()) {
                queue.add(temp.getLeft());
            }
            if (temp.hasRight()) {
                queue.add(temp.getRight());
            }
        }
        this.root = sortedTree.root;
    }

    public void clear() {
        root = null;
    }

    public int height() {
        return height(this.root);
    }

    private int height(LinkedTreeNode node) {
        return 1 + Math.max(node.hasLeft() ? height(node.getLeft()) : 0, node.hasRight() ? height(node.getRight()) : 0);
    }

    public boolean isBalanced() {
        return size(root.getLeft()) == size(root.getRight());
    }

    public boolean isFull() {
        return isFull(this.root);
    }

    private boolean isFull(LinkedTreeNode node) {
        if (node.isLeaf()) {
            return true;
        }
        if (!node.isFull()) {
            return false;
        } else {
            boolean exp1 = isFull(node.getLeft());
            boolean exp2 = isFull(node.getRight());
            return exp1 && exp2;
        }
    }

    public int sum() {
        if (this.isEmpty()) {
            return 0;
        }
        return sum(this.root);
    }

    private int sum(LinkedTreeNode node) {
        return node.getElement() + (node.hasLeft() ? sum(node.getLeft()) : 0)
                + (node.hasRight() ? sum(node.getRight()) : 0);
    }

    public int min() {
        return min(this.root);
    }

    private int min(LinkedTreeNode node) {
        return Math.min(node.getElement(), Math.min((node.hasLeft() ? this.min(node.getLeft()) : Integer.MAX_VALUE),
                (node.hasRight() ? this.min(node.getRight()) : Integer.MAX_VALUE)));
    }

    public int max() {
        return max(this.root);
    }

    private int max(LinkedTreeNode node) {
        return Math.max(node.getElement(), Math.max((node.hasLeft() ? this.max(node.getLeft()) : Integer.MIN_VALUE),
                (node.hasRight() ? this.max(node.getRight()) : Integer.MIN_VALUE)));
    }

    public boolean binarySearch(int value) {
        return binarySearch(this.root, value);
    }

    private boolean binarySearch(LinkedTreeNode node, int value) {
        if (node == null)
            return false;
        return (node.getElement() == value) || (node.getElement() > value ? binarySearch(node.getLeft(), value)
                : binarySearch(node.getRight(), value));
    }

    public boolean isSorted() {
        return isSorted(this.root);
    }

    private boolean isSorted(LinkedTreeNode node) {
        if (node == null)
            return true;
        return (node.hasLeft() ? (node.getElement() > node.getLeft().getElement()) && isSorted(node.getLeft()) : true)
                && (node.hasRight() ? (node.getElement() <= node.getRight().getElement()) && isSorted(node.getRight())
                        : true);
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private LinkedTreeNode delete(LinkedTreeNode node, int value) {
        if (node == null)
            return null;
        if (node.getElement() == value) {
            if (!node.hasLeft()) {
                return node.getRight();
            } else if (!node.hasRight()) {
                return node.getLeft();
            } else if (node.isLeaf()) {
                return null;
            } else {
                node.setElement(min(node.getRight()));
                deletemin(node.getRight());
            }
        } else if (node.getElement() > value) {
            node.setLeft(delete(node.getLeft(), value));
        } else {
            node.setRight(delete(node.getRight(), value));
        }
        return node;
    }

    public void deletemin() {
        if (!root.hasLeft()) {
            root = root.getRight();
        } else {
            deletemin(root);
        }
    }

    private void deletemin(LinkedTreeNode node) {
        if (node == null)
            return;
        if (node.hasLeft() && !node.getLeft().hasLeft()) {
            if (!node.getLeft().hasRight()) {
                node.setLeft(null);
            } else {
                node.setLeft(node.getLeft().getRight());
            }
        } else {
            deletemin(node.getLeft());
        }
    }

    public void visualize() {
        Integer[] array = this.toArray();
        int height = this.height();
        int pntr = 0;
        int pntr2 = 1;

        for (int i = (int) Math.pow(2, height); i > 0; i--) {
            System.out.print("_");
        }
        System.out.println();
        System.out.println();
        for (int i = height; i > 0; i--) {
            for (int j = 0; j < (int) Math.pow(2, i - 1) - 1; j++) {
                System.out.print(" ");
            }
            int temp = pntr;
            for (; pntr <= temp + (int) Math.pow(2, height - i) - 1; pntr++) {
                if (array[pntr] != null) {
                    System.out.print(array[pntr]);
                } else {
                    System.out.print(" ");
                }
                for (int j = 0; j < (int) Math.pow(2, i) - 1; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            if (i > 1) {
                for (int j = 0; j < (int) Math.pow(2, i - 2) - 1; j++) {
                    System.out.print(" ");
                }
                int temp2 = pntr2;
                for (; pntr2 <= temp2 + (int) Math.pow(2, height - (i - 1)) - 1; pntr2++) {
                    if (array[pntr2] != null) {
                        if (pntr2 % 2 == 1) {
                            System.out.print("/");
                        } else {
                            System.out.print("\\");
                        }
                    } else {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < (int) Math.pow(2, i - 1) - 1; j++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        for (int j = (int) Math.pow(2, height); j > 0; j--) {
            System.out.print("_");
        }
        System.out.println();
    }

    public Integer[] toArray() {
        if (this.isEmpty()) {
            return new Integer[0];
        }
        int height = this.height();
        Integer[] array = new Integer[(int) Math.pow(2, height) - 1];

        Queue<LinkedTreeNode> queue = new LinkedList<>();
        queue.add(this.root);
        int i = 1;
        array[0] = root.getElement();
        while (!queue.isEmpty()) {
            LinkedTreeNode temp = queue.poll();
            if (temp != null) {
                if (temp.hasLeft()) {
                    array[2 * i - 1] = temp.getLeft().getElement();
                }
                if (temp.hasRight()) {
                    array[2 * i] = temp.getRight().getElement();
                }
                queue.add(temp.getLeft());
                queue.add(temp.getRight());
            } else if (i < Math.pow(2, height) - 1) {
                queue.add(null);
                queue.add(null);
            }
            i++;
        }
        return array;
    }

    public void treeToBinaryTree(Tree originalTree) {
        this.clear();
        if (originalTree == null)
            return;

        this.root = new LinkedTreeNode(originalTree.value);

        treeToBinaryTree(originalTree, this.root);
    }

    private void treeToBinaryTree(Tree originalTree, LinkedTreeNode node) {
        if (originalTree == null)
            return;

        for (int i = 0; i < originalTree.children.size(); i++) {
            if (i == 0) {
                node.setLeft(new LinkedTreeNode(originalTree.children.get(i).value));
                treeToBinaryTree(originalTree.children.get(i), node.getLeft());
            } else {
                LinkedTreeNode current = node.getLeft();
                while (current.getRight() != null) {
                    current = current.getRight();
                }
                current.setRight(new LinkedTreeNode(originalTree.children.get(i).value));
                treeToBinaryTree(originalTree.children.get(i), current.getRight());
            }
        }
    }

    int subTreeAt(LinkedTreeNode node, int index) {
        return 0;
    }

    //Question 8
    public int internalNodes() {
        if(root.isLeaf())
        {
            return 0;
        }
        return internalNodes(this.root);
    }
    private int internalNodes(LinkedTreeNode node) {
        int sum;
        if(node.isLeaf())
        {
            sum = 0;
        }
        else sum = 1;
        return sum + (node.hasLeft() ?  internalNodes(node.getLeft()) : 0) + (node.hasRight() ?  internalNodes(node.getRight()) : 0);
    }

    public int leafNodes() {
        if(root.isLeaf())
        {
            return 0;
        }
        return leafNodes(this.root);
    }
    

    private int leafNodes(LinkedTreeNode node) {
        int sum;
        if(node.isLeaf())
        {
            sum = 1;
        }
        else sum = 0;
        return sum + (node.hasLeft() ?  leafNodes(node.getLeft()) : 0) + (node.hasRight() ?  leafNodes(node.getRight()) : 0);
    }


    //Question 9
    public boolean isEquivalent(LinkedBinaryTree tree) { 
        return isEquivalent(this.root, tree.root);
    }

    private boolean isEquivalent(LinkedTreeNode node1, LinkedTreeNode node2) {
        
        if(node1 != null && node2 == null || node1 != null && node2 == null)
        {
            return false;
        }
        if(node1 == null && node2 == null)
        {
            return true;
        }
        if(node1.getElement() == node2.getElement())
        {
            return isEquivalent(node1.getLeft(), node2.getLeft()) && isEquivalent(node1.getRight(), node2.getRight());
        }
        else{
            return false;
        }
    }

    //Question 10
    public void swap()
    {
        swap(this.root);
    }
    private void swap(LinkedTreeNode node)
    {
        LinkedTreeNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);
        if(node.hasLeft())
        {
            swap(node.getLeft());
        }
        if(node.hasRight())
        {
            swap(node.getRight());
        }   
    }

    public static void main(String[] args) {
        LinkedBinaryTree tree = new LinkedBinaryTree(1);

        LinkedTreeNode temp = tree.root;
        temp.setLeft(new LinkedTreeNode(6));
        temp.setRight(new LinkedTreeNode(3));
        temp.getLeft().setLeft(new LinkedTreeNode(1));
        temp.getLeft().setRight(new LinkedTreeNode(8));
        temp.getRight().setLeft(new LinkedTreeNode(5));
        temp.getRight().setRight(new LinkedTreeNode(7));
        temp.getLeft().getLeft().setLeft(new LinkedTreeNode(0));
        temp.getRight().getRight().setLeft(new LinkedTreeNode(9));
        temp.getLeft().getRight().setLeft(new LinkedTreeNode(4));
        temp.getLeft().getRight().setRight(new LinkedTreeNode(1));
        temp.getLeft().getRight().getRight().setRight(new LinkedTreeNode(2));

        // 1
        // / \
        // 6 3
        // / \ / \
        // 1 8 5 7
        // / / \ /
        // 0 4 1 9
        // \
        // 2

        LinkedBinaryTree treeSorted = new LinkedBinaryTree(new int[] { 5, 3, 7, 4, 6, 8, 9, 7, 2, 1 });
        // 5
        // / \
        // 3 7
        // / \ / \
        // 2 4 6 8
        // / / \
        // 1 7 9

        tree.postOrder();
        tree.inOrder();
        tree.preOrder();
        tree.levelOrder();
        tree.depthFirstTraversal();

        System.out.println(Arrays.toString(tree.toArray()));
        System.out.println("Height: " + tree.height());
        System.out.println("isFull: " + tree.isFull());
        System.out.println("isBalanced: " + tree.isBalanced());
        System.out.println("Sum: " + tree.sum());
        System.out.println("Min: " + tree.min());
        System.out.println("Max: " + tree.max());
        System.out.println("size: " + tree.size());

        System.out.println(Arrays.toString(treeSorted.toArray()));
        System.out.println(treeSorted.binarySearch(3));
        System.out.println(treeSorted.binarySearch(10));
        System.out.println(treeSorted.binarySearch(5));
        System.out.println(treeSorted.binarySearch(12));
        System.out.println(treeSorted.binarySearch(7));

        System.out.println(tree.isSorted());
        System.out.println(treeSorted.isSorted());
        tree.visualize();
        treeSorted.visualize();
        treeSorted.delete(7);
        treeSorted.delete(7);
        treeSorted.visualize();
        tree.toBinarySearchTree();
        System.out.println("Height: " + tree.height());
        tree.inOrder();
        tree.visualize();

        Tree root = new Tree(1);
        root.addchild(new int[] { 2, 3, 4 });
        root.children.get(0).addchild(new int[] { 0, 12 });
        root.children.get(1).addchild(new int[] { 5, 6 });
        root.children.get(1).children.get(0).addchild(new int[] { 8, 9 });
        root.children.get(1).children.get(1).addchild(new int[] { 10 });
        root.children.get(2).addchild(7);

        LinkedBinaryTree tree2 = new LinkedBinaryTree();
        tree2.treeToBinaryTree(root);
        tree2.visualize();
        tree2.print();
        System.out.println("Internal Nodes of tree is " + tree.internalNodes());
        System.out.println("Internal Nodes of treeSorted is " + treeSorted.internalNodes());
        System.out.println("Internal Nodes of tree2 is " + tree2.internalNodes());
        System.out.println("Leaf Nodes of tree is " + tree.leafNodes());
        System.out.println("Leaf Nodes of treeSorted is " + treeSorted.leafNodes());
        System.out.println("Leaf Nodes of tree2 is " + tree2.leafNodes());




        treeSorted.swap();
        treeSorted.visualize();
        System.out.println("Internal Nodes of tree2 is " + treeSorted.internalNodes());
        System.out.println("Leaf Nodes of tree2 is " + treeSorted.leafNodes());
        System.out.println("Height: " + treeSorted.height());
        System.out.println("isFull: " + treeSorted.isFull());
        System.out.println("isBalanced: " + treeSorted.isBalanced());
        System.out.println("Sum: " + treeSorted.sum());
        System.out.println("Min: " + treeSorted.min());
        System.out.println("Max: " + treeSorted.max());
        System.out.println("size: " + treeSorted.size());
        System.out.println(Arrays.toString(treeSorted.toArray()));


        System.out.println(tree.isEquivalent(tree2));
        System.out.println(treeSorted.isEquivalent(tree2));
        System.out.println(tree.isEquivalent(treeSorted));
        System.out.println(tree2.isEquivalent(treeSorted));
        System.out.println(tree2.isEquivalent(tree));
        System.out.println(treeSorted.isEquivalent(tree));
        System.out.println(tree.isEquivalent(treeSorted));
        System.out.println(treeSorted.isEquivalent(tree));
        System.out.println(tree.isEquivalent(tree2));
        System.out.println(tree2.isEquivalent(tree));
        System.out.println(treeSorted.isEquivalent(tree2));
        System.out.println(tree2.isEquivalent(treeSorted));

        System.out.println(tree.isEquivalent(tree));
        System.out.println(treeSorted.isEquivalent(treeSorted));
        System.out.println(tree2.isEquivalent(tree2));
    }
}