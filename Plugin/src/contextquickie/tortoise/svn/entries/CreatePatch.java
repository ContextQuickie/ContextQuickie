package contextquickie.tortoise.svn.entries;

public class CreatePatch extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000001000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 206;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreatePatch(String iconPath)
  {
    super(MenuTextIdentifier, "Create patch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudiff.ico");
    this.setCommand("createpatch");
  }
}