package contextquickie.handlers.tortoise.hg;

import contextquickie.handlers.tortoise.AbstractTortoiseWorkingCopyDetect;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a Git working copy is present or not.
 *         It is used to show/hide the Tortoise Git context menu entries.
 */
public class TortoiseHgWorkingCopyDetect extends AbstractTortoiseWorkingCopyDetect
{
  /**
   * Default constructor.
   */
  public TortoiseHgWorkingCopyDetect()
  {
    super(PreferenceConstants.TORTOISE_HG);
  }
}

