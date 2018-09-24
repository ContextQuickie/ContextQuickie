package contextquickie.tortoise.git;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseCommand;

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
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);
  }
}
