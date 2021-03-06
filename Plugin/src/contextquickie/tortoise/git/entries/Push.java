package contextquickie.tortoise.git.entries;

public class Push extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000001000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 222;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Push(String iconPath)
  {
    super(MenuTextIdentifier, "Push...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "Push.ico");
    this.setCommand("push");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
