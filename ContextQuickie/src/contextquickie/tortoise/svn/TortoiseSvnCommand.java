package contextquickie.tortoise.svn;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseCommand;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise SVN commands based on the passed
 *         parameters.
 */
public class TortoiseSvnCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseSvnCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_SVN);
  }
}
