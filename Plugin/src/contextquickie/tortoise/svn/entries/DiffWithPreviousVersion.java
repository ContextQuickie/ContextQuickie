package contextquickie.tortoise.svn.entries;

public class DiffWithPreviousVersion extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000200000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 214;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWithPreviousVersion(String iconPath)
  {
    super(MenuTextIdentifier, "Diff with previous version");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("prevdiff");
    this.setMaxFolderCount(0);
    this.setMaxFileCount(1);
    this.setSupportingLinkedResources(false);
  }
}