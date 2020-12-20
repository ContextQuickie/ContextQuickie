package contextquickie.tortoise.git.entries;

public class DiffTwoFiles extends Diff
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000010000;
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public DiffTwoFiles(String iconPath)
  {
    super(iconPath);
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommandId("ContextQuickie.commands.TortoiseGit.TortoiseGitDiffTwoFilesCommand");
    this.setMaxItemsCount(2);
    this.setMinItemsCount(2);
    this.setMaxFolderCount(0);
    this.setUsesDefaultParameters(false);
  }
}
