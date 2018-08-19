package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.handlers.tortoise.TortoiseMenuEntry;
import contextquickie.handlers.tortoise.TortoiseMenuSettings;
import contextquickie.preferences.PreferenceConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ContextQuickie
 *
 *         Menu configuration for Tortoise SVN.
 */
public class TortoiseSvnMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * Checkout menu entry.
   */
  private static final long MENUCHECKOUT = 0x0000000000000001;

  /**
   * Update menu entry.
   */
  private static final long MENUUPDATE = 0x0000000000000002;

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
   * Import menu entry.
   */
  private static final long MENUIMPORT = 0x0000000000000100;

  /**
   * Export menu entry.
   */
  private static final long MENUEXPORT = 0x0000000000000200;

  /**
   * Create repository menu entry.
   */
  private static final long MENUCREATEREPOS = 0x0000000000000400;

  /**
   * Branch/tag menu entry.
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
   * Update to revision menu entry.
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
   * Relocate menu entry.
   */
  private static final long MENURELOCATE = 0x0000000000080000;

  /**
   * Check for modifications menu entry.
   */
  private static final long MENUSHOWCHANGED = 0x0000000000100000;

  /**
   * Ignore menu entry.
   */
  private static final long MENUIGNORE = 0x0000000000200000;

  /**
   * Repository browser menu entry.
   */
  private static final long MENUREPOBROWSE = 0x0000000000400000;

  /**
   * Blame menu entry.
   */
  private static final long MENUBLAME = 0x0000000000800000;

  /**
   * Create Patch menu entry.
   */
  private static final long MENUCREATEPATCH = 0x0000000001000000;

  /**
   * Apply Patch menu entry.
   */
  private static final long MENUAPPLYPATCH = 0x0000000002000000;

  /**
   * Revision graph menu entry.
   */
  private static final long MENUREVISIONGRAPH = 0x0000000004000000;

  /**
   * Get Lock menu entry.
   */
  private static final long MENULOCK = 0x0000000008000000;

  /**
   * Release Lock menu entry.
   */
  private static final long MENUUNLOCK = 0x0000000010000000;

  /**
   * Properties menu entry.
   */
  private static final long MENUPROPERTIES = 0x0000000020000000;

  /**
   * Diff with URL menu entry.
   */
  private static final long MENUURLDIFF = 0x0000000040000000;

  /**
   * Delete unversioned items menu entry.
   */
  private static final long MENUDELUNVERSIONED = 0x0000000080000000;

  /**
   * Merge all entry.
   */
  private static final long MENUMERGEALL = 0x0000000100000000L;

  /**
   * Diff with previous version menu entry.
   */
  private static final long MENUPREVDIFF = 0x0000000200000000L;

  /**
   * Paste menu entry.
   */
  private static final long MENUCLIPPASTE = 0x0000000400000000L;

  /**
   * Upgrade working copy menu entry.
   * TODO: currently not supported.
   */
  //private static final long MENUUPGRADE = 0x0000000800000000L;

  /**
   * Diff later menu entry.
   */
  private static final long MENUDIFFLATER = 0x0000001000000000L;

  /**
   * Diff menu entry.
   */
  private static final long MENUDIFFNOW = 0x0000002000000000L;

  /**
   * Unified diff menu entry.
   * TODO: currently not supported.
   */
  //private static final long MENUUNIDIFF = 0x0000004000000000L;

  /**
   * Copy URL to clipboard menu entry.
   */
  private static final long MENUCOPYURL = 0x0000008000000000L;

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
    final String defaultCommandId = "ContextQuickie.commands.TortoiseSvn.TortoiseSvnCommand";
    
    // Path to the "Update" icon
    final String menuUpdateIconPath = "TortoiseSvn/menuupdate.png";
   
    // Path to the "Compare" icon.
    final String menuCompareIconPath = "Tortoise/menucompare.png";

    // Path to the "Merge" icon.
    final String menuMergeIconPath = "Tortoise/menumerge.png";

    // Path to the "Delete" icon
    final String menuDeleteIconPath = "TortoiseSvn/menudelete.png";
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Chekout...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCHECKOUT)
        .setIconPath("Tortoise/menucheckout.png")
        .setCommand("checkout")
        .setIsVisibleInWorkingCopy(false)
        .setVisibleWithoutWorkingCopy(true)
        .setMaxFolderCount(1)
        .setMaxFileCount(0)
        .setSupportingLinkedResources(false));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Update")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPDATE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Commit")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOMMIT)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("commit"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    /* TODO: Continue here 
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff later")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFLATER)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with \"%ls\"")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFNOW)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with previous version")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPREVDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("prevdiff"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with URL")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUURLDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Show log")
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOG)
        .setIconPath("Tortoise/menulog.png")
        .setCommand("log"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Repo-browser")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREPOBROWSE)
        .setIconPath("Tortoise/menurepobrowse.png")
        .setCommand("repobrowser")
        .setEntryRequiresPath(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Check for modifications")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSHOWCHANGED)
        .setIconPath("Tortoise/menushowchanged.png")
        .setCommand("repostatus"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Revision graph")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVISIONGRAPH)
        .setIconPath("Tortoise/menurevisiongraph.png")
        .setCommand("revisiongraph"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Edit conflicts")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCONFLICTEDITOR)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Resolve...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENURESOLVE)
        .setIconPath("Tortoise/menuresolve.png")
        .setCommand("resolve"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Update to revision...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPDATEEXT)
        .setIconPath(menuUpdateIconPath)
        .setCommand("/rev"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Rename...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENURENAME)
        .setIconPath("TortoiseSvn/menurename.png")
        .setCommand("rename")
        .setMaxItemsCount(1));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Revert...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVERT)
        .setIconPath("Tortoise/menurevert.png")
        .setCommand("revert"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete unversioned items...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDELUNVERSIONED)
        .setIconPath(menuDeleteIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Clean up...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLEANUP)
        .setIconPath("Tortoise/menucleanup.png")
        .setCommand("cleanup"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Get lock...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOCK)
        .setIconPath("TortoiseSvn/menulock.png")
        .setCommand("lock"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Release lock...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUNLOCK)
        .setIconPath("TortoiseSvn/menuunlock.png")
        .setCommand("unlock"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Branch/tag...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOPY)
        .setIconPath("Tortoise/menucopy.png")
        .setCommand("copy"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Switch...")
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
        .setLabel("Merge all..")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUMERGEALL)
        .setIconPath(menuMergeIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Export...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUEXPORT)
        .setIconPath("Tortoise/menuexport.png")
        .setCommand("export"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Relocate...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENURELOCATE)
        .setIconPath("TortoiseSVN/menurelocate.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Create repository here")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCREATEREPOS)
        .setIconPath("TortoiseSVN/menucreaterepos.png")
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Add...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUADD)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("add"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Import...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIMPORT)
        .setIconPath("TortoiseSVN/menuimport.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Blame...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBLAME)
        .setIconPath("Tortoise/menublame.png")
        .setCommand("blame"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Copy URL to clipboard")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOPYURL)
        .setIconPath("TortoiseSVN/copy.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Add to ignore list")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIGNORE)
        .setIconPath("TortoiseSVN/menuignore.png")
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Create patch...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCREATEPATCH)
        .setIconPath("Tortoise/menudiff.png")
        .setCommand("createpatch"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Apply patch...")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUAPPLYPATCH)
        .setIconPath("Tortoise/menupatch.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Properties")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPROPERTIES)
        .setIconPath("TortoiseSVN/menuproperties.png")
        .setCommand("properties"));
    
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
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Paste")
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLIPPASTE)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
        */
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
    settings.setEntries(entries);
    settings.setSubMenuIconPath("TortoiseSvn/tsvnmenumultiple.png");
    settings.setSubMenuText("TortioseSVN");
    settings.setMainMenuPrefix("SVN");
    settings.setWorkingCopyFolderName(".svn");
    settings.setRegistryUserPath("HKEY_CURRENT_USER\\Software\\TortoiseSVN");
    settings.setContextMenuEntriesDefault(MENUCHECKOUT | MENUUPDATE | MENUCOMMIT);
  }
}
