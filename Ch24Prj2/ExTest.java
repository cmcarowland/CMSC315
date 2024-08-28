import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExTest {
    @Test 
    public void Test1() {
        Pattern pattern = Pattern.compile("[\\s\\S]*list1: \\[A, B, C, D, E\\][\\s\\S]*list2: \\[A, B, D, S, F\\][\\s\\S]*After addAll: list1 is \\[A, B, C, D, E, A, B, D, S, F\\][\\s\\S]*list1: \\[A, B, C, D, E\\][\\s\\S]*list2: \\[A, B, D, S, F\\][\\s\\S]*After removeAll: list1 is \\[C, E\\][\\s\\S]*list1: \\[A, B, C, D, E\\][\\s\\S]*list2: \\[A, B, D, S, F\\][\\s\\S]*After retainAll: list1 is \\[A, B, D\\][\\s\\S]*list1: \\[A, B, C, D, E\\][\\s\\S]*list2: \\[A, B, D, S, F\\][\\s\\S]*list1 contains all list2\\? false[\\s\\S]*list1: \\[A, B, C, D, E\\][\\s\\S]*list2: \\[A, H\\][\\s\\S]*list1 contains all list2\\? false[\\s\\S]*name4: A B C D E[\\s\\S]*name6: A B C D E[\\s\\S]*", Pattern.CASE_INSENSITIVE);
        MockInOut mio = new MockInOut("A B C D E\nA B D S F\nA H");
        Exercise e = new Exercise();
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
   
    @Test
    public void Test2() {
        Pattern pattern = Pattern.compile("[\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After addAll: list1 is \\[red, green, blue, yellow, cyan, blue, yellow, cyan, orange, purple\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After removeAll: list1 is \\[red, green\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*After retainAll: list1 is \\[blue, yellow, cyan\\][\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, yellow, cyan, orange, purple\\][\\s\\S]*list1 contains all list2\\? false[\\s\\S]*list1: \\[red, green, blue, yellow, cyan\\][\\s\\S]*list2: \\[blue, red\\][\\s\\S]*list1 contains all list2\\? true[\\s\\S]*name4: red green blue yellow cyan[\\s\\S]*name6: red green blue yellow cyan[\\s\\S]*", Pattern.CASE_INSENSITIVE);
        MockInOut mio = new MockInOut("red green blue yellow cyan\nblue yellow cyan orange purple\nblue red");
        Exercise e = new Exercise();
        var x = mio.getOutput();
        mio.close();
        System.out.println(x);
        Matcher matcher = pattern.matcher(x);
        assertEquals(true, matcher.find());
    }
}