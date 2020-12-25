package contextquickie.tortoise.git.entries;

public class BisectReset extends AbstractTortoiseGitBisectEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 171;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectReset(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect reset");
    this.setIconPath(iconPath + "menubisectreset" + iconExtension);
    this.setParameter1("/reset");
  }
}
