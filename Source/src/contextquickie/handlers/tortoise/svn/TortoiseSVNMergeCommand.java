package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.TortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseSVNMergeCommand extends TortoiseMergeCommand {

  @Override
  protected String getMergeCommandPathName() {
    return PreferenceConstants.P_TORTOISE_SVN_MERGE_PATH;
  }
}
