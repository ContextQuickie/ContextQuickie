package contextquickie.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;

/**
 * @author ContextQuickie
 *
 *         Wrapper class for starting processes.
 */
public final class ProcessWrapper
{
  /**
   * Prevents from creating instances.
   */
  private ProcessWrapper()
  {
  }
  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
   * @param arguments
   *          The arguments of the command.
   */
  public static void executeCommand(final String command, final String... arguments)
  {
    executeCommand(command, null, Arrays.asList(arguments));
  }
  
  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
   * @param arguments
   *          The arguments of the command.
   */
  public static void executeCommand(final String command, Set<IResource> resources, final String... arguments)
  {
    executeCommand(command, resources, Arrays.asList(arguments));
  }

  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
   * @param arguments
   *          The arguments of the command.
   */
  public static void executeCommand(final String command, Set<IResource> resources, final List<String> arguments)
  {
    final List<String> commandAndArguments = new ArrayList<String>();
    commandAndArguments.add(command);
    for (String parameter : arguments)
    {
      if (parameter.contains(" "))
      {
        commandAndArguments.add(StringUtil.quoteString(parameter));
      }
      else
      {
        commandAndArguments.add(parameter);
      }
    }
    final ProcessBuilder processBuilder = new ProcessBuilder(commandAndArguments);
    try
    {
      Process p = processBuilder.start();
      if (resources != null)
      {
        new ResourceRefresher(p, resources).start();
      }
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
