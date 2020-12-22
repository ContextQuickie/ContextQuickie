package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Cleanup extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000020;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Cleanup(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCLEANUP, "Clean up...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucleanup.ico");
    this.setCommand("cleanup");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}