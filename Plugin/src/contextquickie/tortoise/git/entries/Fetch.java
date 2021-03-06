package contextquickie.tortoise.git.entries;

public class Fetch extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000040000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 311;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Fetch(String iconPath)
  {
    super(MenuTextIdentifier, "Fetch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "pull1.ico");
    this.setCommand("fetch");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
