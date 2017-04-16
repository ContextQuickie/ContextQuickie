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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

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
  public Object execute(final ExecutionEvent event) throws ExecutionException
  {

    final ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection != null)
    {

      final IAdapterManager adapterManager = Platform.getAdapterManager();
      if ((selection != null) && (selection.isEmpty() == false))
      {
        Object receiver = null;
        // Context menu has been opened in a tree view
        if (selection instanceof IStructuredSelection)
        {
          final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
          receiver = structuredSelection.getFirstElement();
        }
        else if (selection instanceof TextSelection)
        {
          receiver = WorkbenchUtil.getCurrentDocument();
        }

        final IResource rightResource;
        rightResource = adapterManager.getAdapter(receiver, IResource.class);
        if (rightResource != null)
        {
          final BeyondCompare bc = new BeyondCompare();
          bc.readRegistry();
          final String savedLeft = bc.getSavedLeft();
          BeyondCompare.compare(savedLeft, rightResource.getLocation().toString());
        }
      }
    }

    return null;
  }
}
