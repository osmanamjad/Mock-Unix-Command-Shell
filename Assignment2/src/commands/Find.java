package commands;
import system.FileSystem;
import system.InputHistory;
import system.Validator;
import system.InputHistory;
import system.Directory;
import system.Content;

/**
 * A command that finds a file or a set of files
 */
public class Find implements CommandExecutor{
  /** Error message for invalid path*/
  private final static String INVALIDPATHERRORMSG = "Error: Invalid path"; 
  /** Error message for invalid characters*/
  private final static String INVALIDCHARSERRORMSG = "Error: Invalid Characters"; 
  /** Array of strings to keep track of what paths to check*/
  private static String[] paths;
  /** String to keep track of whether what we're looking for is file or directory*/
  private static String inputType;
  /** Array of strings to keep track of what paths to check*/
  private static String inputName;
  /**
   * Executes find command
   */
  public String execute(FileSystem fileSystem, String[] arguments, InputHistory inputHistory) {
    String returnString = "";
    String startDir = fileSystem.getCurrDirPath();
    returnString = parseArguments(returnString, arguments);
    for(int i=0;i<paths.length;i++) {
      paths[i] = Validator.formatPath(paths[i]);
      fileSystem.setCurrDirWithPath(paths[i]);
      ArrayList<Content> contents = fileSystem.getCurrDir().
          getContents();
      for(int i=0;i<contents.length;i++) {
        if(contents[i].equals(inputName)){
          returnString += inputName + " found at the following full path: " 
              + fileSystem.getCurrDirPath();
        }
      }
    }
    return returnString;
  }
  /**
   * Helper method for find command: goes thru arguments in the input and separates them into paths,
   * inputType (d or f), and inputName (name of file or directory)
   * 
   * @param returnString the current string in execute command - starts off empty
   * @param arguments array of Strings that has all the arguments in the input
   * @return String the string that is the updated returnString (with error messages);
   * 
   */
  private static String parseArguments(String returnString, String[] arguments) {
    for(int i=0; i<arguments.length;i++) {
      if(Validator.containsInvalidChars(arguments[i])) {
        returnString += INVALIDCHARSERRORMSG + "\n";
      }
      if(arguments[i].contains("/")) {
        if(!Validator.pathCheck(arguments[i])) {
          returnString += INVALIDPATHERRORMSG + "\n";
        }
        paths.append(arguments[i]);
      }
      else if(arguments[i].length() == 1 && arguments[i-1].equals("-type") && 
          (arguments[i].equals("f") || arguments[i].equals("d"))) {
        inputType = arguments[i]; 
      }
      else {
        inputName = arguments[i];
      }
    }
  }
}
