package contextquickie.handlers.tortoise;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.Activator;
import contextquickie.tools.ProcessWrapper;

public abstract class TortoiseMergeCommand extends AbstractHandler {

  /**
   * @return The preference name of the merge command path.
   */
  protected abstract String getMergeCommandPathName();
  
  /* (non-Javadoc)
   * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  @Override
  public Object execute(ExecutionEvent event) {
    List<String> arguments = new ArrayList<String>();
    String command = Activator.getDefault().getPreferenceStore().getString(this.getMergeCommandPathName());

    TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
    if (selection.isEmpty() == false) {
      IAdapterManager adapterManager = Platform.getAdapterManager();
      IResource resource = adapterManager.getAdapter(selection.getFirstElement(), IResource.class);
      if (resource != null) {
        arguments.add("/patchpath:" + resource.getLocation().toString());
        ProcessWrapper.executeCommand(command, arguments);
      }
    }

    return null;
  }
}
