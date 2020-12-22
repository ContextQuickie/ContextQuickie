package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class ReleaseLock extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000010000000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ReleaseLock(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENU_UNLOCK, "Release lock...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuunlock.ico");
    this.setCommand("unlock");
  }
}