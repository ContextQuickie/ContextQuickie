package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class DeleteKeepLocal extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000002000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DeleteKeepLocal(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUREMOVEKEEP, "Delete (keep local)");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudelete" + iconExtension);
    this.setCommand("remove");
    this.setParameter1("/keep");
  }
}