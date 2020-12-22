package contextquickie.tortoise.svn.entries;

public class CopyUrlToClipboard extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000008000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 280;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CopyUrlToClipboard(String iconPath)
  {
    super(MenuTextIdentifier, "Copy URL to clipboard");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "copy.ico");
    this.setCommand("copyurls");
  }
}