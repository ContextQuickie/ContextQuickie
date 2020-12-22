package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Shelve extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000010000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Shelve(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSHELVE, "Shelve...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menushelve.ico");
    this.setCommand("shelve");
  }
}