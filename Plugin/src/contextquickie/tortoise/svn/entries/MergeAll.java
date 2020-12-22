package contextquickie.tortoise.svn.entries;

public class MergeAll extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000100000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 210;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public MergeAll(String iconPath)
  {
    super(MenuTextIdentifier, "Merge all..");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menumerge.ico");
    this.setCommand("mergeall");
  }
}