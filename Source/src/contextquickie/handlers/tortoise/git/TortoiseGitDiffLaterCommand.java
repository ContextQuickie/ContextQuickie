package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseDiffLaterCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * Handler for the Tortoise Git "diff later" command.
 */
public class TortoiseGitDiffLaterCommand extends AbstractTortoiseDiffLaterCommand
{
  /**
   * Default constructor.
   */
  public TortoiseGitDiffLaterCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);
  }
}
