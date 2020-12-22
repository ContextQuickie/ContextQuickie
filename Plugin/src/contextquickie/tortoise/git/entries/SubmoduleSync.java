package contextquickie.tortoise.git.entries;

public class SubmoduleSync extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0001000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 316;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SubmoduleSync(String iconPath)
  {
    super(MenuTextIdentifier, "Submodule Sync");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menusync.ico");
    this.setCommand("subsync");
  }
}