package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseMergeCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise SVN merge command.
 */
public final class TortoiseSvnMergeCommand extends AbstractTortoiseMergeCommand
{
  /**
   * Default constructor.
   */
  public TortoiseSvnMergeCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_SVN);
  }
}
