package contextquickie.tortoise.git.entries;

public class GitSvnDCommit extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000010000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 337;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public GitSvnDCommit(String iconPath)
  {
    super(MenuTextIdentifier, "Git SVN DCommit...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucommit.ico");
    this.setCommand("commit");
    this.setMaxItemsCount(0); // TODO: Disabled
  }
}
