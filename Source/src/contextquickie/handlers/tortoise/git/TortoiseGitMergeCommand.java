package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise Git merge command.
 */
public class TortoiseGitMergeCommand extends AbstractTortoiseMergeCommand
{
  /**
   * Default constructor.
   */
  public TortoiseGitMergeCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);
  }
}
