package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

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

    final ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof TreeSelection)
    {
      final TreeSelection treeSelection = (TreeSelection) selection;
      if (treeSelection.size() == 2)
      {
        final List<?> structuredSelection = treeSelection.toList();
        if ((structuredSelection.get(0) instanceof IAdaptable) && (structuredSelection.get(1) instanceof IAdaptable))
        {
          final IAdaptable firstPathAdaptable = (IAdaptable) structuredSelection.get(0);
          final IAdaptable secondPathAdaptable = (IAdaptable) structuredSelection.get(1);
          final IResource firstPathResource = firstPathAdaptable.getAdapter(IResource.class);
          final IResource secondPathResource = secondPathAdaptable.getAdapter(IResource.class);
          if ((firstPathResource != null) && (secondPathResource != null))
          {
            arguments.add("/path:" + StringUtil.quoteString(firstPathResource.getLocation().toString()));
            arguments.add("/path2:" + StringUtil.quoteString(secondPathResource.getLocation().toString()));
          }
        }
      }
    }

    new ProcessWrapper().executeCommand(command, null, arguments);

    return null;
  }

}
