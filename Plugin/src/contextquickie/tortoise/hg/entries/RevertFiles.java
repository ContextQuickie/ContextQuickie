package contextquickie.tortoise.hg.entries;

public class RevertFiles extends AbstractTortoiseHgEntryForFile
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public RevertFiles(String iconPath)
  {
    this.setCommand("revert");
    this.setLabel("Revert Files...");
    this.setHelpText("Revert file changes");
    this.setIconPath(iconPath + "menurevert.png");
  }
}
