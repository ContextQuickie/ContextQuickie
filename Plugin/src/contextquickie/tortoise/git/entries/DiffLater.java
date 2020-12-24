package contextquickie.tortoise.git.entries;

public class DiffLater extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 232;

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
