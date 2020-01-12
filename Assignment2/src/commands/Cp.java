package commands;

import system.Content;
import system.Directory;
import system.File;
import system.FileSystem;
import system.InputHistory;
import system.Validator;

/**
 * A command that copies a file into another directory
 */
public class Cp implements CommandExecutor{
  
  /** Error message for unable to copy file */
  private final static String FILEERRORMSG = "Error: Cannot copy to a file";
  
  /** Error message for invalid path */
  private final static String INVALIDPATHERRORMSG = "Error: Invalid path provided";
  
  /** Error message for same names */
  private final static String SAMENAMEERRORMSG = "Error: Directory of this name already exists";
  
  boolean pathOne, pathTwo;
  
  /**
   * Executes cp command
   */
  public String execute(FileSystem fileSystem, String[] arguments, InputHistory inputHistory) {
    // Check for 2 parameters only! Not just making an assumption.
    pathOne = fileSystem.validatePath(arguments[0]);
    pathTwo = fileSystem.validatePath(arguments[1]); 
    if (pathOne && pathTwo){
      try {
        String pathTwoTrue = Validator.formatPath(arguments[1]);
        Content contentPathOne = fileSystem.getContentFromPath(Validator.formatPath(arguments[0]));
        Content contentPathTwo = fileSystem.getContentFromPath(Validator.formatPath(arguments[1]));
        if (contentPathTwo instanceof Directory) {
          if (contentPathOne instanceof File) {
            ((Directory)contentPathTwo).addContent(new File(contentPathOne.getName(),
                pathTwoTrue + contentPathOne.getName(),
                ((File) contentPathTwo).readData()));
          }
          else {
            // Recursively create a copy of every item in the directory.
            recursiveCopy((Directory) contentPathOne, (Directory)contentPathTwo);
          }
          
        }
        else {
          return FILEERRORMSG;
        }
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }
  
  /**
   * Implements copy by recursively copying the contents
   * 
   * @param copyNode Directory which is to be copied
   * @param copyToNode Directory location where copy should be sent
   */
  private void recursiveCopy(Directory copyNode, Directory copyToNode)
  {
    // This assumes that you do not already have a directory with the same name in the 
    // copyToNode directory.
    Directory newDirectory = new Directory(copyNode.getName(), copyToNode.getPath() + copyNode.getName());
    copyToNode.addContent(newDirectory);
    for (Content content : copyNode.getContents()) {
      if (content instanceof File) {
        newDirectory.addContent(new File(content.getName(), 
            newDirectory.getPath() + content.getName(),
            ((File) content).readData()));
      }
      else {
        recursiveCopy((Directory) content, newDirectory);
      }
    }
  }
}
