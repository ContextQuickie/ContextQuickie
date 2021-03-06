package contextquickie.tortoise.git.entries;

public class ImportSvnIgnore extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000040000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 342;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ImportSvnIgnore(String iconPath)
  {
    super(MenuTextIdentifier, "Import SVN Ignore");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuignore.ico");
    this.setCommand("commit");
    this.setMaxItemsCount(0); // TODO: Disabled
  }
}
