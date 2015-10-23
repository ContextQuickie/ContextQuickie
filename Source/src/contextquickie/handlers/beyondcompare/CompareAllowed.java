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
public class CompareAllowed extends PropertyTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		TreeSelection selection = (TreeSelection) receiver;
		Object[] paths = selection.toArray();
		IAdapterManager adapterManager = Platform.getAdapterManager();
		if (adapterManager != null) {
			int leftType = adapterManager.getAdapter(paths[0], IResource.class).getType();
			int rightType = adapterManager.getAdapter(paths[1], IResource.class).getType();

			if ((leftType == IResource.FILE) && (rightType == IResource.FILE)) {
				return true;
			} else if ((leftType == IResource.FOLDER) && (rightType == IResource.FOLDER)) {
				return true;
			} else if ((leftType == IResource.FOLDER) && (rightType == IResource.PROJECT)) {
				return true;
			} else if ((leftType == IResource.PROJECT) && (rightType == IResource.FOLDER)) {
				return true;
			} else if ((leftType == IResource.PROJECT) && (rightType == IResource.PROJECT)) {
				return true;
			}
		}
		return false;
	}

}
