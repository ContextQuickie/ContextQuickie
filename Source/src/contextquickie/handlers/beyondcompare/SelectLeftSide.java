package contextquickie.handlers.beyondcompare;

import contextquickie.tools.WorkbenchUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author ContextQuickie
 * 
 *         Class which implements the "Select Left Side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SelectLeftSide extends AbstractHandler
{
  @Override
  public final Object execute(final ExecutionEvent event) throws ExecutionException
  {
    final ISelection selection = HandlerUtil.getCurrentSelection(event);
    IResource resource = null;
    if (selection instanceof TreeSelection)
    {
      final IAdapterManager adapterManager = Platform.getAdapterManager();
      final TreeSelection treeSelection = (TreeSelection) selection;
      resource = adapterManager.getAdapter(treeSelection.getFirstElement(), IResource.class);
    }
    else if (selection instanceof TextSelection)
    {
      resource = WorkbenchUtil.getCurrentDocument();
    }
    if (resource != null)
    {
      final BeyondCompare bc = new BeyondCompare();
      final int resourceType = resource.getType();
      if ((resourceType == IResource.FOLDER) || (resourceType == IResource.PROJECT))
      {
        bc.setSavedLeft(resource.getLocation().toString());
        bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
        bc.writeRegistry();
      }
      else if (resourceType == IResource.FILE)
      {
        bc.setSavedLeft(resource.getLocation().toString());
        bc.setSavedLeftType(BeyondCompareSavedLeft.File);
        bc.writeRegistry();
      }
    }
    return null;
  }
}
