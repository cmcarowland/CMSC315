/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * A Binary Search Tree has at most two children, 
 * a left child and a right child, 
 * with the left child containing values less than 
 * the parent node and the right child containing values greater than the parent node. 
 */

import java.util.ArrayList;

public class BST {

    private Node head;
    private String treeAsString;
    private char[] fileCharacters;
    private Integer currentIndex;

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

    @Override
    public String toString() {  
        return preorder(head, 0);
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
    
    public boolean isBST () {
        if(head == null)
            return false;
    
        return head.isNodeBST();
    }

    public boolean isBalanced() {
        return false;
    }

    public int getHeight() {
        if(head == null)
            return 0;

        return getHeight(head);
    }

    public ArrayList<Integer> getTreeAsArray() {
        return new ArrayList<>();
    }

    private int getHeight(Node node) {
        if(node == null)
            return -1;

        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
}
