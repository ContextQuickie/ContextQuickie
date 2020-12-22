package contextquickie.tortoise.git.entries;

public class StashList extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000400000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 319;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public StashList(String iconPath)
  {
    super(MenuTextIdentifier, "Stash List");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulog.ico");
    this.setCommand("reflog");
    this.setParameter1("/ref:refs/stash");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}