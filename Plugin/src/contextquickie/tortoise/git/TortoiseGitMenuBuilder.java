package contextquickie.tortoise.git;

import contextquickie.Activator;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.base.MenuSeparator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseDiffTwoFilesCommand;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.Translation;
import contextquickie.tortoise.git.entries.*;
import rolandomagico.jniregistry.Registry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author ContextQuickie
 *
 *         Menu configuration for TortoiseGit.
 */
public class TortoiseGitMenuBuilder extends AbstractTortoiseMenuBuilder implements BiPredicate<TortoiseMenuEntry, TortoiseEnvironment>
{
  private static final String DIFF_TOW_FILES_COMMAND_ID = "ContextQuickie.commands.TortoiseGit.TortoiseGitDiffTwoFilesCommand";

  /**
   * Add menu entry.
   */
  private static final long MENUADD = 0x0000000000000008;

  /**
   * Revert menu entry.
   */
  private static final long MENUREVERT = 0x0000000000000010;

  /**
   * Cleanup menu entry.
   */
  private static final long MENUCLEANUP = 0x0000000000000020;

  /**
   * Resolve menu entry.
   */
  private static final long MENURESOLVE = 0x0000000000000040;

  /**
   * Switch menu entry.
   */
  private static final long MENUSWITCH = 0x0000000000000080;

  /**
   * Send mail menu entry.
   */
  private static final long MENUSENDMAIL = 0x0000000000000100;

  /**
   * Export menu entry.
   */
  private static final long MENUEXPORT = 0x0000000000000200;

  /**
   * Create repository menu entry.
   */
  private static final long MENUCREATEREPOS = 0x0000000000000400;

  /**
   * Create branch menu entry.
   */
  private static final long MENUCOPY = 0x0000000000000800;

  /**
   * Merge menu entry.
   */
  private static final long MENUMERGE = 0x0000000000001000;

  /**
   * Remove menu entry.
   */
  private static final long MENUREMOVE = 0x0000000000002000;

  /**
   * Rename menu entry.
   */
  private static final long MENURENAME = 0x0000000000004000;

  /**
   * Submodule Update menu entry.
   */
  private static final long MENUUPDATEEXT = 0x0000000000008000;

  /**
   * Diff menu entry.
   */
  private static final long MENUDIFF = 0x0000000000010000;

  /**
   * Edit conflicts menu entry.
   */
  private static final long MENUCONFLICTEDITOR = 0x0000000000040000;

  /**
   * Browse References menu entry.
   */
  private static final long MENUREFBROWSE = 0x0000000000080000;

  /**
   * Check for modifications menu entry.
   */
  private static final long MENUSHOWCHANGED = 0x0000000000100000;

  /**
   * Ignore menu entry.
   */
  private static final long MENUIGNORE = 0x0000000000200000;

  /**
   * Show Reflog menu entry.
   */
  private static final long MENUREFLOG = 0x0000000000400000;

  /**
   * Blame menu entry.
   */
  private static final long MENUBLAME = 0x0000000000800000;

  /**
   * Repo browser menu entry.
   */
  private static final long MENUREPOBROWSE = 0x0000000001000000;

  /**
   * Apply patch menu entry.
   */
  private static final long MENUAPPLYPATCH = 0x0000000002000000;

  /**
   * Delete (keep local) menu entry.
   */
  private static final long MENUREMOVEKEEP = 0x0000000004000000;

  /**
   * "Show log of this folder" menu entry.
   */
  private static final long MENULOGSUBMODULE = 0x0000000100000000L;

  /**
   * Diff with previous version menu entry.
   */
  private static final long MENUPREVDIFF = 0x0000000200000000L;

  /**
   * "Paste" menu entry.
   */
  private static final long MENUCLIPPASTE = 0x0000000400000000L;

  /**
   * Create tag menu entry.
   */
  private static final long MENUTAG = 0x0000004000000000L;

  /**
   * "Create Patch Serial..." menu entry.
   */
  private static final long MENUFORMATPATCH = 0x0000008000000000L;

  /**
   * "Apply Patch Serial..." menu entry.
   */
  private static final long MENUIMPORTPATCH = 0x0000010000000000L;

  /**
   * "Diff later" menu entry.
   */
  private static final long MENUDIFFLATER = 0x0000020000000000L;

  /**
   * Rebase menu entry.
   */
  private static final long MENUREBASE = 0x0000080000000000L;

  /**
   * Stash Save menu entry.
   */
  private static final long MENUSTASHSAVE = 0x0000100000000000L;

  /**
   * Stash Apply menu entry.
   */
  private static final long MENUSTASHAPPLY = 0x0000200000000000L;

