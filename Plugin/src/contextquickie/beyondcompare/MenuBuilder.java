package contextquickie.beyondcompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

import contextquickie.base.AbstractMenuBuilder;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.beyondcompare.entries.Compare;
import contextquickie.beyondcompare.entries.CompareToLeftSide;
import contextquickie.beyondcompare.entries.SelectLeftSideForCompare;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;

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
  protected List<IContributionItem> getMenuEntries()
  {
    List<IContributionItem> menuEntries = new ArrayList<IContributionItem>();
    
    List<AbstractMenuEntry> entries = new ArrayList<AbstractMenuEntry>();
    entries.add(new CompareToLeftSide());
    entries.add(new SelectLeftSideForCompare());
    entries.add(new Compare());
    
    ContextMenuEnvironment environment = new ContextMenuEnvironment();
    
    for (AbstractMenuEntry entry: entries)
    {
      if (entry.isVisible(environment))
      {
        // Create map of parameters for the command
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(AbstractMenuEntry.ParameterName, entry);
        
        final CommandContributionItemParameter commandParameter = new CommandContributionItemParameter(
            this.getServiceLocator(), 
            null,
            "ContextQuickie.Command", 
            CommandContributionItem.STYLE_PUSH);
        commandParameter.label = entry.getLabel();
        commandParameter.icon = entry.getImageDescriptor();
        commandParameter.parameters = parameters;
        menuEntries.add(new CommandContributionItem(commandParameter));
      }
    }
    
    return menuEntries;
  }
}
