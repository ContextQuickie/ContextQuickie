package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.AbstractTortoiseWorkingCopyDetect;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a SVN working copy is present or not.
 *         It is used to show/hide the Tortoise SVN context menu entries.
 */
public class TortoiseSvnWorkingCopyDetect extends AbstractTortoiseWorkingCopyDetect
{
  /**
   * Default constructor.
   */
  public TortoiseSvnWorkingCopyDetect()
  {
    super(PreferenceConstants.TORTOISE_SVN);
  }
}
