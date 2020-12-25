package contextquickie.tortoise.git.entries;

public class Lfs extends AbstractTortoiseGitLfsEntry
{
  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 370;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Lfs(String iconPath)
  {
    super(MenuTextIdentifier, "LFS");
    this.setIconPath(iconPath + "menulfs.png");
  }
}
