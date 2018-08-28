package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseDiffTwoFilesCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise Hg diff command with two selected files.
 */
public class TortoiseHgDiffTwoFilesCommand extends AbstractTortoiseDiffTwoFilesCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgDiffTwoFilesCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_HG);
  }
}
