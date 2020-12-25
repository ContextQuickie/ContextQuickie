package contextquickie.tortoise.git.entries;

public class LfsLock extends AbstractTortoiseGitLfsEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 174;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public LfsLock(String iconPath)
  {
    super(MenuTextIdentifier, "Lock");
    this.setIconPath(iconPath + "menulock.png");
    this.setCommand("lfslock");
  }
}
