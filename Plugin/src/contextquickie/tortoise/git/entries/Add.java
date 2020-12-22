package contextquickie.tortoise.git.entries;

public class Add extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000008;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 124;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Add(String iconPath)
  {
    super(MenuTextIdentifier, "Add...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuadd.ico");
    this.setCommand("add");
  }
}