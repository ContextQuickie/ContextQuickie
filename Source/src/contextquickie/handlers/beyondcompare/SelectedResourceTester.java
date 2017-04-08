package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;

/**
 * @author ContextQuickie
 *
 *         Class which checks if the currently selected item is of the expected
 *         type.
 * 
 */
public final class SelectedResourceTester extends PropertyTester
{

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
  {
    if ((receiver != null) && (expectedValue != null))
    {
      int expectedType = IResource.NONE;
      if (expectedValue.toString().toUpperCase().equals("IResource.FILE".toUpperCase()))
      {
        expectedType = IResource.FILE;
      }
      else if (expectedValue.toString().toUpperCase().equals("IResource.FOLDER".toUpperCase()))
      {
        expectedType = IResource.FOLDER;
      }
      else if (expectedValue.toString().toUpperCase().equals("IResource.PROJECT".toUpperCase()))
      {
        expectedType = IResource.PROJECT;
      }
      else
      {
        // Invalid or unknown expected value
        return false;
      }

      IAdapterManager adapterManager = Platform.getAdapterManager();
      if (adapterManager != null)
      {
        // Check if selected item is of expected type
        IResource resource = adapterManager.getAdapter(receiver, IResource.class);
        if ((resource != null) && ((resource.getType() & expectedType) != IResource.NONE))
        {
          return true;
        }
      }
    }
    return false;
  }
}
