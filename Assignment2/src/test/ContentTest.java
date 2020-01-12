package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import system.Content;

public class ContentTest {
  public Content testContent;
  public Content testContent1;
  public Content testContent2;
  String expected;

  /**
   * Before each test, create new Content
   */
  @Before
  public void setUp() {
    testContent = new Content("TestContent", "/utsc");
  }

  /**
   * Test Constructor
   */
  @Test
  public void testConstructor() {
    expected = "/utsc";
    
    // Checks for Content path
    assertEquals(expected, testContent.getPath());
    
    expected = "TestContent";
    
    // Checks for Content name
    assertEquals(expected, testContent.getName());

  }

  /**
   * Test setName
   */
  @Test
  public void testSetName() {
    expected = "NewName";
    
    // Set content name
    testContent.setName("NewName");
    
    assertEquals(expected, testContent.getName());
  }

  /**
   * Test setPath
   */
  @Test
  public void testSetPath() {
    expected = "/utsc";
    
    // Set content path
    testContent.setPath("/utsc");
    
    assertEquals(expected, testContent.getPath());
  }
  
  /**
   * Test setPath
   */
  @Test
  public void testClone() {
    expected = "/utsc";
    
    // Clone testContent
    Content clone = testContent.clone();
    
    assertEquals(expected, clone.getPath());
    
    expected = "TestContent";
    
    assertEquals(expected, clone.getName());
  }
}
