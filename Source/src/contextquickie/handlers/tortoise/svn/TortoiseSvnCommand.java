package contextquickie.handlers.tortoise.svn;

import org.eclipse.core.runtime.IPath;

import contextquickie.handlers.tortoise.TortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise SVN commands based on the passed
 *         parameters.
 */
public class TortoiseSvnCommand extends TortoiseCommand
{
  public TortoiseSvnCommand()
  {
    super(PreferenceConstants.TortoiseSvn);
  }

  @Override
  protected String getCommandIdName()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.CommandID";
  }

  @Override
  protected String getRequiresPathName()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.RequiresPath";
  }

  @Override
  protected String getParameter1Name()
  {
    return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.Parameter1";
  }

  @Override
  protected String getWorkingCopyRoot(IPath path)
  {
    return new TortoiseSvnEnabled().getWorkingCopyRoot(path);
  }
}
