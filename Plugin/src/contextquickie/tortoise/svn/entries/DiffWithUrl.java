package contextquickie.tortoise.svn.entries;

public class DiffWithUrl extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000040000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 261;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWithUrl(String iconPath)
  {
    super(MenuTextIdentifier, "Diff with URL");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("urldiff");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled