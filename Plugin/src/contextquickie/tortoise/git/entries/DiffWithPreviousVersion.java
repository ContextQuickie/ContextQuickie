package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class DiffWithPreviousVersion extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000200000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWithPreviousVersion(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUPREVDIFF, "Diff with previous version");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("diff");
    this.setParameter1("HEAD^");
  }
}
