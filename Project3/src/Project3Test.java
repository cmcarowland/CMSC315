import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project3Test {

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
   
    // @Test
    // public void Test2() {
    //     Pattern pattern = Pattern.compile("[\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After addAll: list1 is \\[red, green, blue, yellow, cyan, blue, yellow, cyan, orange, purple\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After removeAll: list1 is \\[red, green\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After retainAll: list1 is \\[blue, yellow, cyan\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*list1 contains all list2\\? false[\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, red\\][\\s\\S]*list1 contains all list2\\? true[\\s\\S]*name4: red green blue yellow cyan[\\s\\S]*name6: red green blue yellow cyan[\\s\\S]*", Pattern.CASE_INSENSITIVE);
    //     MockInOut mio = new MockInOut("red green blue yellow cyan\nblue yellow cyan orange purple\nblue red");
    //     Exercise e = new Exercise();
    //     var x = mio.getOutput();
    //     mio.close();
    //     System.out.println(x);
    //     Matcher matcher = pattern.matcher(x);
    //     assertEquals(true, matcher.find());
    // }
}