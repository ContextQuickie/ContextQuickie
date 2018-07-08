package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;

/**
 * Base class for execute a Tortoise diff command with two selected files.
 */
public abstract class AbstractTortoiseDiffTwoFilesCommand extends AbstractHandler
{
  /**
   * @return The preference name of the command path.
   */
  protected abstract String getCommandPathName();

  @Override
  public final Object execute(final ExecutionEvent event) throws ExecutionException
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = Activator.getDefault().getPreferenceStore().getString(this.getCommandPathName());

    arguments.add("/command:diff");
    Set<IResource> selectedResources = new ContextMenuEnvironment().getSelectedResources();
    if (selectedResources.size() == 2)
    {
      Iterator<IResource> iterator = selectedResources.iterator();
      final IResource firstPathResource = iterator.next();
      final IResource secondPathResource = iterator.next();
      if ((firstPathResource != null) && (secondPathResource != null))
      {
        arguments.add("/path:" + StringUtil.quoteString(firstPathResource.getLocation().toString()));
        arguments.add("/path2:" + StringUtil.quoteString(secondPathResource.getLocation().toString()));
      }
    }

    ProcessWrapper.executeCommand(command, arguments);

    return null;
  }

}
