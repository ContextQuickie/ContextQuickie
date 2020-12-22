package contextquickie.tortoise.git.entries;

public class ShowRefLog extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000400000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 317;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ShowRefLog(String iconPath)
  {
    super(MenuTextIdentifier, "Show Reflog");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulog.ico");
    this.setCommand("reflog");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}