package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseGitMergeCommand extends TortoiseMergeCommand {

  @Override
  protected String getMergeCommandPathName() {
    return PreferenceConstants.P_TORTOISE_GIT_MERGE_PATH;
  }
}
