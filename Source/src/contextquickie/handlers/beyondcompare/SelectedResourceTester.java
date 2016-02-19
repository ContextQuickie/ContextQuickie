package contextquickie.handlers.beyondcompare;

import java.util.List;

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
	 *            The expected resource type. Can be a combination of types.
	 * @return true if the selected resource type matches the expected type,
	 *         otherwise false.
	 */
	public static boolean test(Object receiver, String property, Object[] args, Object expectedValue,
			int expectedResourceType) {
		IAdapterManager adapterManager = Platform.getAdapterManager();
		if (adapterManager != null) {
			List<?> selection = (List<?>) receiver;
			if (selection != null) {
				for (Object selectedItem : selection) {
					IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
					if ((resource != null) && ((resource.getType() & expectedResourceType) != IResource.NONE)) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
