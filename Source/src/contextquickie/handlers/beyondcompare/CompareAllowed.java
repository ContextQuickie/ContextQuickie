package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.TreeSelection;

public class CompareAllowed extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		TreeSelection selection = (TreeSelection)receiver;
		Object[] paths = selection.toArray();
		if ((paths[0] instanceof IFile) && (paths[1] instanceof IFile)) {
			return true;
		}
		else if ((paths[0] instanceof IFolder) && (paths[1] instanceof IProject)) {
			return true;
		}
		else if ((paths[0] instanceof IProject) && (paths[1] instanceof IFolder)) {
			return true;
		}
		else if ((paths[0] instanceof IProject) && (paths[1] instanceof IProject)) {
			return true;
		}
		else if ((paths[0] instanceof IFolder) && (paths[1] instanceof IFolder)) {
			return true;
		}

		return false;
	}

}
