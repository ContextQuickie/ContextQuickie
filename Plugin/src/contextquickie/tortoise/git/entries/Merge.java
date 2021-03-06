package contextquickie.tortoise.git.entries;

public class Merge extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000001000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 129;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Merge(String iconPath)
  {
    super(MenuTextIdentifier, "Merge...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menumerge.ico");
    this.setCommand("merge");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}