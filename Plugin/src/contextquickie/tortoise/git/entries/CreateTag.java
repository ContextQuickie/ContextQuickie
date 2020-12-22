package contextquickie.tortoise.git.entries;

public class CreateTag extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000004000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 307;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreateTag(String iconPath)
  {
    super(MenuTextIdentifier, "Create Tag...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "tag.ico");
    this.setCommand("tag");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}