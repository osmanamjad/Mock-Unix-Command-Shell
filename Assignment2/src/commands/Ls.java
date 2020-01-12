package commands;

import java.util.List;
import system.Content;
import system.Directory;
import system.FileSystem;
import system.IO;
import system.InputHistory;
import system.Validator;

/**
 * A command that prints the contents of the directory at a specified location.
 */
public class Ls implements CommandExecutor{
  
  /** Error message for an invalid path */
  private final static String ERRORMSG = "Error: Invalid Path Provided";
  
  /**
   * Executes ls command
   */
  public String execute(FileSystem fileSystem, String[] arguments, InputHistory inputHistory) {
    if (arguments.length == 0) {
      List<Content> contentCurrDir = fileSystem.getCurrDir().getContents();
      for (Content content : contentCurrDir) {
        IO.Output(content.getName()); // Outputs each content name in the current directory
      }
    } else {
      for (String argument : arguments) {
        try {
          // Outputs the content
          IO.Output(fileSystem.getContentFromPath(Validator.formatPath(argument)));
        } catch (Exception e) {
          return ERRORMSG;
        }
      }
    }
    
    return null;
  }
  
  private static void recurse(Directory directoryNode)
  {
    List<Content> contentOfNode = directoryNode.getContents();
    IO.Output("Contents of: " + directoryNode.getName());
    for (Content content : contentOfNode)
    {
      IO.Output(content.getName());
    }
    for (Content content : contentOfNode)
    {
      if (content instanceof Directory)
      {
        Ls.recurse((Directory) content);
      }
    }
  }
  
  // Catch error if asked to recurse file, not directory.
  
  public String executeRecursive(FileSystem fileSystem, String[] arguments)
  {
    if (arguments.length == 0)
    {
      Directory contentCurrDir = fileSystem.getCurrDir();
      recurse(contentCurrDir);
    }
    else
    {
      for (String argument : arguments)
      {
        Content contentAtPath;
        try {
          contentAtPath = fileSystem.getContentFromPath(Validator.formatPath(argument));
          if (contentAtPath instanceof Directory)
          {
            Ls.recurse((Directory) contentAtPath);
          }
        } catch (Exception e) {
          // TODO Auto-generated catch block
          return ERRORMSG;
        }
      }
    }
    return "";
  }
}
