package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Git commands based on the passed
 *         parameters.
 */
public class TortoiseGitCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseGitCommand()
  {
    super(PreferenceConstants.TORTOISE_GIT);
  }
}
