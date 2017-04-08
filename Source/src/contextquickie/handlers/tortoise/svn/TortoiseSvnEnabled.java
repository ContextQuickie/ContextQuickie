package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.TortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise SVN is enabled in the
 *         settings. It is used to show/hide the Tortoise SVN context menu
 *         entries.
 */
public class TortoiseSvnEnabled extends TortoiseEnabled
{
  public TortoiseSvnEnabled()
  {
    super(PreferenceConstants.TortoiseSvn);
  }
}
