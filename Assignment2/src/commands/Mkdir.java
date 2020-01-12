package commands;

import system.FileSystem;
import system.InputHistory;

/**
 * A command that creates a directory in another directory specified by a path.
 */
public class Mkdir implements CommandExecutor{
  
  /**
   * Executes mkdir command
   */
  public String execute(FileSystem fileSystem, String[] arguments, InputHistory inputHistory) {
    for (String argument : arguments) {
      fileSystem.createDirectory(argument);
    }
    
    return null;
  }

}
