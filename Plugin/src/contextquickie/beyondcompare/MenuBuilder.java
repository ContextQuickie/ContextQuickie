package contextquickie.beyondcompare;

import java.util.Arrays;
import java.util.List;

import contextquickie.base.AbstractMenuBuilder;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.beyondcompare.entries.Compare;
import contextquickie.beyondcompare.entries.CompareToLeftSide;
import contextquickie.beyondcompare.entries.SelectLeftSideForCompare;
import contextquickie.preferences.PreferenceConstants;

/**
 * Class for creating Beyond Compare menu entries.
 * 
 * @author ContextQuickie
 */
public class MenuBuilder extends AbstractMenuBuilder
{
  /**
   * Constructor.
   */
  public MenuBuilder()
  {
    super(PreferenceConstants.P_BEYOND_COMPARE_ENABLED);
  }
  
  @Override
  protected List<AbstractMenuEntry> getMenuEntries()
  {
    return Arrays.asList(new CompareToLeftSide(), new SelectLeftSideForCompare(), new Compare());
  }
}
