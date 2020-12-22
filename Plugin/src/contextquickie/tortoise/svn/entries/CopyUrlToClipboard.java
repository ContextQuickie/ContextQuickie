package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class CopyUrlToClipboard extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000008000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CopyUrlToClipboard(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCOPYURL, "Copy URL to clipboard");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "copy.ico");
    this.setCommand("copyurls");
  }
}