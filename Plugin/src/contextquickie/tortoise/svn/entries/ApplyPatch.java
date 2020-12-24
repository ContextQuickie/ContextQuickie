package contextquickie.tortoise.svn.entries;

public class ApplyPatch extends AbstractTortoiseSvnEntry
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
  public ApplyPatch(String iconPath)
  {
    super(MenuTextIdentifier, "Apply patch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menupatch.ico");
    this.setMaxFileCount(0);
    this.setMaxFolderCount(1);
  }

  @Override
  public void executeCommand()
  {
    this.executeApplyPatchCommand();
  }
}
