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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected final String getRequiresPathName()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected final String getParameter1Name()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected final String getWorkingCopyRoot(final IPath path)
  {
    // TODO Auto-generated method stub
    return null;
  }

}
