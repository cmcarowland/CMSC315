/*
 * Raymond Rowland
 * Project 1
 * 8/25/2024
 * 
 * This class serves to test the validation techniques in the JavaValidator
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ProjectTest {
    @Test 
    public void JVValid() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/src/Project.java");
            assertEquals(true, jv.fileCharacters.length > 0);
        } catch(Exception e) {
            assertEquals(0, 1);
        }
    }
   
    @Test 
    public void JVValidProject() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/src/Project.java");
            assertEquals(true, jv.validateFile());
        } catch(Exception e) {
            assertEquals(0, 1);
        }
    }
    
    @Test 
    public void JVValidDelimiter() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/src/Delimiter.java");
            assertEquals(true, jv.validateFile());
        } catch(Exception e) {
            assertEquals(0, 1);
        }
    }
   
    @Test 
    public void JVValidValidator() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/src/JavaValidator.java");
            assertEquals(true, jv.validateFile());
        } catch(Exception e) {
            assertEquals(0, 1);
        }
    }

    @Test 
    public void JVInvalidFile() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/asd.java");
            assertEquals(null, jv);
        } catch(Exception e) {
            assertEquals(1, 1);
        }
    }
    
    @Test 
    public void JVFailClose() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Close_Bracket.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }

    @Test 
    public void JVFailMissingOpen() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Open_Bracket.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }

    @Test 
    public void JVFailMissingOpenBrace() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Open_Brace.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }
    
    @Test 
    public void JVFailMissingCloseBrace() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Close_Brace.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }

    @Test 
    public void JVFailQuote() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Quote.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }
    
    @Test 
    public void JVFailChar() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Char_Quote.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }
   
    @Test 
    public void JVFailBlock() {
        try {
            JavaValidator jv = new JavaValidator("/workspaces/CMSC315/Project1/TestFiles/Test_Fail_Block.java");
            assertEquals(false, jv.validateFile());
        } catch(Exception e) {
            assertEquals(false, true);
        }
    }
}
