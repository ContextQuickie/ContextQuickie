package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class About extends AbstractTortoiseGitEntry
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
    this.setEntryRequiresPath(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}
