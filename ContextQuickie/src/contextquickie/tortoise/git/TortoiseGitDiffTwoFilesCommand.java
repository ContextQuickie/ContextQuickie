package contextquickie.tortoise.git;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseDiffTwoFilesCommand;

/**
 * @author ContextQuickie
 *
 *     Class for execute a TortoiseGit diff command with two selected files.
 */
public class TortoiseGitDiffTwoFilesCommand extends AbstractTortoiseDiffTwoFilesCommand
{
  /**
   * Default constructor.
   */
  public TortoiseGitDiffTwoFilesCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);
  }
}
