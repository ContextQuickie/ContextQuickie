package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.handlers.tortoise.TortoiseMenuEntry;
import contextquickie.handlers.tortoise.TortoiseMenuSettings;
import contextquickie.preferences.PreferenceConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ContextQuickie
 *
 *         Menu configuration for Tortoise Git.
 */
public class TortoiseGitMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * Sync menu entry.
   */
  private static final long MENUSYNC = 0x0000000000000002;

  /**
   * Commit menu entry.
   */
  private static final long MENUCOMMIT = 0x0000000000000004;

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
   * Show log menu entry.
   */
  private static final long MENULOG = 0x0000000000020000;

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
   * SVN Rebase menu entry.
   */
  private static final long MENUSVNREBASE = 0x0000000008000000;

  /**
   * SVN DCommit menu entry.
   */
  private static final long MENUSVNDCOMMIT = 0x0000000010000000;

  /**
   * Import SVN Ignore menu entry.
   */
  private static final long MENUSVNIGNORE = 0x0000000040000000;

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
   * Pull menu entry.
   */
  private static final long MENUPULL = 0x0000000800000000L;

  /**
   * Push menu entry.
   */
  private static final long MENUPUSH = 0x0000001000000000L;

  /**
   * Clone menu entry.
   */
  private static final long MENUCLONE = 0x0000002000000000L;

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
   * Fetch menu entry.
   */
  private static final long MENUFETCH = 0x0000040000000000L;

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
   * Tortoise SVN menu configuration.
   */
  private static List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();
  
  static
  {
    final String defaultCommandId = "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand";
    
    // Path to the "Compare" icon.
    final String menuCompareIconPath = "Tortoise/menucompare.png";

    // Path to the "Merge" icon.
    final String menuMergeIconPath = "Tortoise/menumerge.png";

    // Path to the "Delete" icon
    final String menuDeleteIconPath = "Tortoise/menudelete.png";

    entries.add(new TortoiseMenuEntry()
        .setLabel("Clone...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLONE)
        .setIconPath("Tortoise/menucheckout.png")
        .setCommand("clone")
        .setIsVisibleInWorkingCopy(false)
        .setVisibleWithoutWorkingCopy(true)
        .setMaxFolderCount(1)
        .setMaxFileCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Pull...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPULL)
        .setIconPath("TortoiseGit/pull1.png")
        .setCommand("pull"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Fetch...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUFETCH)
        .setIconPath("TortoiseGit/pull1.png")
        .setCommand("fetch"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Push...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPUSH)
        .setIconPath("TortoiseGit/Push.png")
        .setCommand("push"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Sync...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSYNC)
        .setIconPath("TortoiseGit/menurelocate.png")
        .setCommand("sync"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Commit...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOMMIT)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("commit"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Git SVN DCommit...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSVNDCOMMIT)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("commit")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("SVN Rebase")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSVNREBASE)
        .setIconPath("TortoiseGit/menurebase.png")
        .setCommand("commit")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("SVN Fetch")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSVNFETCH)
        .setIconPath("Tortoise/pull1.png")
        .setCommand("commit")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("Import SVN Ignore")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSVNIGNORE)
        .setIconPath("TortoiseSvn/menuignore.png")
        .setCommand("commit")
        .setMaxItemsCount(0)); // TODO: Disabled

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxFileCount(1)
        .setMaxFolderCount(0));

    // Diff for two files
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff")
        .setCommandId("ContextQuickie.commands.TortoiseGit.TortoiseGitDiffTwoFilesCommand")
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(2)
        .setMinItemsCount(2)
        .setMaxFolderCount(0)
        .setRequiresParameters(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff later")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFLATER)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with previous version")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPREVDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setParameter1("HEAD^"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff two commits")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFTWO)
        .setIconPath(menuCompareIconPath)
        .setCommand("diffcommits")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("Show log")
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOG)
        .setIconPath("Tortoise/menulog.png")
        .setCommand("log"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Show log of this folder")
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOGSUBMODULE)
        .setIconPath("Tortoise/menulog.png")
        .setCommand("log")
        .setParameter1("/submodule"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Show Reflog")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREFLOG)
        .setIconPath("Tortoise/menulog.png")
        .setCommand("reflog"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Browse References")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREFBROWSE)
        .setIconPath("Tortoise/menurepobrowse.png")
        .setCommand("refbrowse"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Daemon")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDAEMON)
        .setIconPath("TortoiseGit/menudaemon.png")
        .setCommand("daemon"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Revision graph")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVISIONGRAPH)
        .setIconPath("Tortoise/menurevisiongraph.png")
        .setCommand("revisiongraph"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Repo-browser")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREPOBROWSE)
        .setIconPath("Tortoise/menurepobrowse.png")
        .setCommand("repobrowser"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Check for modifications")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSHOWCHANGED)
        .setIconPath("Tortoise/menushowchanged.png")
        .setCommand("repostatus"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Rebase")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREBASE)
        .setIconPath("TortoiseGit/menurebase.png")
        .setCommand("rebase"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Stash Save")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSTASHSAVE)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("stashsave"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Stash Apply")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSTASHAPPLY)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("stashapply"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Stash Pop")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSTASHPOP)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("stashpop"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Stash List")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSTASHLIST)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("reflog")
        .setParameter1("/ref:refs/stash"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Bisect start")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBISECT)
        .setIconPath("TortoiseGit/menubisect.png")
        .setCommand("bisect")
        .setParameter1("/start"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Bisect good")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBISECT)
        .setIconPath("TortoiseGit/menubisect.png")
        .setCommand("bisect")
        .setParameter1("/good"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Bisect bad")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBISECT)
        .setIconPath("TortoiseGit/menubisect.png")
        .setCommand("bisect")
        .setParameter1("/bad"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Bisect skip")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBISECT)
        .setIconPath("TortoiseGit/menubisect.png")
        .setCommand("bisect")
        .setParameter1("/skip"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Bisect reset")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBISECT)
        .setIconPath("TortoiseGit/menubisect.png")
        .setCommand("bisect")
        .setParameter1("/reset"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Edit conflicts")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCONFLICTEDITOR)
        .setIconPath("Tortoise/menuresolve.png")
        .setCommand("conflicteditor")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Resolve...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENURESOLVE)
        .setIconPath("Tortoise/menuresolve.png")
        .setCommand("resolve"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Abort Merge")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge")
        .setParameter1("/abort"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Rename...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENURENAME)
        .setIconPath("Tortoise/menurename.png")
        .setCommand("rename")
        .setMaxFileCount(1)
        .setMaxFolderCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete (keep local)")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREMOVEKEEP)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove")
        .setParameter1("/keep"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Revert...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVERT)
        .setIconPath("Tortoise/menurevert.png")
        .setCommand("revert"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Clean up...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLEANUP)
        .setIconPath("Tortoise/menucleanup.png")
        .setCommand("cleanup"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Switch/Checkout...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSWITCH)
        .setIconPath("Tortoise/menuswitch.png")
        .setCommand("switch"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Merge...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Create Branch...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOPY)
        .setIconPath("Tortoise/menucopy.png")
        .setCommand("branch"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Create Tag...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUTAG)
        .setIconPath("TortoiseGit/tag.png")
        .setCommand("tag"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Export...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUEXPORT)
        .setIconPath("Tortoise/menuexport.png")
        .setCommand("export"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Create repository here")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCREATEREPOS)
        .setIconPath("Tortoise/menucreaterepos.png")
        .setCommand("repocreate")
        .setVisibleWithoutWorkingCopy(true)
        .setIsVisibleInWorkingCopy(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Add...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUADD)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("add"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Blame...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBLAME)
        .setIconPath("Tortoise/menublame.png")
        .setCommand("blame")
        .setMaxFileCount(1)
        .setMaxFolderCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Add to ignore list")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIGNORE)
        .setIconPath("TortoiseGit/menuignore.png")
        .setCommand(""));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Submodule Add...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSUBADD)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("subadd"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Submodule Update...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPDATEEXT)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("subupdate"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Submodule Sync")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSUBSYNC)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("subsync"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Create Patch Serial...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUFORMATPATCH)
        .setIconPath("Tortoise/menudiff.png")
        .setCommand("formatpatch"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Apply Patch Serial...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIMPORTPATCH)
        .setIconPath("Tortoise/menupatch.png")
        .setCommand(""));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Review/apply single patch..")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUAPPLYPATCH)
        .setIconPath("Tortoise/menupatch.png")
        .setCommand(""));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Send Mail...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSENDMAIL)
        .setIconPath("Tortoise/menusendmail.png")
        .setCommand("settings")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel("Paste")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLIPPASTE)
        .setIconPath("Tortoise/menusendmail.png")
        .setCommand("settings")
        .setMaxItemsCount(0)); // TODO: Disabled, also not supported by Tortoise Git

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel("Settings")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSETTINGS)
        .setIconPath("Tortoise/menusettings.png")
        .setCommand("settings")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Help")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUHELP)
        .setIconPath("Tortoise/menuhelp.png")
        .setCommand("help")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));

    entries.add(new TortoiseMenuEntry()
        .setLabel("About")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUABOUT)
        .setIconPath("Tortoise/menuabout.png")
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
    settings.setSubMenuIconPath("TortoiseGit/tsvnmenufolder.png");
    settings.setSubMenuText("TortioseGit");
    settings.setMainMenuPrefix("Git");
    settings.setWorkingCopyFolderName(".git");
    settings.setRegistryUserPath("HKEY_CURRENT_USER\\Software\\TortoiseGit");
    settings.setContextMenuEntriesDefault(MENUCREATEREPOS | MENUSYNC | MENUCOMMIT);
  }
}
