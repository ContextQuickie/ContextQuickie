package contextquickie.tortoise.git.entries;

public class BisectStart extends AbstractTortoiseGitBisectEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 136;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectStart(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect start");
    this.setIconPath(iconPath + "menubisect" + iconExtension);
    this.setParameter1("/start");
    this.setBisectRequired(false);
  }
}
