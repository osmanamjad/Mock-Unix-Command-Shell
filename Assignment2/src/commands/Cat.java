package commands;

import system.File;
import system.FileSystem;
import system.InputHistory;
import system.Validator;

/**
 * A command that displays the contents of a File.
 */
public class Cat implements CommandExecutor{
  
  /** Error message for a non-existent file */
  private final static String ERRORMSG = "Error: File not found";
  
  /**
   * Executes cat command
   */
  public String execute(FileSystem fileSystem, String[] arguments, InputHistory inputHistory) {
    String returnString = "";
    File fileContent = null;
    for (String argument : arguments) {
      // Adds break lines between different file content
      returnString += "\n\n\n\n";
      // Check if the given file name has any invalid characters
      if (Validator.containsInvalidChars(argument))
        returnString += ERRORMSG;
      // Check if the file exists in the file system
      try {
        fileContent = (File) fileSystem.getContentFromPath(Validator.formatPath(argument));
        returnString += fileContent.readData();
      } catch (Exception e) {
        returnString += ERRORMSG;
      }
    }
    return returnString.trim();
  }
}
