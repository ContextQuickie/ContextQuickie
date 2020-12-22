package contextquickie.tortoise.git.entries;

public class SvnRebase extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000008000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 335;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SvnRebase(String iconPath)
  {
    super(MenuTextIdentifier, "SVN Rebase");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurebase.ico");
    this.setCommand("commit");
    this.setMaxItemsCount(0); // TODO: Disabled
  }
}
