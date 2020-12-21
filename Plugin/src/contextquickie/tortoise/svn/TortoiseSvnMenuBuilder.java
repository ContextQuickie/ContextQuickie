package contextquickie.tortoise.svn;

import contextquickie.Activator;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSettings;
import contextquickie.tortoise.Translation;
import contextquickie.tortoise.Version;
import contextquickie.tortoise.svn.entries.ApplyPatch;
import contextquickie.tortoise.svn.entries.DiffLater;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author ContextQuickie
 *
 *         Menu configuration for TortoiseSVN.
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
   * TortoiseSVN menu configuration.
   */
  private static List<AbstractMenuEntry> entries = new ArrayList<AbstractMenuEntry>();

  /**
   * The instance using for translating the menu entries.
   */
  private static final Translation translation;

  /**
   * The path to the icons based on the used TortoiseSVN version.
   */
  private static final String iconPath;

  static
  {
    final String defaultCommandId = "ContextQuickie.commands.TortoiseSvn.TortoiseSvnCommand";

    translation = new Translation(PreferenceConstants.TORTOISE_SVN);
    
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    String usedVersionString = preferenceStore.getString(PreferenceConstants.TORTOISE_SVN.getUsedVersion());
    
    final Version usedVersion = new Version(usedVersionString);
    final Version version1_10 = new Version(1, 10);
    final Version version1_11 = new Version(1, 11);

    final String alternativeExtension;
    if (usedVersion.compareTo(version1_11) > 0)
    {
      iconPath = "TortoiseSvn/1.11/";

      // If using icons from version 1.11, some files require .png extension in eclipse
      alternativeExtension = ".png";
    }
    else
    {
      iconPath = "TortoiseSvn/1.10/";

      // If using icons from version 1.10 or before, all files have .ico extension
      alternativeExtension = ".ico";
    }
    
    // Path to the "Update" icon
    final String menuUpdateIconPath = iconPath + "menuupdate.ico";

    // Path to the "Compare" icon.
    final String menuCompareIconPath = iconPath + "menucompare.ico";

    // Path to the "Merge" icon.
    final String menuMergeIconPath = iconPath + "menumerge.ico";

    // Path to the "Delete" icon
    final String menuDeleteIconPath = iconPath + "menudelete" + alternativeExtension;

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCHECKOUT, "Chekout..."))

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

        .setMenuId(MENUUPGRADE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("wcupgrade")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPDATE, "Update"))

        .setMenuId(MENUUPDATE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCOMMIT, "Commit..."))

        .setMenuId(MENUCOMMIT)
        .setIconPath(iconPath + "menucommit.ico")
        .setCommand("commit"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    // Diff for one file or folder
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFF, "Diff"))
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    // Diff for two files
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFF, "Diff"))
        .setMenuId(MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setMaxItemsCount(2)
        .setMinItemsCount(2)
        .setMaxFolderCount(0));

    entries.add(new DiffLater(iconPath));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDIFFNOW, "Diff with \"%ls\""))
        .setMenuId(MENUDIFFNOW)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUPREVDIFF, "Diff with previous version"))
        .setMenuId(MENUPREVDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("prevdiff")
        .setMaxFolderCount(0)
        .setMaxFileCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUURLDIFF, "Diff with URL"))
        .setMenuId(MENUURLDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("urldiff")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUNIDIFF, "Unified Diff"))
        .setMenuId(MENUUNIDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("urldiff")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENULOG, "Show log"))
        .setMenuId(MENULOG)
        .setIconPath(iconPath + "menulog.ico")
        .setCommand("log")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREPOBROWSE, "Repo-browser"))
        .setMenuId(MENUREPOBROWSE)
        .setIconPath(iconPath + "menurepobrowse.ico")
        .setCommand("repobrowser")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSHOWCHANGED, "Check for modifications"))
        .setMenuId(MENUSHOWCHANGED)
        .setIconPath(iconPath + "menushowchanged.ico")
        .setCommand("repostatus"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVISIONGRAPH, "Revision graph"))
        .setMenuId(MENUREVISIONGRAPH)
        .setIconPath(iconPath + "menurevisiongraph.ico")
        .setCommand("revisiongraph")
        .setMaxItemsCount(1)
        .setSupportingLinkedResources(false));

    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCONFLICT, "Edit conflicts"))
        .setMenuId(MENUCONFLICTEDITOR)
        .setIconPath(menuCompareIconPath)
        .setCommand("conflicteditor")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURESOLVE, "Resolve..."))
        .setMenuId(MENURESOLVE)
        .setIconPath(iconPath + "menuresolve.ico")
        .setCommand("resolve"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUPDATEEXT, "Update to revision..."))
        .setMenuId(MENUUPDATEEXT)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update")
        .setParameter1("/rev"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURENAME, "Rename..."))
        .setMenuId(MENURENAME)
        .setIconPath(iconPath + "menurename.ico")
        .setCommand("rename")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVE, "Delete"))
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREMOVEKEEP, "Delete (keep local)"))
        .setMenuId(MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove")
        .setParameter1("/keep"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUREVERT, "Revert..."))
        .setMenuId(MENUREVERT)
        .setIconPath(iconPath + "menurevert" + alternativeExtension)
        .setCommand("revert"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUDELUNVERSIONED, "Delete unversioned items..."))
        .setMenuId(MENUDELUNVERSIONED)
        .setIconPath(menuDeleteIconPath)
        .setCommand("delunversioned"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLEANUP, "Clean up..."))
        .setMenuId(MENUCLEANUP)
        .setIconPath(iconPath + "menucleanup.ico")
        .setCommand("cleanup")
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENU_LOCK, "Get lock..."))
        .setMenuId(MENULOCK)
        .setIconPath(iconPath + "menulock.ico")
        .setCommand("lock"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENU_UNLOCK, "Release lock..."))
        .setMenuId(MENUUNLOCK)
        .setIconPath(iconPath + "menuunlock.ico")
        .setCommand("unlock"));

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBRANCH, "Branch/tag..."))
        .setMenuId(MENUCOPY)
        .setIconPath(iconPath + "menucopy.ico")
        .setCommand("copy")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSWITCH, "Switch..."))
        .setMenuId(MENUSWITCH)
        .setIconPath(iconPath + "menuswitch.ico")
        .setCommand("switch")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGE, "Merge..."))
        .setMenuId(MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge")
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUMERGEALL, "Merge all.."))
        .setMenuId(MENUMERGEALL)
        .setIconPath(menuMergeIconPath)
        .setCommand("mergeall"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUEXPORT, "Export..."))

        .setMenuId(MENUEXPORT)
        .setIconPath(iconPath + "menuexport.ico")
        .setCommand("export")
        .setMaxFileCount(0)
        .setMaxFolderCount(1)
        .setMaxItemsCount(1));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENURELOCATE, "Relocate..."))
        .setMenuId(MENURELOCATE)
        .setIconPath(iconPath + "menurelocate.ico")
        .setCommand("relocate")
        .setMaxItemsCount(0)); // TODO: Disabled

    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCREATEREPOS, "Create repository here"))
        .setMenuId(MENUCREATEREPOS)
        .setIconPath(iconPath + "menucreaterepos.ico")
        .setCommand("repocreate")
        .setMaxItemsCount(0)); // TODO: Disabled

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUADD, "Add..."))
        .setMenuId(MENUADD)
        .setIconPath(iconPath + "menuadd.ico")
        .setCommand("add"));

     entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIMPORT, "Import..."))
        .setMenuId(MENUIMPORT)
        .setIconPath(iconPath + "menuimport.ico")
        .setCommand("import")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUBLAME, "Blame..."))
        .setMenuId(MENUBLAME)
        .setIconPath(iconPath + "menublame.ico")
        .setCommand("blame")
        .setMaxFileCount(1)
        .setMaxFolderCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCOPYURL, "Copy URL to clipboard"))

        .setMenuId(MENUCOPYURL)
        .setIconPath(iconPath + "copy.ico")
        .setCommand("copyurls"));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUIGNORE, "Add to ignore list"))
        .setMenuId(MENUIGNORE)
        .setIconPath(iconPath + "menuignore.ico")
        .setCommand("ignore")
        .setMaxItemsCount(0)); // TODO: Disabled
    
    // Separator
    entries.add(new TortoiseMenuEntry());

    if (usedVersion.compareTo(version1_10) >= 0)
    {
      entries.add(new TortoiseMenuEntry()
          .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSHELVE, "Shelve..."))
            .setMenuId(MENUSHELVE)
          .setIconPath(iconPath + "menushelve.ico")
          .setCommand("shelve"));

      entries.add(new TortoiseMenuEntry()
          .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUUNSHELVE, "Unshelve..."))
            .setMenuId(MENUUNSHELVE)
          .setIconPath(iconPath + "menuunshelve.ico")
          .setCommand("unshelve"));
    }

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCREATEPATCH, "Create patch..."))
        .setMenuId(MENUCREATEPATCH)
        .setIconPath(iconPath + "menudiff.ico")
        .setCommand("createpatch"));

    entries.add(new ApplyPatch(iconPath));

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUPROPERTIES, "Properties"))
        .setMenuId(MENUPROPERTIES)
        .setIconPath(iconPath + "menuproperties.ico")
        .setCommand("properties"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUCLIPPASTE, "Paste"))
        .setMenuId(MENUCLIPPASTE)
        .setIconPath(iconPath + "menuignore.ico")
        .setCommand("pastecopy")
        .setMaxItemsCount(0)); // TODO: Disabled

    // Separator
    entries.add(new TortoiseMenuEntry());

    entries.add(new TortoiseMenuEntry()
        .setLabel(translation.getTranslatedString(MenuTextIdentifier.IDS_MENUSETTINGS, "Settings"))
        .setMenuId(MENUSETTINGS)
        .setIconPath(iconPath + "menusettings" + alternativeExtension)
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
