package contextquickie.tortoise.git.entries;

public class BisectBad extends AbstractTortoiseGitBisectEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 163;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectBad(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect bad");
    this.setIconPath(iconPath + "thumb_down" + iconExtension);
    this.setParameter1("/bad");
  }
}
