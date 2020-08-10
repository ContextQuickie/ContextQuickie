package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class Push extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000001000000000L;
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Push(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUPUSH, "Push...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "Push.ico");
    this.setCommand("push");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
