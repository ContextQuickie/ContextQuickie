package contextquickie.tortoise.git.entries;

public class StashPop extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0002000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 341;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public StashPop(String iconPath)
  {
    super(MenuTextIdentifier, "Stash Pop");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurelocate.ico");
    this.setCommand("stashpop");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}