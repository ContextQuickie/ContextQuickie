package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Switch extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000080;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Switch(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSWITCH, "Switch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuswitch.ico");
    this.setCommand("switch");
    this.setMaxItemsCount(1);
  }
}