/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * The Project3 class serves as the entry point for the binary search tree (BST) application.
 * It prompts the user to input a binary tree string, validates it, and constructs the BST.
 * The program checks if the tree is a valid BST and whether it is balanced, providing relevant
 * output and allowing the user to input additional trees as desired.
 */


import java.util.Scanner;

public class Project3 {
    static private Scanner scanner;
    
    static private BST bst;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);  // Create a Scanner object
        do {
            String treeAsString = GetUserInput("Enter a binary tree : ");
            try {
                bst = new BST(treeAsString);
            } catch (BSTException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println(bst);
            if(bst.isBST()) {
                if(bst.isBalanced()) {
                    System.out.println("It is a balanced binary search tree");
                } else {
                    System.out.println("It is a binary search tree but it is not balanced");
                    BST balancedBST = new BST(bst.getTreeAsArray());
                    System.out.println(balancedBST);
                    System.out.println("Original tree has height " + bst.getHeight());
                    System.out.println("Balanced tree has height " + balancedBST.getHeight());
                }
            } else {
                System.out.println("It is not a binary search tree");
                BST balancedBST = new BST(bst.getTreeAsArray());
                System.out.println(balancedBST);
                System.out.println("Original tree has height " + bst.getHeight());
                System.out.println("Balanced tree has height " + balancedBST.getHeight());
            }
        } while(GetUserInput("More Trees? Y or N: ").toLowerCase().equals("y"));
    }

    private static String GetUserInput(String question) {
        System.out.print(question);
        return scanner.nextLine().strip();  
    }
}