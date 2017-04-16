package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise Git merge command.
 */
public class TortoiseGitMergeCommand extends AbstractTortoiseMergeCommand
{
  @Override
  protected final String getMergeCommandPathName()
  {
    return PreferenceConstants.TORTOISE_GIT.getMergePath();
  }
}
