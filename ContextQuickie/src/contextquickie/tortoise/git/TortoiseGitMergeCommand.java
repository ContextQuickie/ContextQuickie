package contextquickie.tortoise.git;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMergeCommand;

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
