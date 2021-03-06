package contextquickie.tortoise.git.entries;

public class Sync extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000002;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 339;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Sync(String iconPath)
  {
    super(MenuTextIdentifier, "Sync...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurelocate.ico");
    this.setCommand("sync");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
