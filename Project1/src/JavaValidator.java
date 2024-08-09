import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;

public class JavaValidator {
    public String filePath;
    public char[] fileCharacters;
    
    private int index = 0;
    private int lineNumber = 1;
    private char currentChar = 0;
    private Stack<Delimiter> delimiterStack = new Stack<>();
    private List<Character> delimiters = Arrays.asList(new Character[]     {'{', '(', '[', '"', '\'', '/'});
    private List<Character> delimiterEnds =  Arrays.asList(new Character[] {'}', ')', ']', '"', '\'', '\n'});
    private boolean insideQuote;
    private boolean insideChar;
    private boolean insideComment;
    
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
        while((getNextChar()) != 0)
            continue;

        if(!isStackClear()) {
            Delimiter d = delimiterStack.peek();
            System.out.println("Invalid File " + d.character + " is opened on line " + d.lineNumber +" at index " + d.index + " and is never closed.");
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

    public char getNextChar() {
        try{
            currentChar = fileCharacters[index];
            //Process String Literal \n \r \t etc...
            if((insideChar || insideQuote) && currentChar == '\\') {
                index += 2;
                currentChar = fileCharacters[index];
                return currentChar;
            }
            //Process Comment
            if(!(insideChar || insideQuote) && currentChar == '/' && fileCharacters[index + 1] == '/') {
                delimiterStack.push(new Delimiter(currentChar, index, 5, lineNumber));
                index += 2;
                currentChar = fileCharacters[index++];
                insideComment = true;
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
        // System.out.println(delimiterStack);
        index ++;
        return currentChar;
    }

    public String getLineNumberAndChar() {
        return "Line : " + lineNumber + " Index : " + index;
    }
 
    private boolean isDelimiter() {
        if(insideQuote || insideComment || insideChar)
            return false;

        for(var d : delimiters) {
            if(currentChar == d) {
                if(currentChar == '"')
                    insideQuote = true;
                else if(currentChar == '\'')
                    insideChar = true;

                return true;
            }
        }

        return false;
    }

    private boolean isDelimiterEnd() {
        if(insideChar && currentChar != '\'')
            return false;
        if(insideQuote && currentChar != '"')
            return false;
        if(insideComment && currentChar != '\n')
            return false;

        for(var d : delimiterEnds) {
            if(currentChar == d) {
                if(insideComment && currentChar == '\n') {
                    return true;
                } else if(currentChar != '\n') {
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
}