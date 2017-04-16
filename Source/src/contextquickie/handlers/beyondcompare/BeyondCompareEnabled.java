package contextquickie.handlers.beyondcompare;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.expressions.PropertyTester;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Beyond Compare is enabled in the
 *         settings. It is used to show/hide the Beyond Compare context menu
 *         entries.
 */
public class BeyondCompareEnabled extends PropertyTester
{
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
   * java.lang.String, java.lang.Object[], java.lang.Object)
   */
  @Override
  public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue)
  {
    return Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_BEYOND_COMPARE_ENABLED);
  }
}
