package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * @author ContextQuickie
 * 
 *         Class which checks if the current selection can be compared using
 *         Beyond Compare.
 *
 */
public class CompareAllowed extends PropertyTester
{

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
   * java.lang.String, java.lang.Object[], java.lang.Object)
   */
  @Override
  public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue)
  {
    boolean returnValue = false;
    final TreeSelection selection = (TreeSelection) receiver;
    final Object[] paths = selection.toArray();
    final IAdapterManager adapterManager = Platform.getAdapterManager();
    if (adapterManager != null)
    {
      int leftType = IResource.NONE;

      final IResource left = adapterManager.getAdapter(paths[0], IResource.class);
      if (left != null)
      {
        leftType = left.getType();
      }

      int rightType = IResource.NONE;
      final IResource right = adapterManager.getAdapter(paths[1], IResource.class);
      if (right != null)
      {
        rightType = right.getType();
      }

      if ((leftType == IResource.FILE) && (rightType == IResource.FILE))
      {
        returnValue = true;
      }
      else if ((leftType == IResource.FOLDER) && (rightType == IResource.FOLDER))
      {
        returnValue = true;
      }
      else if ((leftType == IResource.FOLDER) && (rightType == IResource.PROJECT))
      {
        returnValue = true;
      }
      else if ((leftType == IResource.PROJECT) && (rightType == IResource.FOLDER))
      {
        returnValue = true;
      }
      else if ((leftType == IResource.PROJECT) && (rightType == IResource.PROJECT))
      {
        returnValue = true;
      }
    }

    return returnValue;
  }

}
