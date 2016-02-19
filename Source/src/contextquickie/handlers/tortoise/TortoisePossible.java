package contextquickie.handlers.tortoise;

import java.util.List;

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
public class TortoisePossible extends PropertyTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		IAdapterManager adapterManager = Platform.getAdapterManager();
		if (adapterManager != null) {
			List<?> selection = (List<?>) receiver;
			if (selection != null) {
				for (Object selectedItem : selection) {
					IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
					if (resource != null) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
