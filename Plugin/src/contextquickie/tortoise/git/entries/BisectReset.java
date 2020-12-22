package contextquickie.tortoise.git.entries;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.git.MenuTextIdentifier;

public class BisectReset extends AbstractTortoiseGitEntry
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
  public BisectReset(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier.IDS_MENUBISECTRESET, "Bisect reset");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menubisectreset" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/reset");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
  
  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    boolean isVisible = false;
    if (super.isVisible(environment) == true) 
    {
      if (this.bisectActive(TortoiseEnvironment.class.cast(environment)) == true)
      {
        isVisible = true;
      }
    }
    
    return isVisible;
  }
}
