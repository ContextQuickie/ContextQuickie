package contextquickie.tortoise.git.entries;

public class Rename extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000004000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 166;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Rename(String iconPath)
  {
    super(MenuTextIdentifier, "Rename...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurename.ico");
    this.setCommand("rename");
    this.setMaxFolderCount(1);
  }
}