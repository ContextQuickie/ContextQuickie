package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

public class CompareToRightVisible extends PropertyTester  {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		BeyondCompareSavedLeft savedLeft = BaseBeyoncCompare.getSavedLeftType();
		if (((receiver instanceof IProject) || (receiver instanceof IFolder)) && (savedLeft == BeyondCompareSavedLeft.Directory)) {
			return true;
		}
		else if ((receiver instanceof IFile) && (savedLeft == BeyondCompareSavedLeft.File)) {
			return true;
		}
		else {
			return false;
		}
	}

}
