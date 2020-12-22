package contextquickie.tortoise.git.entries;

public class Daemon extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0200000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 216;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Daemon(String iconPath)
  {
    super(MenuTextIdentifier, "Daemon");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudaemon.ico");
    this.setCommand("daemon");
    this.setMaxItemsCount(1);
  }
}