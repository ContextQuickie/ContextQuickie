package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise Git is enabled in the
 *         settings. It is used to show/hide the Tortoise Git context menu
 *         entries.
 */
public class TortoiseGitEnabled extends TortoiseEnabled
{
  public TortoiseGitEnabled()
  {
    super(PreferenceConstants.TortoiseGit);
  }
}
