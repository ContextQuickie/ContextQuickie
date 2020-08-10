package contextquickie.tortoise.git.entries;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.Translation;

public abstract class AbstractTortoiseGitEntry extends TortoiseMenuEntry
{
  /**
   * The instance using for translating the menu entries.
   */
  private static final Translation translation = new Translation(PreferenceConstants.TORTOISE_GIT);

  /**
   * Constructor.
   * 
   * @param menuTextIdentifier
   *          The identifier for the menu text.
   * 
   * @param defaultLabel
   *          The default value for the menu text.
   */
  protected AbstractTortoiseGitEntry(final int menuTextIdentifier, final String defaultLabel)
  {
    this.setCommandId("ContextQuickie.commands.TortoiseGit.TortoiseGitCommand");
    this.setLabel(translation.getTranslatedString(menuTextIdentifier, defaultLabel));
  }
}
