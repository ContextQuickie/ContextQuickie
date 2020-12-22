package contextquickie.tortoise.git.entries;

public class SubmoduleAdd extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000800000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 315;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SubmoduleAdd(String iconPath)
  {
    super(MenuTextIdentifier, "Submodule Add...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuadd.ico");
    this.setCommand("subadd");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}