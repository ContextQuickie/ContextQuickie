package contextquickie.tortoise.git.entries;

public class Clone extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000002000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 223;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Clone(String iconPath)
  {
    super(MenuTextIdentifier, "Clone");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucheckout.ico");
    this.setCommand("clone");
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
    this.setMinFolderCount(1);
    this.setMaxFolderCount(1);
    this.setMaxFileCount(0);
  }
}
