package contextquickie.tortoise;

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

import contextquickie.Activator;
import contextquickie.base.AbstractMenuBuilder;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.base.MenuSeparator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import rolandomagico.jniregistry.Registry;

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
    final List<AbstractMenuEntry> mainMenu = new ArrayList<AbstractMenuEntry>();

    // Trigger reading the registry settings every time to detect if using the
    // registry settings has been disabled.
    this.readSettingsFromRegistry();

    final TortoiseSubMenu subMenu = new TortoiseSubMenu(
        this.entriesConfiguration.getSubMenuText(),
        "icons/" + this.entriesConfiguration.getSubMenuIconPath());

    for (AbstractMenuEntry entry : this.entriesConfiguration.getEntries())
    {
      boolean entryVisible = true;
       
      if (MenuSeparator.class.isInstance(entry))
      {
        if (subMenu.getChildEntries().isEmpty() == false)
        {
          subMenu.addChildEntry(new MenuSeparator());
        }
      }
      else if (TortoiseMenuEntry.class.isInstance(entry))
      {
        TortoiseMenuEntry tortoiseMenuEntry = TortoiseMenuEntry.class.cast(entry);
        if (this.isEntryInMainMenu(tortoiseMenuEntry))
        {
          // TODO: commandParameter.label = this.entriesConfiguration.getMainMenuPrefix() + " " + entry.getLabel();
        }

        if (this.isEntryInMainMenu(tortoiseMenuEntry))
        {
          mainMenu.add(tortoiseMenuEntry);
        }
        else
        {
          subMenu.addChildEntry(tortoiseMenuEntry);
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
