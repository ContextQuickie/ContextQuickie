package contextquickie.tortoise.git.entries;

public class Diff extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000010000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 176;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Diff(String iconPath)
  {
    super(MenuTextIdentifier, "Diff");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("diff");
    this.setMaxItemsCount(1);
  }
}
