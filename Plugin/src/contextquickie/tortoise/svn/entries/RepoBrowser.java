package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class RepoBrowser extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000400000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RepoBrowser(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUREPOBROWSE, "Repo-browser");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurepobrowse.ico");
    this.setCommand("repobrowser");
    this.setMaxItemsCount(1);
    this.setSupportingLinkedResources(false);
  }
}