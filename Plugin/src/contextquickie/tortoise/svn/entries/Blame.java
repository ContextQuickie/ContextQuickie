package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Blame extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000800000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Blame(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUBLAME, "Blame...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menublame.ico");
    this.setCommand("blame");
    this.setMaxFileCount(1);
    this.setMaxFolderCount(0);
  }
}