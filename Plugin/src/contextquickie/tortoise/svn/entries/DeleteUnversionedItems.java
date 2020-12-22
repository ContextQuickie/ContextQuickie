package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class DeleteUnversionedItems extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000080000000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DeleteUnversionedItems(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUDELUNVERSIONED, "Delete unversioned items...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudelete" + iconExtension);
    this.setCommand("delunversioned");
  }
}