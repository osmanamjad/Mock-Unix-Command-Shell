package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Popd;
import commands.Pushd;
import system.FileSystem;
import system.InputHistory;

public class PopdTest {

  /**
   * Instantiate FileSystem
   */
  private FileSystem fileSystem;
  
  /**
   * Instantiate inputHistory
   */
  private InputHistory inputHistory;

  /**
   * Instantiate Popd
   */
  private Popd popd;

  /**
   * Create arguments string array
   */
  private String[] arguments;

  /**
   * Instantiate Pushd
   */
  private Pushd pushd;
  
  String expected;
  String actual;

  /**
   * Before each test case
   */
  @Before
  public void setUp(){
    inputHistory = new InputHistory();
    // create new FileSystem
    fileSystem = FileSystem.createRoot();

    // create new Popd object
    popd = new Popd();

    // create new Pushd Object
    pushd = new Pushd();

    // make directories
    fileSystem.createDirectory("/test");
    fileSystem.createDirectory("/test/test1");

    // Set arguments for pushd
    arguments = new String[] {"/test"};

    // execute Popd
    pushd.execute(fileSystem, arguments, inputHistory);

    // Set arguments for pushd
    arguments = new String[] {"/test/test1"};

    // execute Popd
    pushd.execute(fileSystem, arguments, inputHistory);


  }

  /**
   * After each test case
   */
  @After
  public void tearDown() {
    // Rebuild fileSystem
    fileSystem.rebuild();
  }

  /**
   * Test execute
   */
  @Test
  public void testExecute() {
    expected = "test";
    // Set arguments
    arguments = new String[] {};
    // execute Popd
    popd.execute(fileSystem, arguments, inputHistory);

    assertEquals(expected, fileSystem.getCurrDir().getName());
    
    expected = "/";
    // Set arguments
    arguments = new String[] {};

    // execute Popd
    popd.execute(fileSystem, arguments, inputHistory);

    assertEquals(expected, fileSystem.getCurrDir().getName());
  }

  /**
   * Test Popd execute command with multiple arguments
   */
  @Test()
  public void testPopdMultipleArguments()  {
    
    expected = null;
    // Set arguments
    arguments = new String[] {"/test", "/test/test1"};

    // execute Popd
    actual = popd.execute(fileSystem, arguments, inputHistory);
    
    assertEquals(expected, actual);
  }
}
