package contextquickie.tortoise.git.entries;

public class ApplyPatchSerial extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000010000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 309;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ApplyPatchSerial(String iconPath)
  {
    super(MenuTextIdentifier, "Apply Patch Serial...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menupatch.ico");
    this.setCommand("importpatch");
  }
}