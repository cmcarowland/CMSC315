import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaValidator {
    public String filePath;
    public String fileString;

    public JavaValidator(String filePath) throws FileNotFoundException
    {
        //Validate path exists and can be opened
        this.filePath = filePath;
        if(DoesFileExist()) {
            //Open File and read into buffer
            fileString = OpenFile();
        }
        else {
            throw new FileNotFoundException("The file was not found");
        }
    }

    private boolean DoesFileExist()
    {
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        
        return false;
    }

    public String OpenFile() {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path);
        } catch (Exception e) {
            return "";
        }
    }
}
