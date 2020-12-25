package contextquickie.tortoise.git.entries;

public class BisectGood extends AbstractTortoiseGitBisectEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 137;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectGood(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect good");
    this.setIconPath(iconPath + "thumb_up" + iconExtension);
    this.setParameter1("/good");
  }
}
