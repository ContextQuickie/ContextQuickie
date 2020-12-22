package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class UnifiedDiff extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000004000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public UnifiedDiff(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUUNIDIFF, "Unified Diff");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("urldiff");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled