package contextquickie.tortoise.svn;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.Translation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

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
   */
  private static final long MENUUPGRADE = 0x0000000800000000L;

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
   */
  private static final long MENUUNIDIFF = 0x0000004000000000L;

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
  private static final long MENUSHELVE = 0x0000010000000000L;
  private static final long MENUUNSHELVE = 0x0000020000000000L;

  /**
   * Tortoise SVN menu configuration.
   */
  private static List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();

  /**
   * The instance using for translating the menu entries.
   */
  private static final Translation translation;

  /**
   * The path to the icons based on the used Tortoise SVN version.
   */
  private static final String iconPath = "TortoiseSvn/1.10/";

  static
  {
    final String defaultCommandId = "ContextQuickie.commands.TortoiseSvn.TortoiseSvnCommand";

    translation = new Translation(PreferenceConstants.TORTOISE_SVN);
    // Path to the "Update" icon
    final String menuUpdateIconPath = iconPath + "menuupdate.ico";

    // Path to the "Compare" icon.
    final String menuCompareIconPath = iconPath + "menucompare.ico";

    // Path to the "Merge" icon.
    final String menuMergeIconPath = iconPath + "menumerge.ico";

    // Path to the "Delete" icon
    final String menuDeleteIconPath = iconPath + "menudelete.ico";

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCHECKOUT, "Chekout..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCHECKOUT)
        .setIconPath(iconPath + "menucheckout.ico")
        .setCommand("checkout")
        .setIsVisibleInWorkingCopy(false)
        .setVisibleWithoutWorkingCopy(true)
        .setMaxFolderCount(1)
        .setMaxFileCount(0)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPGRADE, "Upgrade working copy"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPGRADE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("wcupgrade")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPDATE, "Update"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPDATE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCOMMIT, "Commit..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOMMIT)
        .setIconPath(iconPath + "menucommit.ico")
        .setCommand("commit"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    // Diff for one file or folder
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFF, "Diff"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    // Diff for two files
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFF, "Diff"))
        .setCommandId("ContextQuickie.commands.TortoiseSvn.TortoiseSvnDiffTwoFilesCommand")
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setMaxItemsCount(2)
        .setMinItemsCount(2)
        .setMaxFolderCount(0)
        .setUsesDefaultParameters(false)
        .addVisibilityChecker((entry, environment) -> AbstractTortoiseMenuBuilder.diffTwoFilesActive(entry, environment) == true));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFFLATER, "Diff later"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFLATER)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFFNOW, "Diff with \"%ls\""))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDIFFNOW)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUPREVDIFF, "Diff with previous version"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPREVDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("prevdiff")
        .setMaxFolderCount(0)
        .setMaxFileCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUURLDIFF, "Diff with URL"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUURLDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("urldiff")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUNIDIFF, "Unified Diff"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUNIDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("urldiff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENULOG, "Show log"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOG)
        .setIconPath(iconPath + "menulog.ico")
        .setCommand("log")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREPOBROWSE, "Repo-browser"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREPOBROWSE)
        .setIconPath(iconPath + "menurepobrowse.ico")
        .setCommand("repobrowser")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSHOWCHANGED, "Check for modifications"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSHOWCHANGED)
        .setIconPath(iconPath + "menushowchanged.ico")
        .setCommand("repostatus"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVISIONGRAPH, "Revision graph"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVISIONGRAPH)
        .setIconPath(iconPath + "menurevisiongraph.ico")
        .setCommand("revisiongraph")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCONFLICT, "Edit conflicts"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCONFLICTEDITOR)
        .setIconPath(menuCompareIconPath)
        .setCommand("conflicteditor")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURESOLVE, "Resolve..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENURESOLVE)
        .setIconPath(iconPath + "menuresolve.ico")
        .setCommand("resolve"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPDATEEXT, "Update to revision..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUPDATEEXT)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update")
        .setParameter1("/rev"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURENAME, "Rename..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENURENAME)
        .setIconPath(iconPath + "menurename.ico")
        .setCommand("rename")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVE, "Delete"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVEKEEP, "Delete (keep local)"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove")
        .setParameter1("/keep"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVERT, "Revert..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUREVERT)
        .setIconPath(iconPath + "menurevert.ico")
        .setCommand("revert"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDELUNVERSIONED, "Delete unversioned items..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUDELUNVERSIONED)
        .setIconPath(menuDeleteIconPath)
        .setCommand("delunversioned"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLEANUP, "Clean up..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLEANUP)
        .setIconPath(iconPath + "menucleanup.ico")
        .setCommand("cleanup")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENU_LOCK, "Get lock..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENULOCK)
        .setIconPath(iconPath + "menulock.ico")
        .setCommand("lock"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENU_UNLOCK, "Release lock..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUUNLOCK)
        .setIconPath(iconPath + "menuunlock.ico")
        .setCommand("unlock"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBRANCH, "Branch/tag..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOPY)
        .setIconPath(iconPath + "menucopy.ico")
        .setCommand("copy")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSWITCH, "Switch..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSWITCH)
        .setIconPath(iconPath + "menuswitch.ico")
        .setCommand("switch")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGE, "Merge..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGEALL, "Merge all.."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUMERGEALL)
        .setIconPath(menuMergeIconPath)
        .setCommand("mergeall"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUEXPORT, "Export..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUEXPORT)
        .setIconPath(iconPath + "menuexport.ico")
        .setCommand("export")
        .setMaxFileCount(0)
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURELOCATE, "Relocate..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENURELOCATE)
        .setIconPath(iconPath + "menurelocate.ico")
        .setCommand("relocate")
        .setMaxItemsCount(0)); // TODO: Disabled

    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCREATEREPOS, "Create repository here"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCREATEREPOS)
        .setIconPath(iconPath + "menucreaterepos.ico")
        .setCommand("repocreate")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUADD, "Add..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUADD)
        .setIconPath(iconPath + "menuadd.ico")
        .setCommand("add"));

     entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIMPORT, "Import..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIMPORT)
        .setIconPath(iconPath + "menuimport.ico")
        .setCommand("import")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBLAME, "Blame..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUBLAME)
        .setIconPath(iconPath + "menublame.ico")
        .setCommand("blame")
        .setMaxFileCount(1)
        .setMaxFolderCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCOPYURL, "Copy URL to clipboard"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCOPYURL)
        .setIconPath(iconPath + "copy.ico")
        .setCommand("copyurls"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIGNORE, "Add to ignore list"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUIGNORE)
        .setIconPath(iconPath + "menuignore.ico")
        .setCommand("ignore")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    // Separator
    entries.add(new TortoiseMenuEntry());

    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersion = preferenceStore.getString(PreferenceConstants.TORTOISE_SVN.getUsedVersion());
    if (usedVersion.equals("1.10"))
    {
      entries.add(new TortoiseMenuEntry()
          .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSHELVE, "Shelve..."))
          .setCommandId(defaultCommandId)
          .setMenuId(MENUSHELVE)
          .setIconPath(iconPath + "menushelve.ico")
          .setCommand("shelve"));

      entries.add(new TortoiseMenuEntry()
          .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUNSHELVE, "Unshelve..."))
          .setCommandId(defaultCommandId)
          .setMenuId(MENUUNSHELVE)
          .setIconPath(iconPath + "menuunshelve.ico")
          .setCommand("unshelve"));
    }

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCREATEPATCH, "Create patch..."))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCREATEPATCH)
        .setIconPath(iconPath + "menudiff.ico")
        .setCommand("createpatch"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUAPPLYPATCH, "Apply patch..."))
        .setCommandId("ContextQuickie.commands.TortoiseSvn.TortoiseSvnMergeCommand")
        .setMenuId(MENUAPPLYPATCH)
        .setIconPath(iconPath + "menupatch.ico")
        .setMaxFileCount(0)
        .setMaxFolderCount(1)
        .setUsesDefaultParameters(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUPROPERTIES, "Properties"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUPROPERTIES)
        .setIconPath(iconPath + "menuproperties.ico")
        .setCommand("properties"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLIPPASTE, "Paste"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUCLIPPASTE)
        .setIconPath(iconPath + "menuignore.ico")
        .setCommand("pastecopy")
        .setMaxItemsCount(0)); // TODO: Disabled

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSETTINGS, "Settings"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUSETTINGS)
        .setIconPath(iconPath + "menusettings.ico")
        .setCommand("settings")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUHELP, "Help"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUHELP)
        .setIconPath(iconPath + "menuhelp.ico")
        .setCommand("help")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUABOUT, "About"))
        .setCommandId(defaultCommandId)
        .setMenuId(MENUABOUT)
        .setIconPath(iconPath + "menuabout.ico")
        .setCommand("about")
        .setVisibleWithoutWorkingCopy(true));
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
    settings.setSubMenuIconPath(iconPath + "tsvnmenumultiple.ico");
    settings.setSubMenuText(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSUBMENU, "TortioseSVN"));
    settings.setMainMenuPrefix("SVN");
    settings.setContextMenuEntriesDefault(MENUCHECKOUT | MENUUPDATE | MENUCOMMIT);
  }
}
