/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * The Node class represents a single node in a binary search tree (BST).
 * Each node contains integer data and references to its left and right children.
 * It includes methods for accessing and modifying node data, checking if the
 * node maintains BST properties, and a toString method for a readable representation.
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
            return (left.getData() < data) && (data < right.getData()) && left.isNodeBST() && right.isNodeBST();

        if(right == null && left != null)
            return left.getData() < data && left.isNodeBST();
        
        if(right != null && left == null)
            return right.getData() > data && right.isNodeBST();

        return true;
    }
}
