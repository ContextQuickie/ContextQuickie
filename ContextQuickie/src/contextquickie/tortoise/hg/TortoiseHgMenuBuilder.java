package contextquickie.tortoise.hg;

import java.util.ArrayList;
import java.util.List;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSeperator;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.hg.entries.*;

public class TortoiseHgMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * Tortoise Hg menu configuration.
   */
  private static final List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();
  
  /**
   * The path to the icons based on the used Tortoise Hg version.
   */
  private static final String iconPath = "TortoiseHg/5.5/";
  
  /**
   * The settings for the menu.
   */
  private static TortoiseMenuSettings settings = new TortoiseMenuSettings();

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


}
