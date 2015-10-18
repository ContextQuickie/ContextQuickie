package contextquickie.handlers.tortoisesvn;

import org.eclipse.core.expressions.PropertyTester;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;


public class TortoiseSVNEnabled extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_TORTOISE_SVN_ENABLED);
	}

}
