/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * 
 */

public class Node {
    int data;
    Node right;
    Node left;
    
    public Node(int _data) {
        data = _data;
        right = left = null;
    }

    public void setLeft(Node node) {
        left = node;
    }
    
    public void setRight(Node node) {
        right = node;
    }

    @Override
    public String toString() {
        return "data: " + data + " left: " + (left == null ? "*" : left.data) + " right: " + (right == null ? "*" : right.data);
    }
}
