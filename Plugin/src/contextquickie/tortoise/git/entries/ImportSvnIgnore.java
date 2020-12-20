package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class ImportSvnIgnore extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000040000000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ImportSvnIgnore(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSVNIGNORE, "Import SVN Ignore");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuignore.ico");
    this.setCommand("commit");
    this.setMaxItemsCount(0); // TODO: Disabled
  }
}
