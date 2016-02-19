package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;

/**
 * @author ContextQuickie
 *
 *         Class which checks if the "Compare to right side" command is possible
 *         with the current selection.
 * 
 */
public class CompareToRightPossible extends PropertyTester {
	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		BeyondCompare bc = new BeyondCompare();
		bc.readRegistry();

		if (bc.getSavedLeftType() == BeyondCompareSavedLeft.File) {
			return SelectedResourceTester.test(receiver, property, args, expectedValue, IResource.FILE);
		} else if (bc.getSavedLeftType() == BeyondCompareSavedLeft.Directory) {
			return SelectedResourceTester.test(receiver, property, args, expectedValue,
					IResource.FOLDER | IResource.PROJECT);
		} else {
			return false;
		}
	}
}
