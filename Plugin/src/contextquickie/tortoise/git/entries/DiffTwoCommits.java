package contextquickie.tortoise.git.entries;

public class DiffTwoCommits extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0004000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 344;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffTwoCommits(String iconPath)
  {
    super(MenuTextIdentifier, "Diff two commits");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("diffcommits");
    this.setMaxItemsCount(0); // Disabled. Didn't find any hint in TortoiseGit source how to handle this entry.
  }
}
