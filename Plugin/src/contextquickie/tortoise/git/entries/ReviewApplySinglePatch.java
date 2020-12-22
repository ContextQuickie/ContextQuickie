package contextquickie.tortoise.git.entries;

public class ReviewApplySinglePatch extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000002000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 207;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ReviewApplySinglePatch(String iconPath)
  {
    super(MenuTextIdentifier, "Review/apply single patch..");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menupatch.ico");
    this.setCommand("");
  }
}