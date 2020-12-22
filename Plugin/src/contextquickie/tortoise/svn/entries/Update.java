package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Update extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000002;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Update(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUUPDATE, "Update");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuupdate.ico");
    this.setCommand("update");
  }
}