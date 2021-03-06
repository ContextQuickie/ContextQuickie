package contextquickie.tortoise.git.entries;

public class ShowLog extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000020000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 180;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ShowLog(String iconPath)
  {
    super(MenuTextIdentifier, "Show log");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulog.ico");
    this.setCommand("log");
    this.setMaxItemsCount(1);
  }
}
