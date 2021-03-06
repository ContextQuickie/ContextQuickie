package contextquickie.tortoise.svn.entries;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.AbstractTortoiseMenuEntry;
import contextquickie.tortoise.Translation;

public abstract class AbstractTortoiseSvnEntry extends AbstractTortoiseMenuEntry
{
  /**
   * The instance using for translating the menu entries.
   */
  protected static final Translation translation = new Translation(PreferenceConstants.TORTOISE_SVN);
  
  /**
   * Constructor.
   * 
   * @param menuTextIdentifier
   *          The identifier for the menu text.
   * 
   * @param defaultLabel
   *          The default value for the menu text.
   */
  protected AbstractTortoiseSvnEntry(final int menuTextIdentifier, final String defaultLabel)
  {
    this.setLabel(translation.getTranslatedString(menuTextIdentifier, defaultLabel));
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_SVN);
  }
}
