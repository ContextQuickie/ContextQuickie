package contextquickie.tortoise.svn.entries;

public class Commit extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000004;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 122;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Commit(String iconPath)
  {
    super(MenuTextIdentifier, "Commit...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucommit.ico");
    this.setCommand("commit");
  }
}