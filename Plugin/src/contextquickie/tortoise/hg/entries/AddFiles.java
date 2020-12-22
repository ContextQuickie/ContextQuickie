package contextquickie.tortoise.hg.entries;

public class AddFiles extends AbstractTortoiseHgEntryForFile
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public AddFiles(String iconPath)
  {
    this.setCommand("add");
    this.setLabel("Add Files...");
    this.setHelpText("Add files to version control");
    this.setIconPath(iconPath + "menuadd.png");
  }
}
