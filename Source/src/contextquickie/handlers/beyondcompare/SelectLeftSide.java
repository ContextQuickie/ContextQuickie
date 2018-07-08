package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import contextquickie.tools.ContextMenuEnvironment;

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
    IResource resource = new ContextMenuEnvironment().getSelectedResources().stream().findFirst().orElse(null);
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
