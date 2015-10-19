package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeSelection;

public class CompareAllowed extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		TreeSelection selection = (TreeSelection) receiver;
		Object[] paths = selection.toArray();

		if ((paths[0] instanceof IAdaptable) && (paths[1] instanceof IAdaptable)) {
			IAdaptable leftItem = (IAdaptable) paths[0];
			IAdaptable rightItem = (IAdaptable) paths[1];

			IFile leftFile = leftItem.getAdapter(IFile.class);
			IFile rightFile = rightItem.getAdapter(IFile.class);

			IFolder leftFolder = leftItem.getAdapter(IFolder.class);
			IFolder rightFolder = rightItem.getAdapter(IFolder.class);

			IResource leftProject = leftItem.getAdapter(IProject.class);
			IResource rightProject = rightItem.getAdapter(IProject.class);

			if ((leftFile != null) && (rightFile != null)) {
				return true;
			}
			else if ((leftFolder != null) && (rightFolder != null)) {
				return true;
			} else if ((leftFolder != null) && (rightProject != null)) {
				return true;
			} else if ((leftProject != null) && (rightFolder != null)) {
				return true;
			} else if ((leftProject != null) && (rightProject != null)) {
				return true;
			}
		}
		
		return false;
	}

}
