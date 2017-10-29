package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.handlers.tortoise.TortoiseMenuEntry;
import contextquickie.handlers.tortoise.TortoiseMenuSettings;
import contextquickie.preferences.PreferenceConstants;
import java.util.Arrays;
import java.util.List;

/**
 * @author ContextQuickie
 *
 *         Menu configuration for Tortoise SVN.
 */
public class TortoiseSvnMenuBuilder extends AbstractTortoiseMenuBuilder
{
  /**
   * The default command ID.
   */
  private static final String DEFAULT_COMMAND_ID = "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand";
  
  /**
   * Path to the "Update" icon.
   */
  private static final String MENU_UPDATE_ICON_PATH = "TortoiseSVN/menuupdate.png";
  
  /**
   * Path to the "Compare" icon.
   */
  private static final String MENU_COMPARE_ICON_PATH = "Tortoise/menucompare.png";
  
  /**
   * Path to the "Merge" icon.
   */
  private static final String MENU_MERGE_ICON_PATH = "Tortoise/menumerge.png";
  
  /**
   * Path to the "Delete" icon.
   */
  private static final String MENU_DELETE_ICON_PATH = "TortoiseSVN/menudelete.png";

  /**
   * Tortoise SVN menu configuration.
   */
  private static List<TortoiseMenuEntry> entries = Arrays.asList(
      new TortoiseMenuEntry("Chekout...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCHECKOUT, "Tortoise/menucheckout.png", "checkout", true),
      new TortoiseMenuEntry("Update", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUUPDATE, MENU_UPDATE_ICON_PATH, "update", true),
      new TortoiseMenuEntry("Commit", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCOMMIT, "Tortoise/menucommit.png", "commit", true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry("Diff", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUDIFF, MENU_COMPARE_ICON_PATH, "", true),
      new TortoiseMenuEntry("Diff later", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUDIFFLATER, MENU_COMPARE_ICON_PATH, "diff", true),
      new TortoiseMenuEntry("Diff with \"%ls\"", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUDIFFNOW, MENU_COMPARE_ICON_PATH, "", true),
      new TortoiseMenuEntry("Diff with previous version", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUPREVDIFF, MENU_COMPARE_ICON_PATH,
          "prevdiff", true),
      new TortoiseMenuEntry("Diff with URL", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUURLDIFF, MENU_COMPARE_ICON_PATH, "", true),
      new TortoiseMenuEntry("Show log", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENULOG, "Tortoise/menulog.png", "log", true),
      new TortoiseMenuEntry("Repo-browser", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUREPOBROWSE, "Tortoise/menurepobrowse.png", "repobrowser",
          true),
      new TortoiseMenuEntry("Check for modifications", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUSHOWCHANGED, "Tortoise/menushowchanged.png",
          "repostatus", true),
      new TortoiseMenuEntry("Revision graph", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUREVISIONGRAPH, "Tortoise/menurevisiongraph.png",
          "revisiongraph", true),
      new TortoiseMenuEntry("Edit conflicts", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCONFLICTEDITOR, MENU_COMPARE_ICON_PATH, "", true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry("Resolve...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENURESOLVE, "Tortoise/menuresolve.png", "resolve", true),
      new TortoiseMenuEntry("Update to revision...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUUPDATEEXT, MENU_UPDATE_ICON_PATH, "/rev",
          true),
      new TortoiseMenuEntry("Rename...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENURENAME, "TortoiseSVN/menurename.png", "rename", true),
      new TortoiseMenuEntry("Delete", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUREMOVE, MENU_DELETE_ICON_PATH, "remove", true),
      new TortoiseMenuEntry("Revert...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUREVERT, "Tortoise/menurevert.png", "revert", true),
      new TortoiseMenuEntry("Delete unversioned items...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUDELUNVERSIONED, MENU_DELETE_ICON_PATH,
          "", true),
      new TortoiseMenuEntry("Clean up...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCLEANUP, "Tortoise/menucleanup.png", "cleanup", true),
      new TortoiseMenuEntry("Get lock...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENULOCK, "TortoiseSVN/menulock.png", "lock", true),
      new TortoiseMenuEntry("Release lock...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUUNLOCK, "TortoiseSVN/menuunlock.png", "unlock", true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry("Branch/tag...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCOPY, "Tortoise/menucopy.png", "copy", true),
      new TortoiseMenuEntry("Switch...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUSWITCH, "Tortoise/menuswitch.png", "switch", true),
      new TortoiseMenuEntry("Merge...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUMERGE, MENU_MERGE_ICON_PATH, "merge", true),
      new TortoiseMenuEntry("Merge all..", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUMERGEALL, MENU_MERGE_ICON_PATH, "", true),
      new TortoiseMenuEntry("Export...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUEXPORT, "Tortoise/menuexport.png", "export", true),
      new TortoiseMenuEntry("Relocate...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENURELOCATE, "TortoiseSVN/menurelocate.png", "", true),
      new TortoiseMenuEntry("Create repository here", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCREATEREPOS, "TortoiseSVN/menucreaterepos.png", "",
          true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry("Add...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUADD, "Tortoise/menuadd.png", "add", true),
      new TortoiseMenuEntry("Import...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUIMPORT, "TortoiseSVN/menuimport.png", "", true),
      new TortoiseMenuEntry("Blame...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUBLAME, "Tortoise/menublame.png", "blame", true),
      new TortoiseMenuEntry("Copy URL to clipboard", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCOPYURL, "TortoiseSVN/copy.png", "", true),
      new TortoiseMenuEntry("Add to ignore list", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUIGNORE, "TortoiseSVN/menuignore.png", "", true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry(
          "Create patch...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCREATEPATCH, "Tortoise/menudiff.png", "createpatch", true),
      new TortoiseMenuEntry("Apply patch...", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUAPPLYPATCH, "Tortoise/menupatch.png", "", true),
      new TortoiseMenuEntry("Properties", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUPROPERTIES, "TortoiseSVN/menuproperties.png", "properties",
          true),
      new TortoiseMenuEntry(null, null, 0, null, "", true),
      new TortoiseMenuEntry("Settings", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUSETTINGS, "Tortoise/menusettings.png", "settings", false),
      new TortoiseMenuEntry("Help", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUHELP, "Tortoise/menuhelp.png", "help", false),
      new TortoiseMenuEntry("About", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUABOUT, "Tortoise/menuabout.png", "about", true),
      new TortoiseMenuEntry("Paste", DEFAULT_COMMAND_ID, TortoiseSvnMenuItems.MENUCLIPPASTE, MENU_COMPARE_ICON_PATH, "", true));

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
    settings.setSubMenuIconPath("TortoiseSVN/tsvnmenumultiple.png");
    settings.setSubMenuText("TortioseSVN");
    settings.setMainMenuPrefix("SVN");
    settings.setRegistryUserPath("HKEY_CURRENT_USER\\Software\\TortoiseSVN");
    settings.setContextMenuEntriesDefault(TortoiseSvnMenuItems.MENUCHECKOUT | TortoiseSvnMenuItems.MENUUPDATE | TortoiseSvnMenuItems.MENUCOMMIT);
  }
}
