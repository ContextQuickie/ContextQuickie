package contextquickie.tortoise.svn.entries;

public class DiffWith extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000002000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 275;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWith(String iconPath)
  {
    super(MenuTextIdentifier, "Diff with \"%ls\"");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("diff");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled