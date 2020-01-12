package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import system.Validator;

public class ValidatorTest {
  Validator myValidator;
  String[] testInputSplit0;
  String[] testInputSplit1;
  String[] testInputSplit2;
  @Before
  public void setUp(){
    myValidator = new Validator();
    testInputSplit0 = {"cd", "CSCB07/Assignment2/PartB"};
    testInputSplit1 = {"man", "cd", "ls", "mkdir"};
    testInputSplit2 = {"MAN", "man"};
  }

  @After
  public void tearDown(){
    
  }

  @Test
  public void testFormatForRedirection() {
  }
  @Test
  public void testCheckInput() {
    //tests input that is valid
    assertEquals(true, Validator.checkInput(testInputSplit0));
    //tests input that has too many arguments
    assertEquals(false, Validator.checkInput(testInputSplit1));
    //tests input with command having wrong case. commands are case sensitive.
    assertEquals(false, Validator.checkInput(testInputSplit2));
  }
}
