package contextquickie.tortoise.hg;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMergeCommand;

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
