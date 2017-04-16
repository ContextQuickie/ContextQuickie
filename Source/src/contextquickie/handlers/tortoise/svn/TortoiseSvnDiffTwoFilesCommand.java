package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseDiffTwoFilesCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *     Class for execute a Tortoise SVN diff command with two selected files.
 */
public class TortoiseSvnDiffTwoFilesCommand extends AbstractTortoiseDiffTwoFilesCommand
{
  @Override
  protected final String getCommandPathName()
  {
    return PreferenceConstants.TORTOISE_SVN.getPath();
  }
}
