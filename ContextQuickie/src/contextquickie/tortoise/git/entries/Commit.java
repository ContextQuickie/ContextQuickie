package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class Commit extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000004;
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Commit(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCOMMIT, "Commit...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucommit.ico");
    this.setCommand("commit");
  }
}
