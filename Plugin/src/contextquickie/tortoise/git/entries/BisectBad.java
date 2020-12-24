package contextquickie.tortoise.git.entries;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.TortoiseEnvironment;

public class BisectBad extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0008000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 163;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public BisectBad(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Bisect bad");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "thumb_down" + iconExtension);
    this.setCommand("bisect");
    this.setParameter1("/bad");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
  
  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    if (super.isVisible(environment))
    {
      return this.bisectActive(TortoiseEnvironment.class.cast(environment));
    }
    
    return false;
  }
}