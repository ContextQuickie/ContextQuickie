package contextquickie.handlers.beyondcompare;

import contextquickie.tools.ContextMenuEnvironment;

import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;

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
    Set<IResource> resources = new ContextMenuEnvironment().getSelectedResources();
    final IResource rightResource = resources.stream().findFirst().orElse(null);
    if (rightResource != null)
    {
      final BeyondCompare bc = new BeyondCompare();
      bc.readRegistry();
      final String savedLeft = bc.getSavedLeft();
      BeyondCompare.compare(savedLeft, rightResource.getLocation().toString());
    }

    return null;
  }
}
