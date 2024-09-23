/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * The BST class represents a binary search tree, allowing for the creation
 * of a tree from a string representation or an array of integers. It provides
 * methods to check if the tree is a valid BST, balanced, and to retrieve its
 * height and elements as an array. The class also includes tree traversal 
 * methods and parsing functionality for constructing the tree structure.
 */

import java.util.ArrayList;
import java.util.Comparator;

public class BST {

    private Node head;
    private String treeAsString;
    private char[] fileCharacters;
    private Integer currentIndex;
    ArrayList<Integer> treeAsArray;

    //Generated a data from the string input
    public BST(String s) throws BSTException {
        BSTValidator bstValidator = new BSTValidator(s);
        treeAsString = s;
        try {
            if(bstValidator.isStringValidTree()) {
                parseTree();
            }
        } catch(BSTException e) {
            throw e;
        }
        
    }
    
    //Uses the list to build a new properly balanced BST
    public BST(ArrayList<Integer> list) {
        if(list == null || list.size() == 0)
            return;

        list.sort(Comparator.naturalOrder());

        treeAsArray = list;
        head = arrayToTree(0, treeAsArray.size() - 1);

        if(!isBST())
            System.err.println("Tree is invalid!!!");
        
        if(!isBalanced())
            System.err.println("Tree is imbalanced");
    }

    @Override
    public String toString() {  
        return preorder(head, 0);
    }
    
    public boolean isBST () {
        if(head == null)
            return false;
    
        return head.isNodeBST();
    }

    public boolean isBalanced() {
        if(head == null) 
            return false;

        if(head.getLeft() == null && head.getRight() == null)
            return true;

        
        return Math.abs(getHeight(head.getLeft()) - getHeight(head.getRight())) < 2;
    }

    public int getHeight() {
        if(head == null)
            return 0;

        return getHeight(head);
    }

    public ArrayList<Integer> getTreeAsArray() {
        if(head == null)
            return new ArrayList<>();
            
        return getNodeData(head);
    }

    private Node arrayToTree(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(treeAsArray.get(mid));

        node.setLeft(arrayToTree(start, mid - 1));
 
        node.setRight(arrayToTree(mid + 1, end));

        return node;
    }

    private void parseTree() {
        fileCharacters = treeAsString.toCharArray();
        currentIndex = 0;

        head = parseNode();
    }

    private Node parseNode() {
        if(currentIndex >= fileCharacters.length)
            return null;

        Node currentNode = null;
        int end = -1;
        String numericString = "";
        boolean hasChildren = false;
        int dataValue = -1;

        if(fileCharacters[currentIndex] == ' ') {
            currentIndex ++;
        }

        end = findEnd(currentIndex);
        if(fileCharacters[currentIndex] == '(') {
            numericString = treeAsString.substring(currentIndex + 1, end);
            hasChildren = true;
        } else {
            numericString = treeAsString.substring(currentIndex, end);
            hasChildren = false;                
        }

        if(fileCharacters[currentIndex] != '*') {
            dataValue = getDataValue(numericString);
        }

        Node newNode = dataValue == -1 ? null : new Node(dataValue);

        if(currentNode == null) {
            currentNode = newNode;
            currentIndex = end;
        } 
        if(currentNode != null && hasChildren) {
            currentNode.setLeft(parseNode());
            if(fileCharacters[currentIndex] == ')')
                currentIndex ++;
            
            currentNode.setRight(parseNode());
            if(fileCharacters[currentIndex] == ')')
                currentIndex ++;
        }
  
        return currentNode;
    }

    private Integer getDataValue(String numericString) {
        return Integer.parseInt(numericString);
    }

    private int findEnd(int index) {
        do {
            index ++;
        } while(fileCharacters[index] != ' ' && fileCharacters[index] != ')');

        return index;
    }

    private String preorder(Node curNode, int indent) {
        String s = "";
        if(curNode != null) {
            s += "\t".repeat(indent) + curNode.getData() + "\n";
            s += preorder(curNode.getLeft(), indent + 1);
            s += preorder(curNode.getRight(), indent + 1);
        }
        return s;
    }

    private ArrayList<Integer> getNodeData(Node n) {
        ArrayList<Integer> tree = new ArrayList<>();
        if(n == null)
            return tree;
            
        tree.add(n.getData());
        if(n.getLeft() != null) {
            tree.addAll(getNodeData(n.getLeft()));
        }

        if(n.getRight() != null) {
            tree.addAll(getNodeData(n.getRight()));
        }

        return tree;
    }

    private int getHeight(Node node) {
        if(node == null)
            return -1;

        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
}
