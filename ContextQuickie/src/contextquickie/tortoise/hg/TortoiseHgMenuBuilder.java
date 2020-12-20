package contextquickie.tortoise.hg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSeperator;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.hg.entries.*;
import rolandomagico.jniregistry.Registry;

public class TortoiseHgMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * TortoiseHg menu configuration.
   */
  private static final List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();
  
  /**
   * The path to the icons based on the used TortoiseHg version.
   */
  private static final String iconPath = "TortoiseHg/5.5/";
  
  /**
   * The settings for the menu.
   */
  private static TortoiseMenuSettings settings = new TortoiseMenuSettings();
  
  /**
   * The promoted menu items (shown in the main menu). 
   */
  private Set<String> promotedItems;

  static
  {
    entries.add(new Commit(iconPath));
    entries.add(new ViewFileStatus(iconPath));
    entries.add(new ShelveChanges(iconPath));
    entries.add(new VisualDiff(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new AddFiles(iconPath));
    entries.add(new RevertFiles(iconPath));
    entries.add(new RenameFile(iconPath));
    entries.add(new ForgetFiles(iconPath));
    entries.add(new RemoveFiles(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new RevisionHistory(iconPath));
    entries.add(new AnnotateFiles(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new Workbench(iconPath));
    entries.add(new Update(iconPath));
    entries.add(new SearchHistory(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new Synchronize(iconPath));
    entries.add(new WebServer(iconPath));
    entries.add(new Clone(iconPath));
    entries.add(new CreateRepositoryHere(iconPath));
    // Not supported: entries.add(new UpdateIcons(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new EditIgnoreFilter(iconPath));
    entries.add(new GuessRenames(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new ExplorerExtensionSettings(iconPath));
    entries.add(new RepositorySettings(iconPath));
    entries.add(new GlobalSettings(iconPath));
    entries.add(new TortoiseMenuSeperator());
    
    entries.add(new AboutTortioseHg(iconPath));
  }

  public TortoiseHgMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_HG, settings);
    settings.setEntries(entries);
    settings.setMainMenuPrefix("Hg");
    settings.setSubMenuText("TortoiseHg");
    settings.setSubMenuIconPath(iconPath + "hg.png");
  }

  /**
   * Reads the context menu settings from the registry.
   */
  @Override
  protected void readSettingsFromRegistry()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final String defaultPromotedItems = "commit,workbench";
    String promotedItemsString = null; 
    if (preferenceStore.getBoolean(PreferenceConstants.TORTOISE_HG.getUseMenuConfigFromRegistry()) == true)
    {
      if (this.promotedItems == null)
      {
        final String registryLocation = PreferenceConstants.TORTOISE_HG.getRegistryUserDirectory();
        Registry registry = new Registry();
        promotedItemsString = registry.readStringValue(registryLocation, "PromotedItems", defaultPromotedItems);
      }
    }
    else
    {
      promotedItemsString = defaultPromotedItems;
    }
    
    if (promotedItemsString != null)
    {
      this.promotedItems = new HashSet<String>();
      for (String promotedItem : promotedItemsString.split(","))
      {
        this.promotedItems.add(promotedItem);  
      }
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
  @Override
  protected boolean isEntryInMainMenu(final TortoiseMenuEntry entry)
  {
    return this.promotedItems.contains(entry.getCommand());
  }
}
