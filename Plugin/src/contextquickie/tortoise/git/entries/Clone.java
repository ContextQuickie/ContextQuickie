package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class Clone extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000002000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Clone(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCLONE, "Clone");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucheckout.ico");
    this.setCommand("clone");
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
    this.setMaxFolderCount(1);
    this.setMaxFileCount(0);
  }
}
