package contextquickie.tortoise.git.entries;

public class Pull extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000800000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 221;

  /**
   * Constructor.
   * 
   * @param iconPath
   *        The path containing the icon for this instance.
   */
  public Pull(String iconPath)
  {
    super(MenuTextIdentifier, "Pull...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "pull1.ico");
    this.setCommand("pull");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
