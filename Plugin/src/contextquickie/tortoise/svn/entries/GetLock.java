package contextquickie.tortoise.svn.entries;

public class GetLock extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000008000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 240;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public GetLock(String iconPath)
  {
    super(MenuTextIdentifier, "Get lock...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulock.ico");
    this.setCommand("lock");
  }
}