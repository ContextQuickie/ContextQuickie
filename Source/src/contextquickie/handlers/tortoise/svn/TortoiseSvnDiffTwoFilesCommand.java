package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.TortoiseDiffTwoFilesCommand;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseSvnDiffTwoFilesCommand extends TortoiseDiffTwoFilesCommand {

  @Override
  protected String getCommandPathName() {
    return PreferenceConstants.P_TORTOISE_SVN_PATH;
  }

}