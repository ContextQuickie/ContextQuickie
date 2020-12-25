package contextquickie.tortoise.git.entries;

public class LfsShowLockedFiles extends AbstractTortoiseGitLfsEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 184;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public LfsShowLockedFiles(String iconPath)
  {
    super(MenuTextIdentifier, "Show locked files");
    this.setIconPath(iconPath + "menurepobrowse.ico");
    this.setCommand("lfslocks");
  }
}