  /**
   * Stash List menu entry.
   */
  private static final long MENUSTASHLIST = 0x0000400000000000L;

  /**
   * Submodule add menu entry.
   */
  private static final long MENUSUBADD = 0x0000800000000000L;

  /**
   * Submodule Sync menu entry.
   */
  private static final long MENUSUBSYNC = 0x0001000000000000L;

  /**
   * Stash Pop menu entry.
   */
  private static final long MENUSTASHPOP = 0x0002000000000000L;

  /**
   * "Diff Two Commits" menu entry.
   */
  private static final long MENUDIFFTWO = 0x0004000000000000L;

  /**
   * Bisect start menu entry.
   */
  private static final long MENUBISECT = 0x0008000000000000L;

  /**
   * SVN Fetch menu entry.
   */
  private static final long MENUSVNFETCH = 0x0080000000000000L;

  /**
   * Revision Graph menu entry.
   */
  private static final long MENUREVISIONGRAPH = 0x0100000000000000L;

  /**
   * Daemon menu entry.
   */
  private static final long MENUDAEMON = 0x0200000000000000L;

  /**
   * Settings menu entry.
   */
  private static final long MENUSETTINGS = 0x2000000000000000L;

  /**
   * Help menu entry.
   */
  private static final long MENUHELP = 0x4000000000000000L;

  /**
   * About menu entry.
   */
  private static final long MENUABOUT = 0x8000000000000000L;
  
  /**
   * The value of ContextMenuExtEntriesLow in the registry settings of TortoiseGit.
   */
  private long ContextMenuExtEntriesLow;
  
  /**
   * The value of ContextMenuExtEntriesHigh in the registry settings of TortoiseGit.
   */
  private long ContextMenuExtEntriesHigh;
  
  /**
   * TortoiseGit menu configuration.
   */
  private static final List<AbstractMenuEntry> entries = new ArrayList<AbstractMenuEntry>();

  /**
   * The instance using for translating the menu entries.
   */
  private static final Translation translation;
  
  /**
   * The path to the icons based on the used TortoiseGit version.
   */
  private static final String iconPath;
  
