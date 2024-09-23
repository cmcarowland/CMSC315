/*
 * Raymond Rowland
 * Project 3
 * 9/23/2024
 * 
 * The BSTValidator class is responsible for validating the string representation
 * of a binary search tree (BST). It checks for proper syntax, including matching
 * parentheses and valid integer data. The class throws a BSTException with a
 * relevant message for any validation errors encountered during the process.
 */

import java.util.Stack;
import java.util.List;

public class BSTValidator {
    private char[] fileCharacters;
    private String tree;
    private int index = 0;
    private Stack<Delimiter> delimiterStack = new Stack<>();
    private List<Character> delimiters = List.of(new Character[]     {'('});
    private List<Character> delimiterEnds =  List.of(new Character[] {')'});
    
    public BSTValidator(String bst)  {
        tree = bst;
    }

    public boolean isStringValidTree() throws BSTException {
        fileCharacters = tree.toCharArray();
        if(fileCharacters[0] != '(')
            throw new BSTException("Missing Left Parenthesis");
        int parenthesisBalance = countInstancesInString("(") - countInstancesInString(")"); 
        
        if(parenthesisBalance != 0) {
            if(parenthesisBalance < 0) 
                throw new BSTException("Missing Left Parenthesis");
            if(parenthesisBalance > 0) 
                throw new BSTException("Missing Right Parenthesis");
        }
        

        index = 0;
        Integer code = validateString();
        if(code == 0) {
            
        } else {
            switch(code)
            {
                case 1:
                    throw new BSTException("Tree is incomplete");
                case 2:
                    throw new BSTException("Data is not an Integer");
                case 3:
                    throw new BSTException("Extra Characters at the End");
            }
        }

        return true;
    }

    private Integer countInstancesInString(String s) {
        return tree.length() - tree.replace(s, "").length();
    }

    private Integer validateString() {
        int start = index;
        int childCount = 0;
        delimiterStack.push(new Delimiter(fileCharacters[index], index, delimiters.indexOf(fileCharacters[index])));
        index ++;

        for(; index < fileCharacters.length; index++) {
            if(fileCharacters[index] == ' ') {
                childCount ++;
                continue;
            }

            if(isDelimiter(fileCharacters[index])) {
                var code = validateString();
                if(code != 0)
                    return code;
            } else if(isDelimiterEnd(fileCharacters[index])) {
                delimiterStack.pop();
               
                if(childCount != 2) // Incomplete node -> incomplete tree
                    return 1;

                if(index < fileCharacters.length - 1 && isStackClear()) // Extra characters after closing brace
                    return 3;

                return 0;
            } else {
                if(fileCharacters[index] != '*' && !isNumericCharacter(fileCharacters[index])) {
                    return 2;
                } 
            }
        }
        
        return 0;
    }

    private boolean isNumericCharacter(Character c) {
        int charAsInt = (int)c;
        return charAsInt >= 0x30 && charAsInt <= 0x39;
    }
 
    private boolean isDelimiter(Character c) {
        return delimiters.contains(c);
    }

    private boolean isDelimiterEnd(Character c) {
        return delimiterEnds.contains(c);
    }

    public boolean isStackClear() {
        return delimiterStack.empty();
    }
}