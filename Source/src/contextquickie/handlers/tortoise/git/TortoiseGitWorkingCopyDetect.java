package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseWorkingCopyDetect;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a Git working copy is present or not.
 *         It is used to show/hide the Tortoise Git context menu entries.
 */
public class TortoiseGitWorkingCopyDetect extends AbstractTortoiseWorkingCopyDetect
{
  /**
   * Default constructor.
   */
  public TortoiseGitWorkingCopyDetect()
  {
    super(PreferenceConstants.TORTOISE_GIT);
  }
}
