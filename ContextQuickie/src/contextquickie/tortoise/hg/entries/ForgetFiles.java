package contextquickie.tortoise.hg.entries;

public class ForgetFiles extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public ForgetFiles(String iconPath)
  {
    this.setCommand("forget");
    this.setLabel("Forget Files...");
    this.setHelpText("Remove files from version control");
    this.setIconPath(iconPath + "menudelete.ico");
  }
}
