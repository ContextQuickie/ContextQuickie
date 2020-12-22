package contextquickie.tortoise.svn.entries;

public class BranchTag extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000800;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 130;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BranchTag(String iconPath)
  {
    super(MenuTextIdentifier, "Branch/tag...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucopy.ico");
    this.setCommand("copy");
    this.setMaxItemsCount(1);
  }
}