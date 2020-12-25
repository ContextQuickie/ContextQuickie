package contextquickie.tortoise.git.entries;

import java.io.File;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.TortoiseEnvironment;

public abstract class AbstractTortoiseGitLfsEntry extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x1000000000000000L;

  /**
   * Constructor.
   * 
   * @param menuTextIdentifier
   *          The identifier for the menu text.
   * 
   * @param defaultLabel
   *          The default value for the menu text.
   */
  protected AbstractTortoiseGitLfsEntry(final int menuTextIdentifier, final String defaultLabel)
  {
    super(menuTextIdentifier, defaultLabel);
    this.setMenuId(MenuIdentifier);
  }

  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    boolean isVisible = false;
    if (super.isVisible(environment))
    {
      if (TortoiseEnvironment.class.isInstance(environment))
      {
        TortoiseEnvironment tortoiseEnvironment = TortoiseEnvironment.class.cast(environment);
        final String workingCopyRoot = tortoiseEnvironment.getWorkingCopyRoot();
        if (workingCopyRoot != null)
        {
          File gitDirectory = new File(workingCopyRoot);
          if (gitDirectory.exists() && gitDirectory.isDirectory())
          {
            File bisectStartFile = new File(gitDirectory, "lfs");
            if (bisectStartFile.exists() && bisectStartFile.isFile())
            {
              isVisible = true;
            }
          }
        }
      }
    }
    
    return isVisible;
  }
}
