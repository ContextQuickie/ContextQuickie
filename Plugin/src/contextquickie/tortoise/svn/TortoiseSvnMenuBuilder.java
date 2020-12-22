package contextquickie.tortoise.svn;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSeparator;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.Translation;
import contextquickie.tortoise.Version;
import contextquickie.tortoise.svn.entries.*;

/**
 * Menu configuration for TortoiseSVN.
 */
public class TortoiseSvnMenuBuilder extends AbstractTortoiseMenuBuilder
{
  private static final Version version1_10 = new Version(1, 10);
  private static final Version version1_11 = new Version(1, 11);
  
  /**
   * The menu identifier for this class.
   */
  public static final int MenuTextIdentifier = 135;

  protected List<TortoiseMenuEntry> getEntries()
  {
    List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();
    new Translation(PreferenceConstants.TORTOISE_SVN);
    
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersionString = preferenceStore.getString(PreferenceConstants.TORTOISE_SVN.getUsedVersion());
    
    final Version usedVersion = new Version(usedVersionString);

    final String iconPath = this.getIconPath();
    final String alternativeExtension;
    if (usedVersion.compareTo(version1_11) > 0)
    {
      // If using icons from version 1.11, some files require .png extension in eclipse
      alternativeExtension = ".png";
    }
    else
    {
      // If using icons from version 1.10 or before, all files have .ico extension
      alternativeExtension = ".ico";
    }
    
    entries.add(new Checkout(iconPath));
    entries.add(new UpgradeWorkingCopy(iconPath));
    entries.add(new Update(iconPath));
    entries.add(new Commit(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new Diff(iconPath));
    entries.add(new DiffTwoFiles(iconPath));
    entries.add(new DiffLater(iconPath));
    entries.add(new DiffWith(iconPath));
    entries.add(new DiffWithPreviousVersion(iconPath));
    entries.add(new DiffWithUrl(iconPath));
    entries.add(new UnifiedDiff(iconPath));
    entries.add(new ShowLog(iconPath));
    entries.add(new RepoBrowser(iconPath));
    entries.add(new CheckForModifications(iconPath));
    entries.add(new RevisionGraph(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new EditConflicts(iconPath));
    entries.add(new Resolve(iconPath));
    entries.add(new UpdateToRevision(iconPath));
    entries.add(new Rename(iconPath));
    entries.add(new Delete(iconPath, alternativeExtension));
    entries.add(new DeleteKeepLocal(iconPath, alternativeExtension));
    entries.add(new Revert(iconPath, alternativeExtension));
    entries.add(new DeleteUnversionedItems(iconPath, alternativeExtension));
    entries.add(new Cleanup(iconPath));
    entries.add(new GetLock(iconPath));
    entries.add(new ReleaseLock(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new BranchTag(iconPath));
    entries.add(new Switch(iconPath));
    entries.add(new Merge(iconPath));
    entries.add(new MergeAll(iconPath));
    entries.add(new Export(iconPath));
    entries.add(new Relocate(iconPath));

    entries.add(new TortoiseMenuSeparator());
    
    entries.add(new CreateRepositoryHere(iconPath));
    entries.add(new Add(iconPath));
    entries.add(new Import(iconPath));
    entries.add(new Blame(iconPath));
    entries.add(new CopyUrlToClipboard(iconPath));
    entries.add(new AddToIgnoreList(iconPath));
    
    entries.add(new TortoiseMenuSeparator());

    if (usedVersion.compareTo(version1_10) >= 0)
    {
      entries.add(new Shelve(iconPath));
      entries.add(new Unshelve(iconPath));
    }

    entries.add(new CreatePatch(iconPath));
    entries.add(new ApplyPatch(iconPath));
    entries.add(new Properties(iconPath));
    entries.add(new Paste(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new Settings(iconPath, alternativeExtension));
    entries.add(new Help(iconPath, alternativeExtension));
    entries.add(new About(iconPath));
    
    return entries;
  }

  /**
   * The settings for the menu.
   */
  private static TortoiseMenuSettings settings = new TortoiseMenuSettings();

  /**
   * Default constructor.
   */
  public TortoiseSvnMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_SVN, settings);
    Translation translation = new Translation(PreferenceConstants.TORTOISE_SVN);
    settings.setSubMenuIconPath(this.getIconPath() + "tsvnmenumultiple.ico");
    settings.setSubMenuText(translation.getTranslatedString(MenuTextIdentifier, "TortioseSVN"));
    settings.setMainMenuPrefix("SVN");
    settings.setContextMenuEntriesDefault(Checkout.MenuIdentifier | Update.MenuIdentifier | Commit.MenuIdentifier);
  }
  
  private String getIconPath()
  {
    final String iconPath;
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersionString = preferenceStore.getString(PreferenceConstants.TORTOISE_SVN.getUsedVersion());
    
    final Version usedVersion = new Version(usedVersionString);

    if (usedVersion.compareTo(version1_11) > 0)
    {
      iconPath = "TortoiseSvn/1.11/";
    }
    else
    {
      iconPath = "TortoiseSvn/1.10/";
    }
    
    return iconPath;
  }
}
