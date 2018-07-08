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
        .setMenuId(TortoiseSvnMenuItems.MENUCHECKOUT)
        .setIconPath("Tortoise/menucheckout.png")
        .setCommand("checkout")
        .setIsVisibleInWorkingCopy(false)
        .setVisibleWithoutWorkingCopy(true)
        .setMaxFolderCount(1)
        .setMaxFileCount(0));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Update")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUUPDATE)
        .setIconPath(menuUpdateIconPath)
        .setCommand("update"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Commit")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCOMMIT)
        .setIconPath("Tortoise/menucommit.png")
        .setCommand("commit"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff later")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUDIFFLATER)
        .setIconPath(menuCompareIconPath)
        .setCommand("diff"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with \"%ls\"")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUDIFFNOW)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with previous version")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUPREVDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand("prevdiff"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Diff with URL")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUURLDIFF)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Show log")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENULOG)
        .setIconPath("Tortoise/menulog.png")
        .setCommand("log"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Repo-browser")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUREPOBROWSE)
        .setIconPath("Tortoise/menurepobrowse.png")
        .setCommand("repobrowser")
        .setEntryRequiresPath(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Check for modifications")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUSHOWCHANGED)
        .setIconPath("Tortoise/menushowchanged.png")
        .setCommand("repostatus"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Revision graph")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUREVISIONGRAPH)
        .setIconPath("Tortoise/menurevisiongraph.png")
        .setCommand("revisiongraph"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Edit conflicts")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCONFLICTEDITOR)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Resolve...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENURESOLVE)
        .setIconPath("Tortoise/menuresolve.png")
        .setCommand("resolve"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Update to revision...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUUPDATEEXT)
        .setIconPath(menuUpdateIconPath)
        .setCommand("/rev"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Rename...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENURENAME)
        .setIconPath("TortoiseSvn/menurename.png")
        .setCommand("rename"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUREMOVE)
        .setIconPath(menuDeleteIconPath)
        .setCommand("remove"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Revert...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUREVERT)
        .setIconPath("Tortoise/menurevert.png")
        .setCommand("revert"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Delete unversioned items...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUDELUNVERSIONED)
        .setIconPath(menuDeleteIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Clean up...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCLEANUP)
        .setIconPath("Tortoise/menucleanup.png")
        .setCommand("cleanup"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Get lock...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENULOCK)
        .setIconPath("TortoiseSvn/menulock.png")
        .setCommand("lock"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Release lock...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUUNLOCK)
        .setIconPath("TortoiseSvn/menuunlock.png")
        .setCommand("unlock"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Branch/tag...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCOPY)
        .setIconPath("Tortoise/menucopy.png")
        .setCommand("copy"));

    entries.add(new TortoiseMenuEntry()
        .setLabel("Switch...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUSWITCH)
        .setIconPath("Tortoise/menuswitch.png")
        .setCommand("switch"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Merge...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUMERGE)
        .setIconPath(menuMergeIconPath)
        .setCommand("merge"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Merge all..")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUMERGEALL)
        .setIconPath(menuMergeIconPath)
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Export...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUEXPORT)
        .setIconPath("Tortoise/menuexport.png")
        .setCommand("export"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Relocate...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENURELOCATE)
        .setIconPath("TortoiseSVN/menurelocate.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Create repository here")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCREATEREPOS)
        .setIconPath("TortoiseSVN/menucreaterepos.png")
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Add...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUADD)
        .setIconPath("Tortoise/menuadd.png")
        .setCommand("add"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Import...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUIMPORT)
        .setIconPath("TortoiseSVN/menuimport.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Blame...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUBLAME)
        .setIconPath("Tortoise/menublame.png")
        .setCommand("blame"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Copy URL to clipboard")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCOPYURL)
        .setIconPath("TortoiseSVN/copy.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Add to ignore list")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUIGNORE)
        .setIconPath("TortoiseSVN/menuignore.png")
        .setCommand(""));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Create patch...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCREATEPATCH)
        .setIconPath("Tortoise/menudiff.png")
        .setCommand("createpatch"));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Apply patch...")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUAPPLYPATCH)
        .setIconPath("Tortoise/menupatch.png")
        .setCommand(""));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Properties")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUPROPERTIES)
        .setIconPath("TortoiseSVN/menuproperties.png")
        .setCommand("properties"));
    
    // Separator
    entries.add(new TortoiseMenuEntry());
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Settings")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUSETTINGS)
        .setCommand("Tortoise/menusettings.png")
        .setCommand("settings")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Help")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUHELP)
        .setIconPath("Tortoise/menuhelp.png")
        .setCommand("help")
        .setEntryRequiresPath(false)
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("About")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUABOUT)
        .setIconPath("Tortoise/menuabout.png")
        .setCommand("about")
        .setVisibleWithoutWorkingCopy(true));
    
    entries.add(new TortoiseMenuEntry()
        .setLabel("Paste")
        .setCommandId(defaultCommandId)
        .setMenuId(TortoiseSvnMenuItems.MENUCLIPPASTE)
        .setIconPath(menuCompareIconPath)
        .setCommand(""));
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
    settings.setSubMenuIconPath("TortoiseSVN/tsvnmenumultiple.png");
    settings.setSubMenuText("TortioseSVN");
    settings.setMainMenuPrefix("SVN");
    settings.setWorkingCopyFolderName(".svn");
    settings.setRegistryUserPath("HKEY_CURRENT_USER\\Software\\TortoiseSVN");
    settings.setContextMenuEntriesDefault(TortoiseSvnMenuItems.MENUCHECKOUT | TortoiseSvnMenuItems.MENUUPDATE | TortoiseSvnMenuItems.MENUCOMMIT);
  }
}
