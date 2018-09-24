package contextquickie.tortoise.svn;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseDiffTwoFilesCommand;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise SVN diff command with two selected files.
 */
public class TortoiseSvnDiffTwoFilesCommand extends AbstractTortoiseDiffTwoFilesCommand
{
  /**
   * Default constructor.
   */
  public TortoiseSvnDiffTwoFilesCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_SVN);
  }
}
