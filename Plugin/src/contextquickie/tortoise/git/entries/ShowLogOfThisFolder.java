package contextquickie.tortoise.git.entries;

public class ShowLogOfThisFolder extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000100000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 211;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ShowLogOfThisFolder(String iconPath)
  {
    super(MenuTextIdentifier, "Show log of this folder");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulog.ico");
    this.setCommand("log");
    this.setParameter1("/submodule");
  }
}