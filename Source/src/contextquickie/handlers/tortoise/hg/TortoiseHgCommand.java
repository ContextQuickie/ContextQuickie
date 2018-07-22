package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.handlers.tortoise.TortoiseWorkingCopyDetect;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IPath;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Hg commands based on the passed
 *         parameters.
 */
public class TortoiseHgCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgCommand()
  {
    super(PreferenceConstants.TORTOISE_HG);
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseWorkingCopyDetect().getWorkingCopyRoot(path, ".hg");
  }
}
