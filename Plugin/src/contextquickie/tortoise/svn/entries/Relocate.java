package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Relocate extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000080000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Relocate(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENURELOCATE, "Relocate...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurelocate.ico");
    this.setCommand("relocate");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled