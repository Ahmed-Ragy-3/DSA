package Trees;
import java.util.LinkedList;

public class Tree {
    int value;
    Tree parent;
    LinkedList<Tree> children;

    public Tree(int value) {
        this.value = value;
        this.children = new LinkedList<>();
    }

    public Tree(int value, Tree parent) {
        this.value = value;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public void addchild(int[] children) {
        for(int i = 0; i < children.length; i++) {
            Tree temp = new Tree(children[i], this); 
            this.children.add(temp);
        }
    }
    public void addchild(int child) {
        this.children.add(new Tree(child, this));
    }

    public void preOrder() {
        System.out.print(this.value + " ");
        for(int i = 0; i < children.size(); i++) {
            children.get(i).preOrder();
        }
    }

    public void postOrder() {
        for(int i = 0; i < children.size(); i++) {
            children.get(i).postOrder();
        }
        System.out.print(this.value + " ");
    }

    public int size() {
        int size = 1;
        for(int i = 0; i < children.size(); i++)
        {
            size += children.get(i).size();
        }
        return size;
    }

    public static void main(String[] args) {
        Tree root = new Tree(1);
        root.addchild(new int[] {2, 3, 4});
        root.children.get(1).addchild(new int[] {5, 6});
        root.children.get(1).children.get(0).addchild(new int[] {8, 9});
        root.children.get(1).children.get(1).addchild(new int[] {10});
        root.children.get(2).addchild(7);

        root.preOrder();
        System.out.println();
        root.postOrder();
        System.out.println(); 
        System.out.println("size: " + root.size());
    }
}
