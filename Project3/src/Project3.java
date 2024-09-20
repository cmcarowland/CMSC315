/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * 
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
            System.out.println("It is a balanced binary search tree");
        } while(GetUserInput("More Trees? Y or N: ").toLowerCase().equals("y"));
    }

    public static String GetUserInput(String question) {
        System.out.print(question);
        return scanner.nextLine().strip();  
    }
}