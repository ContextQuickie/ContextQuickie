package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Rename extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000004000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Rename(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENURENAME, "Rename...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurename.ico");
    this.setCommand("rename");
    this.setMaxItemsCount(1);
  }
}