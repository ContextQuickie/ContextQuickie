package contextquickie.handlers.beyondcompare;

import org.eclipse.core.expressions.PropertyTester;

/**
 * @author ContextQuickie
 *
 *         Class which checks if the "Compare to right side" command is possible
 *         with the current selection.
 * 
 */
public class CompareToRightPossible extends PropertyTester
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
    if (expectedValue != null)
    {
      final BeyondCompare bc = new BeyondCompare();
      bc.readRegistry();
      if (bc.getSavedLeftType() == BeyondCompareSavedLeft.valueOf(expectedValue.toString()))
      {
        return true;
      }
    }

    return false;
  }
}
