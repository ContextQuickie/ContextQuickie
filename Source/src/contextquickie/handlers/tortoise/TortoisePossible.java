package contextquickie.handlers.tortoise;

import contextquickie.tools.WorkbenchUtil;
import java.util.Collection;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;

/**
 * @author ContextQuickie
 *
 *         Class which checks if a Tortoise context menu is possible with the
 *         current selection.
 */
public class TortoisePossible extends PropertyTester
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

    if (receiver instanceof Collection<?>)
    {
      final Collection<?> selection = (Collection<?>) receiver;
      if (selection != null)
      {
        final IAdapterManager adapterManager = Platform.getAdapterManager();
        if (adapterManager != null)
        {
          for (Object selectedItem : selection)
          {
            final IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
            if (resource != null)
            {
              returnValue = true;
              break;
            }
          }
        }
      }
    }
    else if (WorkbenchUtil.getCurrentDocument() != null)
    {
      returnValue = true;
    }

    return returnValue;
  }
}
