package contextquickie.tortoise.git.entries;

public class CreateBranch extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000800;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 130;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreateBranch(String iconPath)
  {
    super(MenuTextIdentifier, "Create Branch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucopy.ico");
    this.setCommand("branch");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}