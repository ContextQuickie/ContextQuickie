package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise Git is enabled in the
 *         settings. It is used to show/hide the Tortoise Git context menu
 *         entries.
 */
public class TortoiseGitEnabled extends AbstractTortoiseEnabled
{
  /**
   * Default constructor.
   */
  public TortoiseGitEnabled()
  {
    super(PreferenceConstants.TORTOISE_GIT);
  }
}