  static
  {
    final String defaultCommandId = "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand";
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    translation = new Translation(PreferenceConstants.TORTOISE_GIT);
    
    String usedVersion = preferenceStore.getString(PreferenceConstants.TORTOISE_GIT.getUsedVersion());
    final String alternativeExtension;
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
    
    // Path to the "Compare" icon.
    final String menuCompareIconPath = iconPath + "menucompare.ico";

    // Path to the "Merge" icon.
    final String menuMergeIconPath = iconPath + "menumerge.ico";

    // Path to the "Delete" icon
    final String menuDeleteIconPath = iconPath + "menudelete" + alternativeExtension;
    
    entries.add(new Clone(iconPath));
    entries.add(new Pull(iconPath));
    entries.add(new Fetch(iconPath));
    entries.add(new Push(iconPath));
    entries.add(new Sync(iconPath));
    entries.add(new MenuSeparator());
    entries.add(new Commit(iconPath));

    entries.add(new GitSvnDCommit(iconPath));
    entries.add(new SvnRebase(iconPath));
    entries.add(new SvnFetch(iconPath));
    entries.add(new ImportSvnIgnore(iconPath));

    entries.add(new MenuSeparator());

    entries.add(new Diff(iconPath));
    entries.add(new DiffTwoFiles(iconPath));
    entries.add(new DiffLater(iconPath));
    entries.add(new DiffWith(iconPath));
    entries.add(new DiffWithPreviousVersion(iconPath));
    
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFFTWO, "Diff two commits"))
        .setMenuId(MENUDIFFTWO)
        .setIconPath(menuCompareIconPath)
        .setCommand("diffcommits")
        .setMaxItemsCount(0)); // Disabled. Didn't find any hint in TortoiseGit source how to handle this entry.

    entries.add(new ShowLog(iconPath));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENULOGSUBMODULE, "Show log of this folder"))
        .setMenuId(MENULOGSUBMODULE)
        .setIconPath(iconPath + "menulog.ico")
        .setCommand("log")
        .setParameter1("/submodule"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREFLOG, "Show Reflog"))
        .setMenuId(MENUREFLOG)
        .setIconPath(iconPath + "menulog.ico")
        .setCommand("reflog")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREFBROWSE, "Browse References"))
        .setMenuId(MENUREFBROWSE)
        .setIconPath(iconPath + "menurepobrowse.ico")
        .setCommand("refbrowse")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDAEMON, "Daemon"))
        .setMenuId(MENUDAEMON)
        .setIconPath(iconPath + "menudaemon.ico")
        .setCommand("daemon")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVISIONGRAPH, "Revision graph"))
        .setMenuId(MENUREVISIONGRAPH)
        .setIconPath(iconPath + "menurevisiongraph.ico")
        .setCommand("revisiongraph")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREPOBROWSE, "Repo-browser"))
        .setMenuId(MENUREPOBROWSE)
        .setIconPath(iconPath + "menurepobrowse.ico")
        .setCommand("repobrowser")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSHOWCHANGED, "Check for modifications"))
        .setMenuId(MENUSHOWCHANGED)
        .setIconPath(iconPath + "menushowchanged.ico")
        .setCommand("repostatus"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREBASE, "Rebase"))
        .setMenuId(MENUREBASE)
        .setIconPath(iconPath + "menurebase.ico")
        .setCommand("rebase")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSTASHSAVE, "Stash Save"))
        .setMenuId(MENUSTASHSAVE)
        .setIconPath(iconPath + "menucommit.ico")
        .setCommand("stashsave")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSTASHAPPLY, "Stash Apply"))
        .setMenuId(MENUSTASHAPPLY)
        .setIconPath(iconPath + "menurelocate.ico")
        .setCommand("stashapply")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSTASHPOP, "Stash Pop"))
        .setMenuId(MENUSTASHPOP)
        .setIconPath(iconPath + "menurelocate.ico")
        .setCommand("stashpop")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSTASHLIST, "Stash List"))
        .setMenuId(MENUSTASHLIST)
        .setIconPath(iconPath + "menulog.ico")
        .setCommand("reflog")
        .setParameter1("/ref:refs/stash")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new MenuSeparator());
    entries.add(new BisectStart(iconPath, alternativeExtension));
    entries.add(new BisectGood(iconPath, alternativeExtension));
    entries.add(new BisectBad(iconPath, alternativeExtension));
    entries.add(new BisectSkip(iconPath, alternativeExtension));
    entries.add(new BisectReset(iconPath, alternativeExtension));
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCONFLICT, "Edit conflicts"))
        .setMenuId(MENUCONFLICTEDITOR)
        .setIconPath(iconPath + "menuresolve.ico")
        .setCommand("conflicteditor")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURESOLVE, "Resolve..."))
        .setMenuId(MENURESOLVE)
        .setIconPath(iconPath + "menuresolve.ico")
        .setCommand("resolve"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGE, "Abort Merge"))
        .setMenuId(MENUMERGE)
        .setIconPath(iconPath + "menumergeabort.ico")
        .setCommand("merge")
        .setParameter1("/abort"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURENAME, "Rename..."))
        .setMenuId(MENURENAME)
        .setIconPath(iconPath + "menurename.ico")
        .setCommand("rename")
        .setMaxFolderCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVE, "Delete"))
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVEKEEP, "Delete (keep local)"))
        .setMenuId(MENUREMOVEKEEP)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove")
        .setParameter1("/keep"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVERT, "Revert..."))
        .setMenuId(MENUREVERT)
        .setIconPath(iconPath + "menurevert" + alternativeExtension)
        .setCommand("revert"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLEANUP, "Clean up..."))
        .setMenuId(MENUCLEANUP)
        .setIconPath(iconPath + "menucleanup.ico")
        .setCommand("cleanup")
        .setMaxFileCount(0));

    // Separator
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSWITCH, "Switch/Checkout..."))
        .setMenuId(MENUSWITCH)
        .setIconPath(iconPath + "menuswitch.ico")
        .setCommand("switch")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGE, "Merge..."))
        .setMenuId(MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBRANCH, "Create Branch..."))
        .setMenuId(MENUCOPY)
        .setIconPath(iconPath + "menucopy.ico")
        .setCommand("branch")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUTAG, "Create Tag..."))
        .setMenuId(MENUTAG)
        .setIconPath(iconPath + "tag.ico")
        .setCommand("tag")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUEXPORT, "Export..."))
        .setMenuId(MENUEXPORT)
        .setIconPath(iconPath + "menuexport.ico")
        .setCommand("export")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    // Separator
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCREATEREPOS, "Create repository here"))
        .setMenuId(MENUCREATEREPOS)
        .setIconPath(iconPath + "menucreaterepos.ico")
        .setCommand("repocreate")
        .setVisibleWithoutWorkingCopy(true)
        .setIsVisibleInWorkingCopy(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUADD, "Add..."))
        .setMenuId(MENUADD)
        .setIconPath(iconPath + "menuadd.ico")
        .setCommand("add"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBLAME, "Blame..."))
        .setMenuId(MENUBLAME)
        .setIconPath(iconPath + "menublame.ico")
        .setCommand("blame")
        .setMaxFileCount(1)
        .setMaxFolderCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIGNORE, "Add to ignore list"))
        .setMenuId(MENUIGNORE)
        .setIconPath(iconPath + "menuignore.ico")
        .setCommand(""));

    // Separator
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSUBADD, "Submodule Add..."))
        .setMenuId(MENUSUBADD)
        .setIconPath(iconPath + "menuadd.ico")
        .setCommand("subadd")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPDATEEXT, "Submodule Update..."))
        .setMenuId(MENUUPDATEEXT)
        .setIconPath(iconPath + "pull1.ico")
        .setCommand("subupdate"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSUBSYNC, "Submodule Sync"))
        .setMenuId(MENUSUBSYNC)
        .setIconPath(iconPath + "menusync.ico")
        .setCommand("subsync"));

    // Separator
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUFORMATPATCH, "Create Patch Serial..."))
        .setMenuId(MENUFORMATPATCH)
        .setIconPath(iconPath + "menudiff.ico")
        .setCommand("formatpatch")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIMPORTPATCH, "Apply Patch Serial..."))
        .setMenuId(MENUIMPORTPATCH)
        .setIconPath(iconPath + "menupatch.ico")
        .setCommand(""));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUAPPLYPATCH, "Review/apply single patch.."))
        .setMenuId(MENUAPPLYPATCH)
        .setIconPath(iconPath + "menupatch.ico")
        .setCommand(""));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSENDMAIL, "Send Mail..."))
        .setMenuId(MENUSENDMAIL)
        .setIconPath(iconPath + "menusendmail.ico")
        .setCommand("settings")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLIPPASTE, "Paste"))
        .setMenuId(MENUCLIPPASTE)
        .setIconPath(iconPath + "menusendmail.ico")
        .setCommand("settings")
        .setMaxItemsCount(0)); // TODO: Disabled, also not supported by TortoiseGit

    // Separator
    entries.add(new MenuSeparator());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSETTINGS, "Settings"))
        .setMenuId(MENUSETTINGS)
        .setIconPath(iconPath + "menusettings.ico")
        .setCommand("settings")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUHELP, "Help"))
        .setMenuId(MENUHELP)
        .setIconPath(iconPath + "menuhelp" + alternativeExtension)
        .setCommand("help")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUABOUT, "About"))
        .setMenuId(MENUABOUT)
        .setIconPath(iconPath + "menuabout.ico")
        .setCommand("about")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));
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
    settings.setEntries(entries);
    settings.setSubMenuIconPath(iconPath + "tsvnmenufolder.ico");
    settings.setSubMenuText(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSUBMENU, "TortoiseGit"));
    settings.setMainMenuPrefix("Git");
    settings.setContextMenuEntriesDefault(MENUCREATEREPOS | Sync.MenuIdentifier | Commit.MenuIdentifier);
    
    this.ContextMenuExtEntriesLow = this.readSettingsFromRegistry("ContextMenuExtEntriesLow", MENUSUBSYNC | MENUSTASHAPPLY);
    this.ContextMenuExtEntriesHigh = this.readSettingsFromRegistry("ContextMenuExtEntriesHigh", ImportSvnIgnore.MenuIdentifier);
    entries.forEach(entry -> entry.addVisibilityChecker(this));
    
    entries.stream().filter(e -> ((e.getMenuId() == MENUBISECT) && (e.getParameter1() == "/start"))).forEach(e -> e.addVisibilityChecker(
        (entry, environment) -> this.bisectActive(environment) == false));
    entries.stream().filter(e -> ((e.getMenuId() == MENUBISECT) && (e.getParameter1() != "/start"))).forEach(e -> e.addVisibilityChecker(
        (entry, environment) -> this.bisectActive(environment) == true));
  }

  private boolean bisectActive(final TortoiseEnvironment environment)
  {
    final String workingCopyRoot = environment.getWorkingCopyRoot();
    if (workingCopyRoot != null)
    {
      File gitDirectory = new File(workingCopyRoot);
      if (gitDirectory.exists() && gitDirectory.isDirectory())
      {
        File bisectStartFile = new File(gitDirectory, "BISECT_START");
        if (bisectStartFile.exists() && bisectStartFile.isFile())
        {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public boolean test(TortoiseMenuEntry entry, TortoiseEnvironment environment)
  {
    if (this.isEntryBitSet(entry.getMenuId(), this.ContextMenuExtEntriesLow, this.ContextMenuExtEntriesHigh))
    {
      return false;
    }
    
    return true;
  }
}
