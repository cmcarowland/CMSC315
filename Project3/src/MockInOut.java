/*
 * Raymond Rowland
 * Project 3
 * 9/23/2024
 * 
 * MockInOut is a utility class that facilitates testing by allowing 
 * the redirection of standard input and output streams. It captures 
 * the output printed to the console and simulates user input during 
 * tests.
 * 
 * Usage:
 * 1. Create an instance of MockInOut with a string input to simulate 
 *    user input.
 * 2. The constructor replaces System.out and System.in with streams 
 *    that capture output and provide the specified input.
 * 3. Use getOutput() to retrieve the captured output as a string.
 * 4. Call close() to restore the original System.in and System.out.
 * 
 * Note: The class ensures UTF-8 encoding to maintain compatibility 
 * with test files and assertions.
 * 
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


public class MockInOut {

    private PrintStream orig;
    private InputStream irig;
    private ByteArrayOutputStream os;
    private ByteArrayInputStream is;
    
    private final static Charset charset;
    static {
        // Our source and tests files are UTF-8, so we assert against UTF-8 strings
        // and don't want MockInOut to convert to the native charset.
        if (Charset.availableCharsets().containsKey("UTF-8")) {
            charset = Charset.forName("UTF-8");
        } else {
            charset = Charset.defaultCharset();
        }
    }

    public MockInOut(String input) {
        orig = System.out;
        irig = System.in;

        os = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(os, false, charset.name()));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        is = new ByteArrayInputStream(input.getBytes());
        System.setIn(is);
    }

    /**
     * You can use this if you want to check how much of the input was read.
     */
    public ByteArrayInputStream getInputStream() {
        return is;
    }

    /**
     * Returns everything written to System.out since this {@code MockInOut} was
     * constructed. Can't be called on a closed {@code MockInOut}
     */
    public String getOutput() {
        if (os != null) {
            try {
                return os.toString(charset.name()).replace("\r\n", "\n");
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new Error("getOutput on closed MockInOut!");
        }
    }

    /**
     * Restores System.in and System.out
     */
    public void close() {
        os = null;
        is = null;
        System.setOut(orig);
        System.setIn(irig);
    }
}