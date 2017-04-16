package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise SVN is enabled in the
 *         settings. It is used to show/hide the Tortoise SVN context menu
 *         entries.
 */
public class TortoiseSvnEnabled extends AbstractTortoiseEnabled
{
  /**
   * Default constructor.
   */
  public TortoiseSvnEnabled()
  {
    super(PreferenceConstants.TORTOISE_SVN);
  }
}
