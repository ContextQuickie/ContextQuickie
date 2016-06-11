package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.tools.WorkbenchUtil;

import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * @author ContextQuickie
 * 
 *         Class which implements the "Select Left Side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SelectLeftSide extends AbstractHandler {

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {

    ISelection selection = HandlerUtil.getCurrentSelection(event);
    IResource resource = null;
    if (selection instanceof TreeSelection) {
      IAdapterManager adapterManager = Platform.getAdapterManager();
      TreeSelection treeSelection = (TreeSelection) selection;
      resource = adapterManager.getAdapter(treeSelection.getFirstElement(), IResource.class);
    } else if (selection instanceof TextSelection) {
      resource = WorkbenchUtil.getCurrentDocument();
    }
    if (resource != null) {
      BeyondCompare bc = new BeyondCompare();
      int resourceType = resource.getType();
      if ((resourceType == IResource.FOLDER) || (resourceType == IResource.PROJECT)) {
        bc.setSavedLeft(resource.getLocation().toString());
        bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
        bc.writeRegistry();
      } else if (resourceType == IResource.FILE) {
        bc.setSavedLeft(resource.getLocation().toString());
        bc.setSavedLeftType(BeyondCompareSavedLeft.File);
        bc.writeRegistry();
      }
    }
    return null;
  }
}
