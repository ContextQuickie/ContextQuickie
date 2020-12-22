package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Import extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000100;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Import(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUIMPORT, "Import...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuimport.ico");
    this.setCommand("import");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled