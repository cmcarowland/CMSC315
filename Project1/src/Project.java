import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project {
    static private String fileName;
    static private Scanner scanner;
    static private JavaValidator jv;

    public static void main(String[] args) throws FileNotFoundException {
        try {
            scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.print("Enter file path to verify : ");

            fileName = scanner.nextLine();  // Read user input
            jv  = new JavaValidator(fileName);
            if(jv.validateFile())
                System.out.println(fileName + " is a valid java file.");
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            scanner.close();
        }
    }
}