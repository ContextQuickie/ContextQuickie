package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class BranchTag extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000800;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BranchTag(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUBRANCH, "Branch/tag...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucopy.ico");
    this.setCommand("copy");
    this.setMaxItemsCount(1);
  }
}