package contextquickie.tortoise.git.entries;

import java.io.File;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.TortoiseEnvironment;

public abstract class AbstractTortoiseGitBisectEntry extends AbstractTortoiseGitEntry
{
  /**
   * A value indicating whether bisect must be active or not to show the menu entry.
   */
  private boolean bisectRequired = true;
  
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0008000000000000L;

  /**
   * Constructor.
   * 
   * @param menuTextIdentifier
   *          The identifier for the menu text.
   * 
   * @param defaultLabel
   *          The default value for the menu text.
   */
  protected AbstractTortoiseGitBisectEntry(final int menuTextIdentifier, final String defaultLabel)
  {
    super(menuTextIdentifier, defaultLabel);
    this.setMenuId(MenuIdentifier);
    this.setCommand("bisect");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
  
  protected boolean bisectActive(final TortoiseEnvironment environment)
  {
    final String workingCopyRoot = environment.getWorkingCopyRoot();
    if (workingCopyRoot != null)
    {
      File gitDirectory = new File(workingCopyRoot);
      if (gitDirectory.exists() && gitDirectory.isDirectory())
      {
        File bisectStartFile = new File(gitDirectory, "BISECT_START");
        if (bisectStartFile.exists() && bisectStartFile.isFile())
        {
          return true;
        }
      }
    }

    return false;
  }
  
  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    boolean isVisible = false;
    if (super.isVisible(environment) == true) 
    {
      if (TortoiseEnvironment.class.isInstance(environment))
      {
        if (this.bisectActive(TortoiseEnvironment.class.cast(environment)) == this.bisectRequired)
        {
          isVisible = true;
        }
      }
    }
    
    return isVisible;
  }

  /**
   * Sets a value indicating whether bisect must be active or not to show the menu entry.
   */
  public void setBisectRequired(boolean bisectRequired)
  {
    this.bisectRequired = bisectRequired;
  }
}
