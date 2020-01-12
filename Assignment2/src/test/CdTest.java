package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Cd;
import system.FileSystem;
import system.InputHistory;

public class CdTest {

  /**
   * Instantiate FileSystem
   */
  private FileSystem fileSystem;
  
  /**
   * Instantiate inputHistory
   */
  private InputHistory inputHistory;

  /**
   * Instantiate Cd
   */
  private Cd cd;

  /**
   * Create arguments string array
   */
  private String[] arguments;
  
  String actual;
  String expected;

  /**
   * Setup before each test case
   */
  @Before
  public void setUp() {
    inputHistory = new InputHistory();
    // Create new FileSystem
    fileSystem = FileSystem.createRoot();

    // Create Cd instance
    cd = new Cd();

    // Create directories
    fileSystem.createDirectory("/first");
    fileSystem.createDirectory("/first/second");
  }

  /**
   * Re-build FileSystem after each test case
   */
  @After
  public void tearDown() {
    fileSystem.rebuild();
  }

  /**
   * Test Cd execute method valid arguments
   */
  @Test
  public void testValidArguments(){
    expected = "first";
    // Set arguments
    arguments = new String[] {"/first"};

    // Execute Cd
    cd.execute(fileSystem, arguments, inputHistory);

    // Check location in FileSystem
    assertEquals(expected, fileSystem.getCurrDir().getName());
    
    expected = "second";
    // Set arguments
    arguments = new String[] {"/first/second"};

    // Execute Cd
    cd.execute(fileSystem, arguments, inputHistory);

    // Check location in FileSystem
    assertEquals(expected, fileSystem.getCurrDir().getName());

  }
  
  /**
   * Test Cd execute command with no arguments
   */
  @Test()
  public void testCdWithNoArguments(){
    expected = null;
    
    // Set arguments
    arguments = new String[] {};

    // Execute Cd
    actual = cd.execute(fileSystem, arguments, inputHistory);
    assertEquals(expected, actual);
  }

  /**
   * Test Cd execute command with multiple arguments
   */
  @Test()
  public void testCdWithMultipleArguments(){
    expected = null;
    
    // Set arguments
    arguments = new String[] {"/first", "/first/second"};

    // Execute Cd
    actual = cd.execute(fileSystem, arguments, inputHistory);
    assertEquals(expected, actual);
  }

  /**
   * Test Cd execute command with multiple parameters
   */
  @Test()
  public void testCdWithInvalidPath() {
    expected = "Error: Invalid Path Provided";
    
    // Set arguments
    arguments = new String[] {"/first/third"};

    // Execute Cd
    actual = cd.execute(fileSystem, arguments, inputHistory);
    
    // Check Error message
    assertEquals(expected, actual);
  }
}
