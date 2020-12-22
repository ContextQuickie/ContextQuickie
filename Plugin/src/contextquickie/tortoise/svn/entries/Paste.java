package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Paste extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000400000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Paste(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCLIPPASTE, "Paste");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuignore.ico");
    this.setCommand("pastecopy");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled