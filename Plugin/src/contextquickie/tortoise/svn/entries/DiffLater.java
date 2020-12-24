package contextquickie.tortoise.svn.entries;

public class DiffLater extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000001000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 267;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffLater(String iconPath)
  {
    super(MenuTextIdentifier, "Diff later");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxFileCount(1);
    this.setMaxItemsCount(1);
  }

  @Override
  public void executeCommand()
  {
    this.executeDiffLaterCommand();
  }
}
