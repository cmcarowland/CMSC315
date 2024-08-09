public class Delimiter {
    public Character character;
    public int index;
    public int delimeterIndex;
    public int lineNumber;

    public Delimiter(Character c, int i, int di, int ln) {
        character = c;
        index = i;
        delimeterIndex = di;
        lineNumber = ln;
    }

    public String toString() {
        return "Char: " + character + " File Index: " + index + " Delimiter Index: " + delimeterIndex + " Line: " + lineNumber;
    }
}
