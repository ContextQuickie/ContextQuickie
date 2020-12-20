package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.git.MenuTextIdentifier;

public class BisectStart extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0008000000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectStart(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUBISECTSTART, "Bisect start");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menubisect" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/start");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}
