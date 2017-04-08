package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseGitMergeCommand extends TortoiseMergeCommand
{
  @Override
  protected String getMergeCommandPathName()
  {
    return PreferenceConstants.TortoiseGit.getMergePath();
  }
}
