import java.io.FileNotFoundException;

public class Project {
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("Hello World");
        try {
            JavaValidator jv  = new JavaValidator("/workspaces/CMSC315/Project1/Project.java");
            System.out.println("The File Was Found!!!!");
            System.out.println(jv.fileString);
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }
}