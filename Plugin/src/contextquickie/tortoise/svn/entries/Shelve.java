package contextquickie.tortoise.svn.entries;

public class Shelve extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000010000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 286;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Shelve(String iconPath)
  {
    super(MenuTextIdentifier, "Shelve...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menushelve.ico");
    this.setCommand("shelve");
  }
}