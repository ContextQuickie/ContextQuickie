package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class MergeAll extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000100000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public MergeAll(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUMERGEALL, "Merge all..");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menumerge.ico");
    this.setCommand("mergeall");
  }
}