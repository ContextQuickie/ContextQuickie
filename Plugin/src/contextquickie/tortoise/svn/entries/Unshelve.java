package contextquickie.tortoise.svn.entries;

public class Unshelve extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 287;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Unshelve(String iconPath)
  {
    super(MenuTextIdentifier, "Unshelve...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuunshelve.ico");
    this.setCommand("unshelve");
  }
}