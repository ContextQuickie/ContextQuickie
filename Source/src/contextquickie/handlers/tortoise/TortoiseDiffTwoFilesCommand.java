package contextquickie.handlers.tortoise;

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

import contextquickie.Activator;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

public abstract class TortoiseDiffTwoFilesCommand extends AbstractHandler {

  /**
   * @return The preference name of the command path.
   */
  protected abstract String getCommandPathName();
  
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    List<String> arguments = new ArrayList<String>();
    String command = Activator.getDefault().getPreferenceStore().getString(this.getCommandPathName());

    arguments.add("/command:diff");

    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof TreeSelection) {
      TreeSelection treeSelection = (TreeSelection) selection;
      if (treeSelection.size() == 2) {
        List<?> structuredSelection = treeSelection.toList();
        if ((structuredSelection.get(0) instanceof IAdaptable) &&
            (structuredSelection.get(1) instanceof IAdaptable)) {
          IAdaptable firstPathAdaptable = (IAdaptable) structuredSelection.get(0);
          IAdaptable secondPathAdaptable = (IAdaptable) structuredSelection.get(1);
          IResource firstPathResource = firstPathAdaptable.getAdapter(IResource.class);
          IResource secondPathResource = secondPathAdaptable.getAdapter(IResource.class);
          if ((firstPathResource != null) && (secondPathResource != null)) {
            arguments.add("/path:" + StringUtil.QuoteString(firstPathResource.getLocation().toString()));
            arguments.add("/path2:" + StringUtil.QuoteString(secondPathResource.getLocation().toString()));
          }
        }
      }
    }

    ProcessWrapper.executeCommand(command, arguments);

    return null;
  }

}
