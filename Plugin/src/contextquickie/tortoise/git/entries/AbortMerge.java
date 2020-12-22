package contextquickie.tortoise.git.entries;

public class AbortMerge extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000001000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 129;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public AbortMerge(String iconPath)
  {
    super(MenuTextIdentifier, "Abort Merge");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menumergeabort.ico");
    this.setCommand("merge");
    this.setParameter1("/abort");
  }
}