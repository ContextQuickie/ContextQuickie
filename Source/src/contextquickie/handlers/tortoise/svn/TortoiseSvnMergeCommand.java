package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise SVN merge command.
 */
public final class TortoiseSvnMergeCommand extends AbstractTortoiseMergeCommand
{
  @Override
  protected String getMergeCommandPathName()
  {
    return PreferenceConstants.TORTOISE_SVN.getMergePath();
  }
}
