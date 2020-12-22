package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class UpgradeWorkingCopy extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000800000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public UpgradeWorkingCopy(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUUPGRADE, "Upgrade working copy");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuupdate.ico");
    this.setCommand("wcupgrade");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled