package contextquickie.tortoise.git.entries;

import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.git.MenuTextIdentifier;

public class BisectSkip extends AbstractTortoiseGitEntry
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
  public BisectSkip(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUBISECTSKIP, "Bisect skip");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menubisect" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/skip");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
  
  @Override
  public boolean isVisible(TortoiseEnvironment environment)
  {
    return ((super.isVisible(environment) == true) && (this.bisectActive(environment) == true));
  }
}
