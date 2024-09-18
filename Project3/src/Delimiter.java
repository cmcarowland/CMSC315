/*
 * Raymond Rowland
 * Project 1
 * 8/25/2024
 * 
 * Represents a delimiter character in a text file.
 * The class stores details about the delimiter including the character itself,
 * its position in the file, its type, and the line number where it appears.
 * 
 * The delimiter type is described by an index into the delimiterDescription
 * array, which includes types such as "Brace", "Parenthesis", "Bracket", etc.
 */

public class Delimiter {
    public Character character;
    public int index;
    public int delimeterIndex;
    public int lineNumber;

    private String[] delimiterDescription = new String[] {"Brace", "Parenthese", "Bracket", "Quote", "Character", "Comment", "Block Comment"};

    public Delimiter(Character c, int i, int di, int ln) {
        character = c;
        index = i;
        delimeterIndex = di;
        lineNumber = ln;
    }

    @Override
    public String toString() {
        return "Opened " + delimiterDescription[delimeterIndex] + " " + "Char: '" + character + "' File Index: " + index + " Delimiter Index: " + delimeterIndex + " Line: " + lineNumber;
    }

    public String getDescription() {
        return delimiterDescription[delimeterIndex];
    }
}
