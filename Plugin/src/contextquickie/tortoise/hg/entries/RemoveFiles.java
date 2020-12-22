package contextquickie.tortoise.hg.entries;

public class RemoveFiles extends AbstractTortoiseHgEntryForFile
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RemoveFiles(String iconPath)
  {
    this.setCommand("remove");
    this.setLabel("Remove Files...");
    this.setHelpText("Remove files from version control");
    this.setIconPath(iconPath + "menudelete.png");
  }
}
