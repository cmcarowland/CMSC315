/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * 
 */

public class Node {
    private int data;
    private Node right;
    private Node left;
    
    public Node(int _data) {
        data = _data;
        right = left = null;
    }

    public int getData() {
        return data;
    }

    public void setLeft(Node node) {
        left = node;
    }
    
    public void setRight(Node node) {
        right = node;
    }
    
    public Node getLeft() {
        return left;
    }
    
    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "data: " + data + " left: " + (left == null ? "*" : left.data) + " right: " + (right == null ? "*" : right.data);
    }

    public boolean isNodeBST() {
        if(left != null && right != null)
            return (left.getData() < data) && (data < right.getData());

        if(right == null && left != null)
            return left.getData() < data;

        return left.isNodeBST() && right.isNodeBST();
    }
}
