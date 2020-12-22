package contextquickie.tortoise.git.entries;

public class SubmoduleUpdate extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000008000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 170;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SubmoduleUpdate(String iconPath)
  {
    super(MenuTextIdentifier, "Submodule Update...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "pull1.ico");
    this.setCommand("subupdate");
  }
}