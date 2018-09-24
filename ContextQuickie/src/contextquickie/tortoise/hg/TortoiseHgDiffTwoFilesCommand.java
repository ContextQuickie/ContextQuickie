package contextquickie.tortoise.hg;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseDiffTwoFilesCommand;

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
