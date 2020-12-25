package contextquickie.tortoise.git.entries;

public class BisectSkip extends AbstractTortoiseGitBisectEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 172;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectSkip(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect skip");
    this.setIconPath(iconPath + "menubisect" + iconExtension);
    this.setParameter1("/skip");
  }
}
