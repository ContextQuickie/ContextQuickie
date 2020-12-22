package contextquickie.tortoise.svn.entries;

public class Blame extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000800000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 203;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Blame(String iconPath)
  {
    super(MenuTextIdentifier, "Blame...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menublame.ico");
    this.setCommand("blame");
    this.setMaxFileCount(1);
    this.setMaxFolderCount(0);
  }
}