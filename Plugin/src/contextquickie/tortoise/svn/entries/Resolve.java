package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Resolve extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000040;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Resolve(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENURESOLVE, "Resolve...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuresolve.ico");
    this.setCommand("resolve");
  }
}