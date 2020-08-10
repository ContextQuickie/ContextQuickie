package contextquickie.tortoise.hg.entries;

public class FileHistory extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public FileHistory(String iconPath)
  {
    this.setCommand("log");
    this.setLabel("File History");
    this.setHelpText("View change history of selected files");
    this.setIconPath(iconPath + "menulog.png");
  }
}
