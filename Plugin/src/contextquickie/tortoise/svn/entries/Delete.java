package contextquickie.tortoise.svn.entries;

public class Delete extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000002000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 168;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Delete(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Delete");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudelete" + iconExtension);
    this.setCommand("remove");
  }
}