/*
 * Raymond Rowland
 * Project 3
 * 9/18/2024
 * 
 * Incomplete Tree 
 * Data is Not an Integer
 * Extra Characters at the End 
 * Missing Left Parenthesis 
 * Missing Right Parentheses 
 * 
 * Custom exception class for handling errors specific to the BST operations.
 * It extends the Exception class and provides a constructor for error messages.
 * 
 */

public class BSTException extends Exception {
    public BSTException(String errorMessage) {
        super(errorMessage);
    }
}
