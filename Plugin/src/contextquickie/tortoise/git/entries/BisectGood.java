package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class BisectGood extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0008000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectGood(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUBISECTGOOD, "Bisect good");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "thumb_up" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/good");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
