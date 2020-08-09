package contextquickie.tortoise.hg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuBuilder;
import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseMenuSettings;

public class TortoiseHgMenuBuilder extends AbstractTortoiseMenuBuilder implements BiPredicate<TortoiseMenuEntry, TortoiseEnvironment>
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
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("commit")
            .setLabel("Commit...")
            .setHelpText("Commit changes in repository")
            .setIconPath(iconPath + "menucommit.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("init")
            .setLabel("Create Repository Here")
            .setHelpText("Create a new repository")
            .setIconPath(iconPath + "menucreaterepos.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("clone")
            .setLabel("Clone...")
            .setHelpText("Create clone here from source")
            .setIconPath(iconPath + "menuclone.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("status")
            .setLabel("File Status")
            .setHelpText("Repository status & changes")
            .setIconPath(iconPath + "menushowchanged.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("add")
            .setLabel("Add Files...")
            .setHelpText("Add files to version control")
            .setIconPath(iconPath + "menuadd.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("revert")
            .setLabel("Revert Files...")
            .setHelpText("Revert file changes")
            .setIconPath(iconPath + "menurevert.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("forget")
            .setLabel("Forget Files...")
            .setHelpText("Remove files from version control")
            .setIconPath(iconPath + "menurevert.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("remove")
            .setLabel("Remove Files...")
            .setHelpText("Remove files from version control")
            .setIconPath(iconPath + "menudelete.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("rename")
            .setLabel("Rename File")
            .setHelpText("Rename file or directory")
            .setIconPath(iconPath + "general.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("workbench")
            .setLabel("Workbench")
            .setHelpText("View change history in repository")
            .setIconPath(iconPath + "menulog.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("log")
            .setLabel("File History")
            .setHelpText("View change history of selected files")
            .setIconPath(iconPath + "menulog.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("shelve")
            .setLabel("Shelve Changes")
            .setHelpText("Move changes between working dir and patch")
            .setIconPath(iconPath + "menucommit.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("synch")
            .setLabel("Synchronize")
            .setHelpText("Synchronize with remote repository")
            .setIconPath(iconPath + "menusynch.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("serve")
            .setLabel("Web Server")
            .setHelpText("Start web server for this repository")
            .setIconPath(iconPath + "proxy.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("update")
            .setLabel("Update...")
            .setHelpText("Update working directory")
            .setIconPath(iconPath + "menucheckout.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("thgstatus")
            .setLabel("Update Icons")
            .setHelpText("Update icons for this repository")
            .setIconPath(iconPath + "refresh_overlays.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("userconf")
            .setLabel("Global Settings")
            .setHelpText("Configure user wide settings")
            .setIconPath(iconPath + "settings_user.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("repoconf")
            .setLabel("Repository Settings")
            .setHelpText("Configure repository settings")
            .setIconPath(iconPath + "settings_repo.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("shellconf")
            .setLabel("Explorer Extension Settings")
            .setHelpText("Configure Explorer extension")
            .setIconPath(iconPath + "settings_user.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("about")
            .setLabel("About TortoiseHg")
            .setHelpText("Show About Dialog")
            .setIconPath(iconPath + "menuabout.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("vdiff")
            .setLabel("Diff to parent")
            .setHelpText("View changes using GUI diff tool")
            .setIconPath(iconPath + "TortoiseMerge.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("hgignore")
            .setLabel("Edit Ignore Filter")
            .setHelpText("Edit repository ignore filter")
            .setIconPath(iconPath + "ignore.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("guess")
            .setLabel("Guess Renames")
            .setHelpText("Detect renames and copies")
            .setIconPath(iconPath + "detect_rename.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("grep")
            .setLabel("Search History")
            .setHelpText("Search file revisions for patterns")
            .setIconPath(iconPath + "menurepobrowse.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("dndsynch")
            .setLabel("DnD Synchronize")
            .setHelpText("Synchronize with dragged repository")
            .setIconPath(iconPath + "menusynch.ico"));
  }

  public TortoiseHgMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_HG, settings);
    settings.setEntries(entries);
    settings.setMainMenuPrefix("Hg");
    settings.setSubMenuText("TortoiseHg");
    settings.setSubMenuIconPath(iconPath + "hg.ico");
    entries.forEach(entry -> entry.addVisibilityChecker(this));
    entries.forEach(entry -> entry.setMenuId(1));
    entries.forEach(entry -> entry.setCommandId("ContextQuickie.commands.TortoiseHg.TortoiseHgCommand"));
  }

  @Override
  public boolean test(TortoiseMenuEntry t, TortoiseEnvironment u)
  {
    // TODO Auto-generated method stub
    return true;
  }
}
