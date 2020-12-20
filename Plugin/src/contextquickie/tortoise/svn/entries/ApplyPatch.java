package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class ApplyPatch extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000002000000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ApplyPatch(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUAPPLYPATCH, "Apply patch...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucheckout.ico");
    this.setMaxFileCount(0);
    this.setMaxFolderCount(1);
  }

  @Override
  public void executeCommand()
  {
    // TODO Auto-generated method stub
    super.executeCommand();
  }
}
