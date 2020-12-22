package contextquickie.tortoise.svn.entries;

public class UnifiedDiff extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000004000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 277;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public UnifiedDiff(String iconPath)
  {
    super(MenuTextIdentifier, "Unified Diff");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("urldiff");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled