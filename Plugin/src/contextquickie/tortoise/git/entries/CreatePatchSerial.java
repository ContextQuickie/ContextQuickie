package contextquickie.tortoise.git.entries;

public class CreatePatchSerial extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000008000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 308;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreatePatchSerial(String iconPath)
  {
    super(MenuTextIdentifier, "Create Patch Serial...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudiff.ico");
    this.setCommand("formatpatch");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}