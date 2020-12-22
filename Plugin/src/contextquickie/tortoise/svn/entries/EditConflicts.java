package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class EditConflicts extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000040000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public EditConflicts(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCONFLICT, "Edit conflicts");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setCommand("conflicteditor");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled