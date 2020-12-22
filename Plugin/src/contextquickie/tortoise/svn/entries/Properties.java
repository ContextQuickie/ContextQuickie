package contextquickie.tortoise.svn.entries;

public class Properties extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000020000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 257;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Properties(String iconPath)
  {
    super(MenuTextIdentifier, "Properties");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuproperties.ico");
    this.setCommand("properties");
  }
}