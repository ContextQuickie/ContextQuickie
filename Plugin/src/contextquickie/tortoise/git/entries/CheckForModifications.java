package contextquickie.tortoise.git.entries;

public class CheckForModifications extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000100000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 192;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CheckForModifications(String iconPath)
  {
    super(MenuTextIdentifier, "Check for modifications");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menushowchanged.ico");
    this.setCommand("repostatus");
  }
}