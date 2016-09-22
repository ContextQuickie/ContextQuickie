package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseDiffTwoFilesCommand;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseGitDiffTwoFilesCommand extends TortoiseDiffTwoFilesCommand {

  @Override
  protected String getCommandPathName() {
    return PreferenceConstants.P_TORTOISE_GIT_PATH;
  }
}
