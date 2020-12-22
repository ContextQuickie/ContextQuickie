package contextquickie.tortoise.git.entries;

public class Rebase extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000080000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 312;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Rebase(String iconPath)
  {
    super(MenuTextIdentifier, "Rebase");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurebase.ico");
    this.setCommand("rebase");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}