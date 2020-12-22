package contextquickie.tortoise.git.entries;

public class RepoBrowser extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000001000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 227;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RepoBrowser(String iconPath)
  {
    super(MenuTextIdentifier, "Repo-browser");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurepobrowse.ico");
    this.setCommand("repobrowser");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}