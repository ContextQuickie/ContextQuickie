package contextquickie.tortoise.git.entries;

public class AddToIgnoreList extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000200000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 194;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public AddToIgnoreList(String iconPath)
  {
    super(MenuTextIdentifier, "Add to ignore list");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuignore.ico");
    this.setCommand("");
  }
}