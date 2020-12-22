package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class DiffWithPreviousVersion extends AbstractTortoiseSvnEntry
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
    this.setCommand("prevdiff");
    this.setMaxFolderCount(0);
    this.setMaxFileCount(1);
    this.setSupportingLinkedResources(false);
  }
}