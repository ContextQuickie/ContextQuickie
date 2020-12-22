package contextquickie.tortoise.hg.entries;

public class RenameFile extends AbstractTortoiseHgEntryForFile
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RenameFile(String iconPath)
  {
    this.setCommand("rename");
    this.setLabel("Rename File");
    this.setHelpText("Rename file or directory");
    this.setIconPath(iconPath + "general.png");
  }
}
