package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

/**
 * @author ContextQuickie
 *
 *         Class which builds Tortoise menu entries based on configuration
 *         structures.
 */
public abstract class AbstractTortoiseMenuBuilder extends CompoundContributionItem implements IWorkbenchContribution
{
  /**
   * Map which stores the information whether the context menu settings has
   * already been read. The registry keys are read out once for each derived
   * class on the first usage and stored to avoid multiple registry accesses.
   */
  private static final Map<Class<?>, Boolean> CONTEXT_MENU_ENTRIES_READ = new HashMap<Class<?>, Boolean>();

  /**
   * Value of the registry key ContextMenuEntries.
   */
  private long contextMenuEntries;
  
  /**
   * Value of the registry key ContextMenuEntriesHigh.
   */
  private long contextMenuEntriesHigh;

  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferences;

  /**
   * The current menu settings.
   */
  private TortoiseMenuSettings entriesConfiguration;

  /**
   * The service locator used to create the
   * {@link CommandContributionItemParameter} for the menu entry.
   */
  private IServiceLocator currentServiceLocator;

  /**
   * Default constructor.
   * 
   * @param tortoisePreferences
   *          The preferences which will be used for execution.
   * @param settings
   *          The menu settings.
   */
  protected AbstractTortoiseMenuBuilder(final TortoisePreferenceConstants tortoisePreferences, final TortoiseMenuSettings settings)
  {
    this.preferences = tortoisePreferences;
    this.entriesConfiguration = settings;
  }

  @Override
  protected final IContributionItem[] getContributionItems()
  {
    final String iconFolder = "icons/";
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final ArrayList<IContributionItem> mainMenu = new ArrayList<IContributionItem>();

    if (preferenceStore.getBoolean(this.preferences.getEnabled()))
    {
      this.readSettingsFromRegistry();

      // Create main menu entry
      final ImageDescriptor subMenuIcon = contextquickie.Activator.getImageDescriptor(iconFolder + this.entriesConfiguration.getSubMenuIconPath());
      final MenuManager subMenu = new MenuManager(this.entriesConfiguration.getSubMenuText(), subMenuIcon, null);
      for (TortoiseMenuEntry entry : this.entriesConfiguration.getEntries())
      {
        if (entry.getMenuId() == 0)
        {
          subMenu.add(new Separator());
        }
        else
        {
          final CommandContributionItemParameter parameter = new CommandContributionItemParameter(this.currentServiceLocator, null,
              entry.getCommandId(), CommandContributionItem.STYLE_PUSH);
          final Map<String, Object> arguments = new HashMap<String, Object>();
          arguments.put(TortoiseMenuConstants.COMMAND_ID, entry.getCommand());
          arguments.put(TortoiseMenuConstants.REQUIRES_PATH_ID, entry.getEntryRequiresPath());
          parameter.parameters = arguments;
          if (this.isEntryInMainMenu(entry.getMenuId()))
          {
            parameter.label = this.entriesConfiguration.getMainMenuPrefix() + " " + entry.getLabel();
          }
          else
          {
            parameter.label = entry.getLabel();
          }

          parameter.icon = contextquickie.Activator.getImageDescriptor(iconFolder + entry.getIconPath());
          final CommandContributionItem commandContributionItem = new CommandContributionItem(parameter);
          if (this.isEntryInMainMenu(entry.getMenuId()))
          {
            mainMenu.add(commandContributionItem);
          }
          else
          {
            subMenu.add(commandContributionItem);
          }
        }
      }

      if (subMenu.isEmpty() == false)
      {
        mainMenu.add(subMenu);
      }
    }

    return mainMenu.toArray(new IContributionItem[mainMenu.size()]);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.menus.IWorkbenchContribution#initialize(org.eclipse.ui.
   * services.IServiceLocator)
   */
  @Override
  public void initialize(final IServiceLocator serviceLocator)
  {
    this.currentServiceLocator = serviceLocator;
  }

  /**
   * Reads the context menu settings from the registry.
   */
  private void readSettingsFromRegistry()
  {
    if ((CONTEXT_MENU_ENTRIES_READ.containsKey(this.getClass()) == false) || (CONTEXT_MENU_ENTRIES_READ.get(this.getClass()) == false))
    {
      CONTEXT_MENU_ENTRIES_READ.put(this.getClass(), true);
      final String registryLocation = this.entriesConfiguration.getRegistryUserPath();
      String registryValue;

      registryValue = Registry.readKey(registryLocation, "ContextMenuEntries");
      if (registryValue != null)
      {
        this.contextMenuEntries = Long.decode(registryValue);
      }

      registryValue = Registry.readKey(registryLocation, "ContextMenuEntrieshigh");
      if (registryValue != null)
      {
        this.contextMenuEntriesHigh = Long.decode(registryValue);
      }
    }
  }

  /**
   * Checks if the context menu entry is visible in the main menu.
   * 
   * @param entry
   *          The name of the entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  private boolean isEntryInMainMenu(final long entry)
  {
    final long int32BitMaxValue = 0xFFFFFFFFL;
    final long compareValue;
    final long entryValue;
    boolean result = false;

    if (entry > int32BitMaxValue)
    {
      entryValue = entry >> Integer.SIZE;
      compareValue = this.contextMenuEntriesHigh;
    }
    else
    {
      entryValue = entry;
      compareValue = this.contextMenuEntries;
    }
    if ((entryValue & compareValue) != 0)
    {
      result = true;
    }
    return result;
  }
}
