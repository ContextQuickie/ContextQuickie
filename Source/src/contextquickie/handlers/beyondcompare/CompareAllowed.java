package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
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

		IFile leftFile = adapterManager.getAdapter(paths[0], IFile.class);
		IFile rightFile = adapterManager.getAdapter(paths[1], IFile.class);

		IFolder leftFolder = adapterManager.getAdapter(paths[0], IFolder.class);
		IFolder rightFolder = adapterManager.getAdapter(paths[1], IFolder.class);

		IResource leftProject = adapterManager.getAdapter(paths[0], IResource.class);
		IResource rightProject = adapterManager.getAdapter(paths[1], IResource.class);

		if ((leftFile != null) && (rightFile != null)) {
			return true;
		} else if ((leftFolder != null) && (rightFolder != null)) {
			return true;
		} else if ((leftFolder != null) && (rightProject != null)) {
			return true;
		} else if ((leftProject != null) && (rightFolder != null)) {
			return true;
		} else if ((leftProject != null) && (rightProject != null)) {
			return true;
		}

		return false;
	}

}
