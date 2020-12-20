package contextquickie.tortoise;

import contextquickie.Activator;
import contextquickie.base.AbstractMenuBuilder;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.base.MenuSeparator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import rolandomagico.jniregistry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
  protected List<AbstractMenuEntry> getMenuEntries()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final List<AbstractMenuEntry> mainMenu = new ArrayList<AbstractMenuEntry>();

    // Trigger reading the registry settings every time to detect if using the
    // registry settings has been disabled.
    this.readSettingsFromRegistry();
    final TortoiseEnvironment currentEnvironment = this.getCurrentMenuEnvironment();
    final boolean workingCopyDetection = preferenceStore.getBoolean(this.preferences.getWorkingCopyDetection());

    final TortoiseSubMenu subMenu = new TortoiseSubMenu(
        this.entriesConfiguration.getSubMenuText(),
        "icons/" + this.entriesConfiguration.getSubMenuIconPath());

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
      
      if ((currentEnvironment.getSelectedResources().size() < entry.getMinItemsCount()) ||
          (currentEnvironment.getSelectedFilesCount() < entry.getMinFileCount()) ||
          (currentEnvironment.getSelectedFoldersCount() < entry.getMinFolderCount()))
      {
        entryVisible = false;
      }
      
      if (entry.isVisible(currentEnvironment) == false)
      {
        entryVisible = false;
      }

      if (entry.getMenuId() == 0)
      {
        if (subMenu.getChildEntries().isEmpty() == false)
        {
          subMenu.addChildEntry(new MenuSeparator());
        }
      }
      else if (entryVisible == true)
      {
        final CommandContributionItemParameter commandParameter = new CommandContributionItemParameter(
            this.getServiceLocator(), 
            null,
            entry.getCommandId(),
            CommandContributionItem.STYLE_PUSH);

        // Create map of parameters for the command
        if (entry.usesDefaultParameters())
        {
          final Map<String, Object> parameters = new HashMap<String, Object>();
          parameters.put(TortoiseMenuConstants.COMMAND_ID, entry.getCommand());
          parameters.put(TortoiseMenuConstants.REQUIRES_PATH_ID, entry.getEntryRequiresPath().toString());
          parameters.put(TortoiseMenuConstants.SUPPORTS_LINKED_RESOURCES_ID, entry.isSupportingLinkedResources().toString());
          if (entry.getParameter1() != null)
          {
            parameters.put(TortoiseMenuConstants.PARAMETER_1_ID, entry.getParameter1());
          }
          commandParameter.parameters = parameters;
        }
        else
        {
          commandParameter.parameters = entry.getCustomParameters();
        }

        if (this.isEntryInMainMenu(entry))
        {
          commandParameter.label = this.entriesConfiguration.getMainMenuPrefix() + " " + entry.getLabel();
        }
        else
        {
          commandParameter.label = entry.getLabel();
        }

        commandParameter.icon = entry.getImageDescriptor();

        if (this.isEntryInMainMenu(entry))
        {
          mainMenu.add(entry);
        }
        else
        {
          subMenu.addChildEntry(entry);
        }
      }
    }

    if (subMenu.getChildEntries().isEmpty() == false)
    {
      mainMenu.add(subMenu);
    }

    return mainMenu;
  }
  
  /**
   * Reads the context menu settings from the registry.
   */
  protected long readSettingsFromRegistry(String registryKey, long defaultValue)
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    if (preferenceStore.getBoolean(this.preferences.getUseMenuConfigFromRegistry()) == true)
    {
      final String registryLocation = this.preferences.getRegistryUserDirectory();

      Registry registry = new Registry();
      return registry.readIntValue(registryLocation, registryKey, defaultValue);
    }

    return defaultValue;
  }

  /**
   * Checks if the context menu entry is visible in the main menu.
   * 
   * @param entry
   *          The name of the entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  protected boolean isEntryBitSet(final long entry, final long lowRegistryEntry, long highRegistryEntry)
  {
    final long int32BitMaxValue = 0xFFFFFFFFL;
    final long compareValue;
    final long entryValue;

    if (entry > int32BitMaxValue)
    {
      entryValue = entry >> Integer.SIZE;
      compareValue = highRegistryEntry;
    }
    else
    {
      entryValue = entry;
      compareValue = lowRegistryEntry;
    }

    return (entryValue & compareValue) != 0;
  }
  
  protected static boolean diffTwoFilesActive(final TortoiseMenuEntry entry, final TortoiseEnvironment environment)
  {
    if ((environment.getSelectedFilesCount() == 2) && (environment.getSelectedFoldersCount() == 0))
    {
      Iterator<IResource> iterator = environment.getSelectedResources().iterator();
      Map<String, Object> parameters = new HashMap<String, Object>();
      parameters.put(AbstractTortoiseDiffTwoFilesCommand.LeftSideParameterName, iterator.next().getLocation().toOSString());
      parameters.put(AbstractTortoiseDiffTwoFilesCommand.RightSideParameterName, iterator.next().getLocation().toOSString());
      entry.setCustomParameters(parameters);
      return true;
    }
    
    return false;
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
    TortoiseWorkingCopyDetect workingCopyDetect = new TortoiseWorkingCopyDetect();
    if (workingCopyDetect.test(result.getSelectedResources(), this.preferences.getWorkingCopyFolderName()))
    {
      result.setWorkingCopyFound(true);
      result.setWorkingCopyRoot(workingCopyDetect.getWorkingCopyRoot());
    }

    result.setSelectedFilesCount(result.getSelectedResources().stream().filter(r -> r.getType() == IResource.FILE).count());
    result.setSelectedFoldersCount(result.getSelectedResources().stream().filter(r -> (r.getType() == IResource.FOLDER) || (r.getType() == IResource.PROJECT)).count());

    return result;
  }

  /**
   * Reads the context menu settings from the registry.
   */
  protected void readSettingsFromRegistry()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    if (preferenceStore.getBoolean(this.preferences.getUseMenuConfigFromRegistry()) == true)
    {
      if (this.registryReadPerformed == false)
      {
        this.registryReadPerformed = true;
        this.registryContextMenuEntries = this.readSettingsFromRegistry("ContextMenuEntries", this.entriesConfiguration.getContextMenuEntriesDefault());
        this.registryContextMenuEntriesHigh = this.readSettingsFromRegistry("ContextMenuEntrieshigh", this.entriesConfiguration.getContextMenuEntriesHighDefault());
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
   *          The entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  protected boolean isEntryInMainMenu(final TortoiseMenuEntry entry)
  {
    return this.isEntryBitSet(entry.getMenuId(), this.registryContextMenuEntries, this.registryContextMenuEntriesHigh);
  }
}
