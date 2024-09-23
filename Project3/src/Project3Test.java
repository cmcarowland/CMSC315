/*
 * Raymond Rowland
 * Project 3
 * 9/23/2024
 * 
 * The Project3Test class contains unit tests for the Project3 application,
 * validating various scenarios for binary search tree input. Tests cover 
 * cases such as complete trees, incomplete trees, invalid data, and extra
 * characters, ensuring that appropriate exceptions and messages are generated.
 * The tests also verify the correct structure and balance of the constructed 
 * trees, using regex patterns to match expected outputs.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project3Test {

    @Test 
    public void TestComplete() {
        Pattern pattern = Pattern.compile("It is a balanced binary search tree", Pattern.CASE_INSENSITIVE);
        MockInOut mio = new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))\nn\n");
        Project3.main(new String[0]);
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
       
    @Test 
    public void TestIncomplete() {
        Pattern pattern = Pattern.compile("Tree is incomplete", Pattern.CASE_INSENSITIVE);
        MockInOut mio = new MockInOut("(53 (28 (11 *) (41 * *)) (83 (67 * *) *))\nn\n");
        Project3.main(new String[0]);
        String x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void TestInvalidData() {
        Pattern pattern = Pattern.compile("Data is not an Integer", Pattern.CASE_INSENSITIVE);
        MockInOut mio = new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * A) *))\nn\n");
        Project3.main(new String[0]);
        String x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void TestExtra() {
        Pattern pattern =Pattern.compile("Extra Characters at the End", Pattern.CASE_INSENSITIVE);
        MockInOut mio =new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))1234\nn\n");
        Project3.main(new String[0]);
        String x =mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
     
    @Test 
    public void TestMissingLeft() {
        Pattern pattern =Pattern.compile("Missing Left Parenthesis", Pattern.CASE_INSENSITIVE);
        MockInOut mio =new MockInOut("53 (28 (11 * *) (41 * *)) (83 (67 * *) *))\nn\n");
        Project3.main(new String[0]);
        String x =mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void TestMissingLeftInside() {
        Pattern pattern =Pattern.compile("Missing Left Parenthesis", Pattern.CASE_INSENSITIVE);
        MockInOut mio =new MockInOut("(53 (28 (11 * *) (41 * *)) 83 (67 * *) *))\nn\n");
        Project3.main(new String[0]);
        String x =mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }  
    
    @Test 
    public void TestMissingRight() {
        Pattern pattern =Pattern.compile("Missing Right Parenthesis", Pattern.CASE_INSENSITIVE);
        MockInOut mio =new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * * *)) \nn\n");
        Project3.main(new String[0]);
        String x =mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
     
    @Test 
    public void TestRightInside() {
        Pattern pattern =Pattern.compile("Missing Right Parenthesis", Pattern.CASE_INSENSITIVE);
        MockInOut mio =new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * *) *)\nn\n");
        Project3.main(new String[0]);
        String x =mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void Test1() {
        Pattern pattern = Pattern.compile("53\\n" + //
                        "\\t28\\n" + //
                        "\\t\\t11\\n" + //
                        "\\t\\t41\\n" + //
                        "\\t83\\n" + //
                        "\\t\\t67", Pattern.CASE_INSENSITIVE);
                        
        MockInOut mio = new MockInOut("(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))\nN");
        Project3.main(new String[0]);
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
        pattern = Pattern.compile("It is a balanced binary search tree", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void Test2() {
        Pattern pattern = Pattern.compile("63\\n" + //
                        "\\t51\\n" + //
                        "\\t\\t20\\n" + //
                        "\\t\\t\\t13", Pattern.CASE_INSENSITIVE);
                        
        MockInOut mio = new MockInOut("(63 (51 (20 (13 * *) *) *) *)\nN");
        Project3.main(new String[0]);
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
        pattern = Pattern.compile("It is a binary search tree but it is not balanced", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());

        pattern = Pattern.compile("20\\n" + //
                        "\\t13\\n" + //
                        "\\t51\\n" + //
                        "\\t\\t63", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());

        pattern = Pattern.compile("Original tree has height 3\nBalanced tree has height 2", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }

    @Test 
    public void Test3() {
        Pattern pattern = Pattern.compile("13\\n" + //
                        "\\t53\\n" + //
                        "\\t11\\n" + //
                        "\\t\\t59", Pattern.CASE_INSENSITIVE);
                        
        MockInOut mio = new MockInOut("(13 (53 * *) (11 (59 * *) *))\nN");
        Project3.main(new String[0]);
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
        pattern = Pattern.compile("It is not a binary search tree", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());

        pattern = Pattern.compile("13\\n" + //
                        "\\t11\\n" + //
                        "\\t53\\n" + //
                        "\\t\\t59", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());

        pattern = Pattern.compile("Original tree has height 2\nBalanced tree has height 2", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
}