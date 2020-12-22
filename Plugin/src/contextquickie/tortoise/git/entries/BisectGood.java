package contextquickie.tortoise.git.entries;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.TortoiseEnvironment;

public class BisectGood extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0008000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 137;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectGood(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect good");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "thumb_up" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/good");
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
