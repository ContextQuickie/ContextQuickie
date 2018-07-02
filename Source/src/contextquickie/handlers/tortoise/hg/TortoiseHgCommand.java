package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IPath;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Hg commands based on the passed
 *         parameters.
 */
public class TortoiseHgCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgCommand()
  {
    super(PreferenceConstants.TORTOISE_HG);
  }

  @Override
  protected final String getCommandIdName()
  {
    return "ContextQuickie.commands.TortoiseHg.TortoiseHgCommand.CommandID";
  }

  @Override
  protected final String getRequiresPathName()
  {
    return "ContextQuickie.commands.TortoiseHg.TortoiseHgCommand.RequiresPath";
  }

  @Override
  protected final String getParameter1Name()
  {
    return "ContextQuickie.commands.TortoiseHg.TortoiseHgCommand.Parameter1";
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseHgWorkingCopyDetect().getWorkingCopyRoot(path);
  }

}
