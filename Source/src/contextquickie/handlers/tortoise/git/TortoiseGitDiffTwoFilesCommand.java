package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseDiffTwoFilesCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *     Class for execute a Tortoise Git diff command with two selected files.
 */
public class TortoiseGitDiffTwoFilesCommand extends AbstractTortoiseDiffTwoFilesCommand
{
  @Override
  protected final String getCommandPathName()
  {
    return PreferenceConstants.TORTOISE_GIT.getPath();
  }
}
