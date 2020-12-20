package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class SvnFetch extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0080000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SvnFetch(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSVNFETCH, "SVN Fetch");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "pull1.ico");
    this.setCommand("commit");
    this.setMaxItemsCount(0); // TODO: Disabled
  }
}
