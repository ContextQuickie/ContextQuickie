package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author ContextQuickie
 * 
 *         Class to compare two selected items using Beyond Compare.
 *
 */
public class Compare extends AbstractHandler
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
    final TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
    final Object[] paths = selection.toArray();
    final IAdapterManager adapterManager = Platform.getAdapterManager();
    final IResource leftResource = adapterManager.getAdapter(paths[0], IResource.class);
    final IResource rightResource = adapterManager.getAdapter(paths[1], IResource.class);
    if ((leftResource != null) && (rightResource != null))
    {
      BeyondCompare.compare(leftResource.getLocation().toString(), rightResource.getLocation().toString());
    }

    return null;
  }
}
