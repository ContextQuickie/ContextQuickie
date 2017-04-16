package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IPath;

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

  @Override
  protected final String getCommandIdName()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.CommandID";
  }

  @Override
  protected final String getRequiresPathName()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.RequiresPath";
  }

  @Override
  protected final String getParameter1Name()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.Parameter1";
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseSvnEnabled().getWorkingCopyRoot(path);
  }
}
