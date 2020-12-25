package contextquickie.tortoise.git.entries;

public class LfsUnlock extends AbstractTortoiseGitLfsEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 175;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public LfsUnlock(String iconPath)
  {
    super(MenuTextIdentifier, "Unlock");
    this.setIconPath(iconPath + "menuunlock.png");
    this.setCommand("lfsunlock");
  }
}
