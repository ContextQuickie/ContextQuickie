package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class About extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x8000000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public About(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUABOUT, "About");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuabout.ico");
    this.setCommand("about");
    this.setVisibleWithoutWorkingCopy(true);
  }
}
