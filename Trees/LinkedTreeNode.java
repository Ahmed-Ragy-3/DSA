package Trees;

public class LinkedTreeNode {
    private int element;
    private LinkedTreeNode left;
    private LinkedTreeNode right;
    
    public LinkedTreeNode(int element){  
        this.element = element;
        this.left = null;
        this.right = null;
    } 
    public LinkedTreeNode(int element, LinkedTreeNode left, LinkedTreeNode right){
        this.element = element;
        this.left = left;
        this.right = right;
    }
    public LinkedTreeNode(){}
    

    public boolean isLeaf(){
        return (this.left == null && this.right == null);
    }   
    public boolean hasLeft(){
        return (this.left!= null);
    }   
    public boolean hasRight(){
        return (this.right!= null);
    }   
    public boolean isFull(){
        return (this.left!= null && this.right!= null);
    }   


    public Object getElement(){
        return this.element;
    }   
    public LinkedTreeNode getLeft(){
        return this.left;
    }  
    public LinkedTreeNode getRight(){
        return this.right;
    }   
    public void setElement(int element){
        this.element = element;
    }  
    public void setLeft(LinkedTreeNode left){
        this.left = left;
    }     
    public void setRight(LinkedTreeNode right){
        this.right = right;
    }   
}