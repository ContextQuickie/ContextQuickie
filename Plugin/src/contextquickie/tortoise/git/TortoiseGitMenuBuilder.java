package contextquickie.tortoise.git;

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
import contextquickie.tortoise.git.entries.*;

/**
 * Menu configuration for TortoiseGit.
 */
public class TortoiseGitMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 135;

  protected List<TortoiseMenuEntry> getEntries()
  {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersion = preferenceStore.getString(PreferenceConstants.TORTOISE_GIT.getUsedVersion());
    final String alternativeExtension;
    String iconPath;
    if (usedVersion.equals("2.6"))
    {
      iconPath = "TortoiseGit/2.6/";

      // If using icons from version 2.6, all files have .ico extension
      alternativeExtension = ".ico";
    }
    else
    {
      iconPath = "TortoiseGit/2.7/";

      // If using icons from version 2.7, some files require .png extension in eclipse
      alternativeExtension = ".png";
    }
    
    final List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();
    entries.add(new Clone(iconPath));
    entries.add(new Pull(iconPath));
    entries.add(new Fetch(iconPath));
    entries.add(new Push(iconPath));
    entries.add(new Sync(iconPath));

    entries.add(new TortoiseMenuSeparator());
    
    entries.add(new Commit(iconPath));
    entries.add(new GitSvnDCommit(iconPath));
    entries.add(new SvnRebase(iconPath));
    entries.add(new SvnFetch(iconPath));
    entries.add(new ImportSvnIgnore(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new Diff(iconPath));
    entries.add(new DiffTwoFiles(iconPath));
    entries.add(new DiffLater(iconPath));
    entries.add(new DiffWith(iconPath));
    entries.add(new DiffWithPreviousVersion(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new DiffTwoCommits(iconPath));
    entries.add(new ShowLog(iconPath));
    entries.add(new ShowLogOfThisFolder(iconPath));
    entries.add(new ShowRefLog(iconPath));
    entries.add(new BrowseReferences(iconPath));
    entries.add(new Daemon(iconPath));
    entries.add(new RevisionGraph(iconPath));
    entries.add(new RepoBrowser(iconPath));
    entries.add(new CheckForModifications(iconPath));
    entries.add(new Rebase(iconPath));
    entries.add(new StashSave(iconPath));
    entries.add(new StashApply(iconPath));
    entries.add(new StashPop(iconPath));
    entries.add(new StashList(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new BisectStart(iconPath, alternativeExtension));
    entries.add(new BisectGood(iconPath, alternativeExtension));
    entries.add(new BisectBad(iconPath, alternativeExtension));
    entries.add(new BisectSkip(iconPath, alternativeExtension));
    entries.add(new BisectReset(iconPath, alternativeExtension));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new EditConflicts(iconPath));
    entries.add(new Resolve(iconPath));
    entries.add(new AbortMerge(iconPath));
    entries.add(new Rename(iconPath));
    entries.add(new Delete(iconPath, alternativeExtension));
    entries.add(new DeleteKeepLocal(iconPath, alternativeExtension));
    entries.add(new Revert(iconPath, alternativeExtension));
    entries.add(new Cleanup(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new SwitchCheckout(iconPath));
    entries.add(new Merge(iconPath));
    entries.add(new CreateBranch(iconPath));
    entries.add(new CreateTag(iconPath));
    entries.add(new Export(iconPath));
    entries.add(new TortoiseMenuSeparator());
    entries.add(new CreateRepositoryHere(iconPath));
    entries.add(new Add(iconPath));
    entries.add(new Blame(iconPath));
    entries.add(new AddToIgnoreList(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new SubmoduleAdd(iconPath));
    entries.add(new SubmoduleUpdate(iconPath));
    entries.add(new SubmoduleSync(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new CreatePatchSerial(iconPath));
    entries.add(new ApplyPatchSerial(iconPath));
    entries.add(new ReviewApplySinglePatch(iconPath));
    entries.add(new SendMail(iconPath));
    entries.add(new Paste(iconPath));

    entries.add(new TortoiseMenuSeparator());

    entries.add(new Settings(iconPath));
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
  public TortoiseGitMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_GIT, settings);
    Translation translation = new Translation(PreferenceConstants.TORTOISE_GIT);
    settings.setSubMenuIconPath(this.getIconPath() + "tsvnmenufolder.ico");
    settings.setSubMenuText(translation.getTranslatedString(MenuTextIdentifier, "TortoiseGit"));
    settings.setMainMenuPrefix("Git");
    settings.setContextMenuEntriesDefault(
        CreateRepositoryHere.MenuIdentifier | Sync.MenuIdentifier | Commit.MenuIdentifier);
  }
  
  private String getIconPath()
  {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersion = preferenceStore.getString(PreferenceConstants.TORTOISE_GIT.getUsedVersion());
    String iconPath;
    if (usedVersion.equals("2.6"))
    {
      iconPath = "TortoiseGit/2.6/";
    }
    else
    {
      iconPath = "TortoiseGit/2.7/";
    }
    
    return iconPath;
  }
}
