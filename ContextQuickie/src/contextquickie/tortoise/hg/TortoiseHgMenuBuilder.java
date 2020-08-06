package contextquickie.tortoise.hg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;

import contextquickie.base.AbstractMenuBuilder;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.TortoiseMenuEntry;

public class TortoiseHgMenuBuilder extends AbstractMenuBuilder
{
  /**
   * Tortoise Hg menu configuration.
   */
  private static final List<TortoiseMenuEntry> entries = new ArrayList<TortoiseMenuEntry>();

  static
  {
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("commit")
            .setLabel("Commit...")
            .setHelpText("Commit changes in repository")
            .setIconPath("menucommit.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("init")
            .setLabel("Create Repository Here")
            .setHelpText("Create a new repository")
            .setIconPath("menucreaterepos.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("clone")
            .setLabel("Clone...")
            .setHelpText("Create clone here from source")
            .setIconPath("menuclone.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("status")
            .setLabel("File Status")
            .setHelpText("Repository status & changes")
            .setIconPath("menushowchanged.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("add")
            .setLabel("Add Files...")
            .setHelpText("Add files to version control")
            .setIconPath("menuadd.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("revert")
            .setLabel("Revert Files...")
            .setHelpText("Revert file changes")
            .setIconPath("menurevert.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("forget")
            .setLabel("Forget Files...")
            .setHelpText("Remove files from version control")
            .setIconPath("menurevert.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("remove")
            .setLabel("Remove Files...")
            .setHelpText("Remove files from version control")
            .setIconPath("menudelete.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("rename")
            .setLabel("Rename File")
            .setHelpText("Rename file or directory")
            .setIconPath("general.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("workbench")
            .setLabel("Workbench")
            .setHelpText("View change history in repository")
            .setIconPath("menulog.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("log")
            .setLabel("File History")
            .setHelpText("View change history of selected files")
            .setIconPath("menulog.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("shelve")
            .setLabel("Shelve Changes")
            .setHelpText("Move changes between working dir and patch")
            .setIconPath("menucommit.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("synch")
            .setLabel("Synchronize")
            .setHelpText("Synchronize with remote repository")
            .setIconPath("menusynch.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("serve")
            .setLabel("Web Server")
            .setHelpText("Start web server for this repository")
            .setIconPath("proxy.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("update")
            .setLabel("Update...")
            .setHelpText("Update working directory")
            .setIconPath("menucheckout.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("thgstatus")
            .setLabel("Update Icons")
            .setHelpText("Update icons for this repository")
            .setIconPath("refresh_overlays.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("userconf")
            .setLabel("Global Settings")
            .setHelpText("Configure user wide settings")
            .setIconPath("settings_user.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("repoconf")
            .setLabel("Repository Settings")
            .setHelpText("Configure repository settings")
            .setIconPath("settings_repo.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("shellconf")
            .setLabel("Explorer Extension Settings")
            .setHelpText("Configure Explorer extension")
            .setIconPath("settings_user.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("about")
            .setLabel("About TortoiseHg")
            .setHelpText("Show About Dialog")
            .setIconPath("menuabout.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("vdiff")
            .setLabel("Diff to parent")
            .setHelpText("View changes using GUI diff tool")
            .setIconPath("TortoiseMerge.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("hgignore")
            .setLabel("Edit Ignore Filter")
            .setHelpText("Edit repository ignore filter")
            .setIconPath("ignore.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("guess")
            .setLabel("Guess Renames")
            .setHelpText("Detect renames and copies")
            .setIconPath("detect_rename.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("grep")
            .setLabel("Search History")
            .setHelpText("Search file revisions for patterns")
            .setIconPath("menurepobrowse.ico"));
    entries
        .add(new TortoiseMenuEntry()
            .setCommand("dndsynch")
            .setLabel("DnD Synchronize")
            .setHelpText("Synchronize with dragged repository")
            .setIconPath("menusynch.ico"));
  }

  public TortoiseHgMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_HG.getEnabled());
  }

  @Override
  protected List<IContributionItem> getMenuEntries()
  {
    // TODO Auto-generated method stub
    return Collections.<IContributionItem>emptyList();
  }
}
