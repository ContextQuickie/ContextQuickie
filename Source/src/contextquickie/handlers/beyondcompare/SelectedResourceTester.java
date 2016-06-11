package contextquickie.handlers.beyondcompare;

import java.util.Collection;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import contextquickie.tools.WorkbenchUtil;

/**
 * @author ContextQuickie
 *
 *         Class which checks if the currently selected item is of the expected
 *         type.
 * 
 */
public final class SelectedResourceTester {
  /**
   * checks if the currently selected item is of the expected type.
   * 
   * @param receiver
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang. Object,
   *      java.lang.String, java.lang.Object[], java.lang.Object)
   * @param property
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang. Object,
   *      java.lang.String, java.lang.Object[], java.lang.Object)
   * @param args
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang. Object,
   *      java.lang.String, java.lang.Object[], java.lang.Object)
   * @param expectedValue
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang. Object,
   *      java.lang.String, java.lang.Object[], java.lang.Object)
   * @param expectedResourceType
   *          The expected resource type. Can be a combination of types.
   * @return true if the selected resource type matches the expected type,
   *         otherwise false.
   */
  public static boolean test(Object receiver, String property, Object[] args, Object expectedValue,
      int expectedResourceType) {
    IAdapterManager adapterManager = Platform.getAdapterManager();
    if (adapterManager != null) {
      Collection<?> selection = (Collection<?>) receiver;
      if (selection != null) {
        for (Object selectedItem : selection) {
          // Check if selection is part of a tree selection
          IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
          if ((resource != null) && ((resource.getType() & expectedResourceType) != IResource.NONE)) {
            return true;
          }

          // Check if selection is part of a text selection in an opened document
          if (selectedItem instanceof TextSelection) {
            resource = WorkbenchUtil.getCurrentDocument();
            if ((resource != null) && ((resource.getType() & expectedResourceType) != IResource.NONE)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
