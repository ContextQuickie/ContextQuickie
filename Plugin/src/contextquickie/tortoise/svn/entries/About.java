package contextquickie.tortoise.svn.entries;

public class About extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x8000000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 134;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public About(String iconPath)
  {
    super(MenuTextIdentifier, "About");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuabout.ico");
    this.setCommand("about");
    this.setVisibleWithoutWorkingCopy(true);
  }
}
