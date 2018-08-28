package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise Hg merge command.
 */
public final class TortoiseHgMergeCommand extends AbstractTortoiseMergeCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgMergeCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_HG);
  }
}
