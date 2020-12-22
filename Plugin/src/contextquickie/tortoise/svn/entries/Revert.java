package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Revert extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000010;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Revert(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUREVERT, "Revert...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurevert" + iconExtension);
    this.setCommand("revert");
  }
}