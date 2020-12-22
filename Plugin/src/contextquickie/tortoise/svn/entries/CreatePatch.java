package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class CreatePatch extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000001000000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreatePatch(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCREATEPATCH, "Create patch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudiff.ico");
    this.setCommand("createpatch");
  }
}