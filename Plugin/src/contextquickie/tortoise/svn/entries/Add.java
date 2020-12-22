package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Add extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000008;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Add(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUADD, "Add...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuadd.ico");
    this.setCommand("add");
  }
}