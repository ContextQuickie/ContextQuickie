package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.base.AbstractMenuBuilder;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

/**
 * @author ContextQuickie
 *
 *         Class which builds Tortoise menu entries based on configuration
 *         structures.
 */
public abstract class AbstractTortoiseMenuBuilder extends AbstractMenuBuilder 
{
  /**
   * Map which stores the information whether the context menu settings has
   * already been read. The registry keys are read out once for each derived
   * class on the first usage and stored to avoid multiple registry accesses.
   */
  private static final Map<Class<?>, ContextMenuRegistry> CONTEXT_MENU_ENTRIES_READ = new HashMap<Class<?>, ContextMenuRegistry>();

  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferences;

  /**
   * The current menu settings.
   */
  private TortoiseMenuSettings entriesConfiguration;



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
    super(tortoisePreferences.getEnabled());
    this.preferences = tortoisePreferences;
    this.entriesConfiguration = settings;
  }

  @Override
  protected List<IContributionItem> getMenuEntries()
  {
    final String iconFolder = "icons/";
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final List<IContributionItem> mainMenu = new ArrayList<IContributionItem>();

    // Trigger reading the registry settings every time to detect if using the
    // registry settings has been disabled.
    this.readSettingsFromRegistry();
    final TortoiseEnvironment currentEnvironment = this.getCurrentMenuEnvironment();
    final boolean workingCopyDetection = preferenceStore.getBoolean(this.preferences.getWorkingCopyDetection());

    // Create sub menu entry
    final ImageDescriptor subMenuIcon = contextquickie.Activator.getImageDescriptor(iconFolder + this.entriesConfiguration.getSubMenuIconPath());
    final MenuManager subMenu = new MenuManager(this.entriesConfiguration.getSubMenuText(), subMenuIcon, null);

    for (TortoiseMenuEntry entry : this.entriesConfiguration.getEntries())
    {
      boolean entryVisible = true;

      if (workingCopyDetection == true)
      {
        if ((entry.isVisibleInWorkingCopy() == true) && 
            (entry.isVisibleWithoutWorkingCopy() == false) &&
            (currentEnvironment.isWorkingCopyFound() == false))
        {
          entryVisible = false;
        }
        if ((entry.isVisibleInWorkingCopy() == false) && 
            (entry.isVisibleWithoutWorkingCopy() == true) &&
            (currentEnvironment.isWorkingCopyFound() == true))
        {
          entryVisible = false;
        }
      }
      
      if ((currentEnvironment.getSelectedResources().size() > entry.getMaxItemsCount()) ||
          (currentEnvironment.getSelectedFilesCount() > entry.getMaxFileCount()) ||
          (currentEnvironment.getSelectedFoldersCount() > entry.getMaxFolderCount()))
      {
        entryVisible = false;
      }

      if (entry.getMenuId() == 0)
      {
        subMenu.add(new Separator());
      }
      else if (entryVisible == true)
      {
        final CommandContributionItemParameter commandParameter = new CommandContributionItemParameter(
            this.getServiceLocator(), 
            null,
            entry.getCommandId(),
            CommandContributionItem.STYLE_PUSH);

        // Create map of parameters for the command
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(TortoiseMenuConstants.COMMAND_ID, entry.getCommand());
        parameters.put(TortoiseMenuConstants.REQUIRES_PATH_ID, entry.getEntryRequiresPath().toString());
        commandParameter.parameters = parameters;

        if (this.isEntryInMainMenu(entry.getMenuId()))
        {
          commandParameter.label = this.entriesConfiguration.getMainMenuPrefix() + " " + entry.getLabel();
        }
        else
        {
          commandParameter.label = entry.getLabel();
        }

        commandParameter.icon = contextquickie.Activator.getImageDescriptor(iconFolder + entry.getIconPath());
        final CommandContributionItem commandContributionItem = new CommandContributionItem(commandParameter);
        
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

    return mainMenu;
  }

  /**
   * Checks in which environment the current menu will be used.
   * 
   * @return The current menu environment.
   */
  private TortoiseEnvironment getCurrentMenuEnvironment()
  {
    final TortoiseEnvironment result = new TortoiseEnvironment();
    result.setSelectedResources(new ContextMenuEnvironment().getSelectedResources());
    if (new TortoiseWorkingCopyDetect().test(result.getSelectedResources(), this.entriesConfiguration.getWorkingCopyFolderName()))
    {
      result.setWorkingCopyFound(true);
    }

    result.setSelectedFilesCount(result.getSelectedResources().stream().filter(r -> r.getType() == IResource.FILE).count());
    result.setSelectedFoldersCount(result.getSelectedResources().stream().filter(r -> r.getType() == IResource.FOLDER).count());

    return result;
  }

  /**
   * Reads the context menu settings from the registry.
   */
  private void readSettingsFromRegistry()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    if (preferenceStore.getBoolean(this.preferences.getUseMenuConfigFromRegistry()) == true)
    {
      // Check if the registry settings for this class type has already been read. Avoid multiple readouts to increase performance.
      if ((CONTEXT_MENU_ENTRIES_READ.containsKey(this.getClass()) == false) || (CONTEXT_MENU_ENTRIES_READ.get(this.getClass()).ReadPerformed == false))
      {
        ContextMenuRegistry registrySettings = new ContextMenuRegistry();
        registrySettings.ReadPerformed = true;
        CONTEXT_MENU_ENTRIES_READ.put(this.getClass(), registrySettings);
        final String registryLocation = this.entriesConfiguration.getRegistryUserPath();

        registrySettings.ContextMenuEntries = new Registry().readIntValue(registryLocation, "ContextMenuEntries", registrySettings.ContextMenuEntries);
        registrySettings.ContextMenuEntriesHigh = new Registry().readIntValue(registryLocation, "ContextMenuEntrieshigh", registrySettings.ContextMenuEntriesHigh);
      }
    }
    else
    {
      ContextMenuRegistry registrySettings = new ContextMenuRegistry();
      registrySettings.ContextMenuEntries = this.entriesConfiguration.getContextMenuEntriesDefault();
      registrySettings.ContextMenuEntriesHigh = this.entriesConfiguration.getContextMenuEntriesHighDefault();
      CONTEXT_MENU_ENTRIES_READ.put(this.getClass(), registrySettings);
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

    if (entry > int32BitMaxValue)
    {
      entryValue = entry >> Integer.SIZE;
      compareValue = CONTEXT_MENU_ENTRIES_READ.get(this.getClass()).ContextMenuEntriesHigh;
    }
    else
    {
      entryValue = entry;
      compareValue = CONTEXT_MENU_ENTRIES_READ.get(this.getClass()).ContextMenuEntries;
    }

    return (entryValue & compareValue) != 0;
  }
}
