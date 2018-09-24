package contextquickie.tortoise.hg;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseCommand;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Hg commands based on the passed
 *         parameters.
 */
public class TortoiseHgCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_HG);
  }
}
