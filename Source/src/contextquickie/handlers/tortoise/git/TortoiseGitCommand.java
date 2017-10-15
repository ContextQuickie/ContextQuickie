package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IPath;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Git commands based on the passed
 *         parameters.
 */
public class TortoiseGitCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseGitCommand()
  {
    super(PreferenceConstants.TORTOISE_GIT);
  }

  @Override
  protected final String getCommandIdName()
  {
    return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.CommandID";
  }

  @Override
  protected final String getRequiresPathName()
  {
    return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.RequiresPath";
  }

  @Override
  protected final String getParameter1Name()
  {
    return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.Parameter1";
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseGitWorkingCopyDetect().getWorkingCopyRoot(path);
  }
}
