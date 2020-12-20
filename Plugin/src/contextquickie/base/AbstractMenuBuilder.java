package contextquickie.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import contextquickie.Activator;
import contextquickie.tools.ContextMenuEnvironment;

/**
 * Base class for building context menu entries.
 * 
 * @author ContextQuickie
 */
public abstract class AbstractMenuBuilder extends CompoundContributionItem implements IWorkbenchContribution
{
  /**
   * The key for retrieving the configuration about the context menu active state.
   */
  private String componentActiveConfigKey;
  
  /**
   * The service locator used to create the
   * {@link CommandContributionItemParameter} for the menu entry.
   */
  private IServiceLocator currentServiceLocator;
  
  protected AbstractMenuBuilder(String componentActiveConfigKey) 
  {
    this.componentActiveConfigKey = componentActiveConfigKey;
  }
  
  @Override
  protected final IContributionItem[] getContributionItems()
  {
    List<IContributionItem> menuEntries = new ArrayList<IContributionItem>();
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    if (preferenceStore.getBoolean(this.componentActiveConfigKey))
    {
      menuEntries = this.createMenuItems(this.getMenuEntries(), new ContextMenuEnvironment());
    }
    
    if (menuEntries.isEmpty())
    {
      this.setVisible(false);
    }

    return menuEntries.toArray(new IContributionItem[menuEntries.size()]);
  }
  
  @Override
  public void initialize(final IServiceLocator serviceLocator)
  {
    this.currentServiceLocator = serviceLocator;
  }
  
  protected IServiceLocator getServiceLocator()
  {
    return this.currentServiceLocator;
  }
  
  protected abstract List<AbstractMenuEntry> getMenuEntries();
  
  private List<IContributionItem> createMenuItems(List<AbstractMenuEntry> entries, ContextMenuEnvironment environment)
  {
    List<IContributionItem> menuEntries = new ArrayList<IContributionItem>();
    
    for (AbstractMenuEntry entry : entries)
    {
      if (entry.isVisible(environment))
      {
        if (entry.getChildEntries().isEmpty())
        {
          if (entry.isSeparator())
          {
            menuEntries.add(new Separator());
          }
          else
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
        else
        {
          final MenuManager subMenu = new MenuManager(entry.getLabel(), entry.getImageDescriptor(), null);
          List<IContributionItem> childEntries = this.createMenuItems(entry.getChildEntries(), environment);
          for (IContributionItem contributionItem : childEntries)
          {
            subMenu.add(contributionItem);
          }
          
          menuEntries.add(subMenu);
        }
      }
    }
    
    return menuEntries;
  }
}
