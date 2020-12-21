package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class DiffTwoCommits extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0004000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffTwoCommits(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUDIFFTWO, "Diff two commits");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("diffcommits");
    this.setMaxItemsCount(0); // Disabled. Didn't find any hint in TortoiseGit source how to handle this entry.
  }
}
