package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class Settings extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x2000000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Settings(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSETTINGS, "Settings");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menusettings.ico");
    this.setCommand("settings");
    this.setEntryRequiresPath(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}
