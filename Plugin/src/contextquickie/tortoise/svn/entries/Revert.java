package contextquickie.tortoise.svn.entries;

public class Revert extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000010;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 125;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Revert(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Revert...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurevert" + iconExtension);
    this.setCommand("revert");
  }
}