package contextquickie.tortoise.git.entries;

public class Cleanup extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000020;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 126;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Cleanup(String iconPath)
  {
    super(MenuTextIdentifier, "Clean up...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucleanup.ico");
    this.setCommand("cleanup");
    this.setMaxFileCount(0);
  }
}