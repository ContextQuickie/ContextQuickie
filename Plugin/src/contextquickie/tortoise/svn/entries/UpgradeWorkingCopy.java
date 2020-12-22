package contextquickie.tortoise.svn.entries;

public class UpgradeWorkingCopy extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000800000000L;

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
  public UpgradeWorkingCopy(String iconPath)
  {
    super(MenuTextIdentifier, "Upgrade working copy");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuupdate.ico");
    this.setCommand("wcupgrade");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled