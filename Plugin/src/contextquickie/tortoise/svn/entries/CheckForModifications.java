package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class CheckForModifications extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000100000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CheckForModifications(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUSHOWCHANGED, "Check for modifications");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menushowchanged.ico");
    this.setCommand("repostatus");
  }
}