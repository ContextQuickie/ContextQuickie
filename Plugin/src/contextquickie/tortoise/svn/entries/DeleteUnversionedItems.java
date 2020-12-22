package contextquickie.tortoise.svn.entries;

public class DeleteUnversionedItems extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000080000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 268;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DeleteUnversionedItems(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Delete unversioned items...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudelete" + iconExtension);
    this.setCommand("delunversioned");
  }
}