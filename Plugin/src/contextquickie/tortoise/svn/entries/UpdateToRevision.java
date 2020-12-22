package contextquickie.tortoise.svn.entries;

public class UpdateToRevision extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000008000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 170;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public UpdateToRevision(String iconPath)
  {
    super(MenuTextIdentifier, "Update to revision...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuupdate.ico");
    this.setCommand("update");
    this.setParameter1("/rev");
  }
}