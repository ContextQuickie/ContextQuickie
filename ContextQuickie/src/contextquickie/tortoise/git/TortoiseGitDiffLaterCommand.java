package contextquickie.tortoise.git;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseDiffLaterCommand;

/**
 * Handler for the TortoiseGit "diff later" command.
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
