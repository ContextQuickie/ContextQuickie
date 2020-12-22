package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class AddToIgnoreList extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000200000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public AddToIgnoreList(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUIGNORE, "Add to ignore list");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuignore.ico");
    this.setCommand("ignore");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled