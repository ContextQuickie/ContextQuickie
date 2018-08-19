package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IPath;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise SVN commands based on the passed
 *         parameters.
 */
public class TortoiseSvnCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseSvnCommand()
  {
    super(PreferenceConstants.TORTOISE_SVN);
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseSvnWorkingCopyDetect().getWorkingCopyRoot(path);
  }
}
