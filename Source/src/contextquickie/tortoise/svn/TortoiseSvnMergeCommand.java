package contextquickie.tortoise.svn;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMergeCommand;

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
