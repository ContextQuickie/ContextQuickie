package contextquickie.tortoise.git.entries;

public class BrowseReferences extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000080000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 333;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BrowseReferences(String iconPath)
  {
    super(MenuTextIdentifier, "Browse References");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurepobrowse.ico");
    this.setCommand("refbrowse");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}