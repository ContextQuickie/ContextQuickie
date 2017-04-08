package contextquickie.handlers.tortoise;

import java.util.Collection;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import contextquickie.tools.WorkbenchUtil;

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
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
  {
    IAdapterManager adapterManager = Platform.getAdapterManager();
    if (adapterManager != null)
    {
      if (receiver instanceof Collection<?>)
      {
        Collection<?> selection = (Collection<?>) receiver;
        if (selection != null)
        {
          for (Object selectedItem : selection)
          {
            IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
            if (resource != null)
            {
              return true;
            }

            if (WorkbenchUtil.getCurrentDocument() != null)
            {
              return true;
            }
          }
        }
      }
      else if (receiver instanceof Collection<?>)
      {
        return true;
      }
    }

    return false;
  }
}
