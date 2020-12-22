package contextquickie.tortoise.git.entries;

public class StashApply extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000200000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 314;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public StashApply(String iconPath)
  {
    super(MenuTextIdentifier, "Stash Apply");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurelocate.ico");
    this.setCommand("stashapply");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}