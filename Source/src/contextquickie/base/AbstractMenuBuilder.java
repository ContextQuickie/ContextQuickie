package contextquickie.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import contextquickie.Activator;

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
      menuEntries = this.getMenuEntries();
    }
    
    if (menuEntries.isEmpty())
    {
      this.setVisible(false);
    }

    return menuEntries.stream().toArray(size -> new IContributionItem[size]);
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
  
  protected abstract List<IContributionItem> getMenuEntries();
}
