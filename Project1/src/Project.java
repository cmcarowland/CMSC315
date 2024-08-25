/*
 * Raymond Rowland
 * Project 1
 * 8/25/2024
 * 
 * The Project class serves as the entry point for a Java application 
 * that validates Java source code files. It prompts the user for a file path, 
 * checks if the file exists, and uses the JavaValidator class to validate the 
 * file's syntactical correctness regarding delimiters.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project {
    static private String fileName;
    static private Scanner scanner;
    static private JavaValidator jv;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = new Scanner(System.in);  // Create a Scanner object
        while(!GetValidFileFromUser())
            System.out.println("Invalid File, Please try again");

        try {
            jv  = new JavaValidator(fileName);
            if(jv.validateFile())
                System.out.println(fileName + " is a valid java file.");
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static boolean GetValidFileFromUser() {
            fileName = GetUserInput();
            if(DoesFileExist(fileName))
                return true;
            
            return false;
    }

    public static String GetUserInput() {
        System.out.print("Enter file path to verify : ");
        return scanner.nextLine();  // Read user input
    }

    public static boolean DoesFileExist(String filePath) {
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        
        return false;
    }
}