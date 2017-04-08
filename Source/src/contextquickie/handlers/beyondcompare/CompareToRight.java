package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.tools.WorkbenchUtil;

/**
 * @author ContextQuickie
 *
 *         Class which implements the "Compare to right side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CompareToRight extends AbstractHandler
{

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
   * ExecutionEvent)
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {

    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection != null)
    {

      IAdapterManager adapterManager = Platform.getAdapterManager();
      if ((selection != null) && (selection.isEmpty() == false))
      {
        Object receiver = null;
        // Context menu has been opened in a tree view
        if (selection instanceof IStructuredSelection)
        {
          IStructuredSelection structuredSelection = (IStructuredSelection) selection;
          receiver = structuredSelection.getFirstElement();
        }
        else if (selection instanceof TextSelection)
        {
          receiver = WorkbenchUtil.getCurrentDocument();
        }

        IResource rightResource;
        rightResource = adapterManager.getAdapter(receiver, IResource.class);
        if (rightResource != null)
        {
          BeyondCompare bc = new BeyondCompare();
          bc.readRegistry();
          String savedLeft = bc.getSavedLeft();
          BeyondCompare.compare(savedLeft, rightResource.getLocation().toString());
        }
      }
    }

    return null;
  }
}
