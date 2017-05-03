package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise Hg is enabled in the
 *         settings. It is used to show/hide the Tortoise Hg context menu
 *         entries.
 */
public class TortoiseHgEnabled extends AbstractTortoiseEnabled
{
  /**
   * Default constructor.
   */
  public TortoiseHgEnabled()
  {
    super(PreferenceConstants.TORTOISE_HG);
  }
}
