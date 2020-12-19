package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class ShowLog extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000020000;

  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public ShowLog(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENULOG, "Show log");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menulog.ico");
    this.setCommand("log");
    this.setMaxItemsCount(1);
  }
}
