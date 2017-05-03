package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise Hg merge command.
 */
public final class TortoiseHgMergeCommand extends AbstractTortoiseMergeCommand
{
  @Override
  protected String getMergeCommandPathName()
  {
    return PreferenceConstants.TORTOISE_HG.getMergePath();
  }
}
