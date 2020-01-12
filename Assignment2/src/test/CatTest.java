package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Cat;
import system.File;
import system.FileSystem;
import system.InputHistory;

public class CatTest {

  /**
   * Instantiate FileSystem
   */
  private FileSystem fileSystem;
  
  /**
   * Instantiate inputHistory
   */
  private InputHistory inputHistory;

  /**
   * Instantiate Cat
   */
  private Cat cat;

  /**
   * Create arguments string array
   */
  private String[] arguments;
  
  String expected;
  String actual;

  /**
   * Before each test case
   * 
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    inputHistory = new InputHistory();
    // create new FileSystem
    fileSystem = FileSystem.createRoot();

    // create new Cat object
    cat = new Cat();

    // create test file
    fileSystem.makeFileAtPath("/testFile");
    ((File)fileSystem.getContentFromPath("/testFile")).writeData("Hello");

    // create second test file
    fileSystem.makeFileAtPath("/testFile1");
    ((File)fileSystem.getContentFromPath("/testFile1")).writeData("Hi");
  }

  /**
   * After each test case
   */
  @After
  public void tearDown() {
    // Re-build FileSystem
    fileSystem.rebuild();
  }

  /**
   * Test Cat execute command with a File
   */
  @Test
  public void testCatFile() {
    expected = "Hello";
    
    // Set arguments
    arguments = new String[] {"/testFile"};

    // Execute Cat command
    actual = cat.execute(fileSystem, arguments, inputHistory);

    assertEquals(expected, actual);

  }

  /**
   * Test Cat execute command with multiple Files
   */
  @Test
  public void testCatWithMultipleFiles() {
    expected = "Hello\n\n\n\nHi";
    
    // Set arguments
    arguments = new String[] {"/testFile", "/testFile1"};

    // Execute Cat command
    actual = cat.execute(fileSystem, arguments, inputHistory);

    assertEquals(expected, actual);

  }
  
  /**
   * Test Cat execute command
   */
  @Test()
  public void testCatInvalidPath() {
    expected = "Error: File not found";
    
    // Set arguments
    arguments = new String[] {"/testFile3"};

    // execute Cat command
    actual = cat.execute(fileSystem, arguments, inputHistory);
    
    assertEquals(expected, actual);
  }

  /**
   * Test Cat execute command with incorrect content type
   */
  @Test()
  public void testCatDirectory() {
    expected = "Error: File not found";
    
    // create directory
    fileSystem.createDirectory("/test");

    // Set arguments
    arguments = new String[] {"/test"};

    // execute Cat command
    actual = cat.execute(fileSystem, arguments, inputHistory);
    
    assertEquals(expected, actual);
  }
}
