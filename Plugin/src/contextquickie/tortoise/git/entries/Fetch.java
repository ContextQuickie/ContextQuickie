package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class Fetch extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000040000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Fetch(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUFETCH, "Fetch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "pull1.ico");
    this.setCommand("fetch");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
