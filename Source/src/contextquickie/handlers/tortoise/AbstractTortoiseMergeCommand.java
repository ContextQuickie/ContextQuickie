package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;

/**
 * Base class for execute a Tortoise merge command.
 */
public abstract class AbstractTortoiseMergeCommand extends AbstractHandler
{

  /**
   * @return The preference name of the merge command path.
   */
  protected abstract String getMergeCommandPathName();

  @Override
  public final Object execute(final ExecutionEvent event)
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = Activator.getDefault().getPreferenceStore().getString(this.getMergeCommandPathName());
    final IResource resource = new ContextMenuEnvironment().getSelectedResources().stream().findFirst().orElse(null);
    if (resource != null)
    {
      arguments.add("/patchpath:" + resource.getLocation().toString());
      new ProcessWrapper().executeCommand(command, null, arguments);
    }

    return null;
  }
}
