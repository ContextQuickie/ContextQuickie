package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Unshelve extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Unshelve(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUUNSHELVE, "Unshelve...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuunshelve.ico");
    this.setCommand("unshelve");
  }
}