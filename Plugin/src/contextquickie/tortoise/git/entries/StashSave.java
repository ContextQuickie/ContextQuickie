package contextquickie.tortoise.git.entries;

public class StashSave extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000100000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 313;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public StashSave(String iconPath)
  {
    super(MenuTextIdentifier, "Stash Save");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucommit.ico");
    this.setCommand("stashsave");
    this.setMaxItemsCount(1);
  }
}