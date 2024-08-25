/*
 * Raymond Rowland
 * Project 1
 * 8/25/2024
 * 
 * The JavaValidator class is designed to validate Java source code files 
 * for proper usage of delimiters such as curly braces, parentheses, brackets, quotes, and comments. 
 * It reads a file specified by its path, analyzes its characters to track and ensure all delimiters are correctly opened and closed, and reports any mismatches. 
 * The class uses a stack to keep track of open delimiters and checks for various conditions to ensure syntactical correctness in the source code.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.Iterator;
import java.util.List;

public class JavaValidator implements Iterator<Character> {
    public String filePath;
    public char[] fileCharacters;
    
    private int index = 0;
    private int lineNumber = 1;
    private char currentChar = 0;
    private Stack<Delimiter> delimiterStack = new Stack<>();
    private List<Character> delimiters = List.of(new Character[]     {'{', '(', '[', '"', '\'', '/', '*'});
    private List<Character> delimiterEnds =  List.of(new Character[] {'}', ')', ']', '"', '\'', '\n','*'});
    private boolean escapeStarted;
    private boolean insideQuote;
    private boolean insideChar;
    private boolean insideComment;
    private boolean insideBlockComment;
    
    public JavaValidator(String filePath) throws FileNotFoundException {
        //Validate path exists and can be opened
        this.filePath = filePath;
        if(DoesFileExist()) {
            //Open File and read into buffer
            fileCharacters = OpenFile();
            index = 0;
        }
        else {
            throw new FileNotFoundException("The file was not found");
        }
    }

    public boolean validateFile() {
        while((currentChar = getNextChar()) != 0) {
            // System.out.println("Current Char: " + currentChar);
            if(parseChar(currentChar) == 0)
                break;
        }

        // System.out.println(delimiterStack);
        if(!isStackClear()) {
            Delimiter d = delimiterStack.peek();
            System.out.println("Invalid File " + d.getDescription() + " " + d.character + " is opened on line " + d.lineNumber +" at index " + d.index + ".");
            if(currentChar == 0)
                System.out.println(d.character + " was not closed properly before the file ended.");
            else
                System.out.println("The closing delimiter " + currentChar + " was encountered instead of " + delimiterEnds.get(d.delimeterIndex) + " on line " + lineNumber + " at index " + index + ".");

            return false;
        } 

        return true;
    }

    private boolean DoesFileExist()
    {
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        
        return false;
    }

    private char[] OpenFile() {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path).toCharArray();
        } catch (Exception e) {
            return null;
        }
    }

    private char getEscapedCharacter(char c) {
        switch(c) {
            case '\'':
                return '\'';
            case '"':
                return '\"';
            case 'r':
                return '\r';
            case '\\':
                return '\\';
            case 'n':
                return '\n';
            case 'f':
                return '\f';
            case 'b':
                return '\b';
        }

        return (char)0;
    }

    private Character getNextChar() {
        if(hasNext())
            return next();

        return 0;
    }

    private char parseChar(Character currentChar) {
        try{
            // currentChar = fileCharacters[index];
            if(escapeStarted) {
                escapeStarted = false;
                // System.out.println("IN ESCAPE : " + currentChar);
                currentChar = getEscapedCharacter(currentChar);
                // System.out.println("IN ESCAPE Post : " + currentChar);
                return currentChar;
            }
            //Process String Literal \n \r \t etc...
            if((insideChar || insideQuote) && currentChar == '\\') {
                // System.out.println(currentChar);
                escapeStarted = true;
                return currentChar;
            }
            //Process Comment
            if(!(insideChar || insideQuote || insideComment || insideBlockComment) && currentChar == '/' && hasNext() && (fileCharacters[index] == '/' || fileCharacters[index] == '*')) {
               
                // System.out.println("Delimiter Added : " + delimiterStack.peek());
                if(fileCharacters[index] == '/') {
                    insideComment = true;
                    delimiterStack.push(new Delimiter(fileCharacters[index], index - 1, 5, lineNumber));
                } else if(fileCharacters[index] == '*') {
                    insideBlockComment = true;
                    delimiterStack.push(new Delimiter(fileCharacters[index], index - 1, 6, lineNumber));
                }
                
                // currentChar = fileCharacters[index++];
                return currentChar;
            }
        } catch(Exception e) {
            currentChar = 0;
        }

        if(currentChar == '\n')
            lineNumber ++;

        if(isDelimiter()) {
            delimiterStack.push(new Delimiter(currentChar, index, delimiters.indexOf(currentChar), lineNumber));
        } else if(isDelimiterEnd()) {
            if(isProperEnd()) {  //Find way to ignore cases where 
                delimiterStack.pop();
            } else {
                return 0;
            }
        }
        
        // System.out.println(currentChar);
        // if(!delimiterStack.empty())
        //     System.out.println(delimiterStack);

        return currentChar;
    }

    public String getLineNumberAndChar() {
        return "Line : " + lineNumber + " Index : " + index;
    }
 
    private boolean isDelimiter() {
        // System.out.println(currentChar + " Quote: " + insideQuote + " Comment: " + insideComment + " Char: " + insideChar + " Block: " + insideBlockComment);
        if(insideQuote || insideComment || insideChar || insideBlockComment)
            return false;

        for(var d : delimiters) {
            if(currentChar == d) {
                if(currentChar == '"')
                    insideQuote = true;
                else if(currentChar == '\'')
                    insideChar = true;
                
                if(currentChar != '/' && currentChar != '*')
                    return true;
            }
        }

        return false;
    }

    private boolean isDelimiterEnd() {
        // System.out.println("Delimiter End: " + currentChar + " Quote: " + insideQuote + " Comment: " + insideComment + " Char: " + insideChar + " Block: " + insideBlockComment);

        if(insideChar && currentChar != '\'')
            return false;
        if(insideQuote && currentChar != '"')
            return false;
        if(insideComment && currentChar != '\n')
            return false;
        if(insideBlockComment && currentChar != '*')
            return false;

        for(var d : delimiterEnds) {
            if(currentChar == d) {
                if(insideComment && currentChar == '\n') {
                    return true;
                } else if(insideBlockComment && currentChar == '*' && hasNext() && fileCharacters[index] == '/') {
                    return true;
                } else if(!insideBlockComment && currentChar != '\n') {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isProperEnd() {
        if(!delimiterStack.isEmpty()) {
            if(currentChar == delimiterEnds.get(delimiterStack.peek().delimeterIndex)) {
                if(currentChar == '\n' && insideComment)
                    insideComment = false;
                if(insideBlockComment)
                    insideBlockComment = false;
                if(currentChar == '\'')
                    insideChar = false;
                if(currentChar == '"')
                    insideQuote = false;
                
                return true;
            }
        }
        
        return false;
    }

    public boolean isStackClear() {
        return delimiterStack.empty();
    }

    @Override
    public boolean hasNext() {
        if(index < fileCharacters.length)
            return true;

        return false;
    }

    @Override
    public Character next() {
        if(index < fileCharacters.length)
            return fileCharacters[index++];

        return (char)0;
    }
}