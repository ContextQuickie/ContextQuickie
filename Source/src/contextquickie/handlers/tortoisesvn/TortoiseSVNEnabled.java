package contextquickie.handlers.tortoisesvn;

import org.eclipse.core.expressions.PropertyTester;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise SVN is enabled in the
 *         settings. It is used to show/hide the Tortoise SVN context menu
 *         entries.
 */
public class TortoiseSVNEnabled extends PropertyTester {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_TORTOISE_SVN_ENABLED);
	}

}
