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
   * Indicates whether the context menu has already been reed or not.
   */
  private boolean registryReadPerformed;
  
  /**
   * Value of the registry key ContextMenuEntries.
   */
  private long registryContextMenuEntries;

  /**
   * Value of the registry key ContextMenuEntriesHigh.
   */
  private long registryContextMenuEntriesHigh;

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
        parameters.put(TortoiseMenuConstants.SUPPORTS_LINKED_RESOURCES_ID, entry.isSupportingLinkedResources().toString());
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
      // TODO: Uncomment when main menu is working: mainMenu.add(subMenu);
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
      if (this.registryReadPerformed == false)
      {
        this.registryReadPerformed = true;
        final String registryLocation = this.entriesConfiguration.getRegistryUserPath();

        Registry registry = new Registry();
        this.registryContextMenuEntries = registry.getIntValue(registryLocation, "ContextMenuEntries", this.entriesConfiguration.getContextMenuEntriesDefault());
        this.registryContextMenuEntriesHigh = registry.getIntValue(registryLocation, "ContextMenuEntrieshigh", this.entriesConfiguration.getContextMenuEntriesHighDefault());
      }
    }
    else
    {
      this.registryContextMenuEntries = this.entriesConfiguration.getContextMenuEntriesDefault();
      this.registryContextMenuEntriesHigh = this.entriesConfiguration.getContextMenuEntriesHighDefault();
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
      compareValue = this.registryContextMenuEntriesHigh;
    }
    else
    {
      entryValue = entry;
      compareValue = this.registryContextMenuEntries;
    }

    return (entryValue & compareValue) != 0;
  }
}
