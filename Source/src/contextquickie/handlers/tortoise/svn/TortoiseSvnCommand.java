package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

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
    super(PreferenceConstants.TORTOISE_SVN);
  }
}
