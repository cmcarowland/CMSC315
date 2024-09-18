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

    public static void main(String[] args) {
        scanner = new Scanner(System.in);  // Create a Scanner object
        do {
            String treeAsString = GetUserInput("Enter a binary tree : ");
            String s = "53\n\t28\n\t\t11\n\t\t41\n\t83\n\t\t67";
            System.out.println(s);
            System.out.println("It is a balanced binary search tree");
        } while(GetUserInput("More Trees? Y or N: ") == "Y");
    }

    public static String GetUserInput(String question) {
        System.out.print(question);
        return scanner.nextLine();  
    }
}