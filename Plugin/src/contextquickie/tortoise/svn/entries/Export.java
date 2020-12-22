package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Export extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000200;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Export(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUEXPORT, "Export...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuexport.ico");
    this.setCommand("export");
    this.setMaxFileCount(0);
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}