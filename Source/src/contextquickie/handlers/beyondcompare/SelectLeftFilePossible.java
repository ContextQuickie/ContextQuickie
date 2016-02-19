package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;

/**
 * @author ContextQuickie
 *
 *         Class which checks if the "Select Left Side" command for files is
 *         possible with the current selection.
 */
public class SelectLeftFilePossible extends PropertyTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return SelectedResourceTester.test(receiver, property, args, expectedValue, IResource.FILE);
	}
}
