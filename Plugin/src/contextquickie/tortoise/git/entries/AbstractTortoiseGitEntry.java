package contextquickie.tortoise.git.entries;

import java.io.File;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.Translation;

public abstract class AbstractTortoiseGitEntry extends TortoiseMenuEntry
{
  /**
   * The instance using for translating the menu entries.
   */
  protected static final Translation translation = new Translation(PreferenceConstants.TORTOISE_GIT);

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
    this.setLabel(translation.getTranslatedString(menuTextIdentifier, defaultLabel));
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);
  }

  protected boolean bisectActive(final TortoiseEnvironment environment)
  {
    final String workingCopyRoot = environment.getWorkingCopyRoot();
    if (workingCopyRoot != null)
    {
      File gitDirectory = new File(workingCopyRoot);
      if (gitDirectory.exists() && gitDirectory.isDirectory())
      {
        File bisectStartFile = new File(gitDirectory, "BISECT_START");
        if (bisectStartFile.exists() && bisectStartFile.isFile())
        {
          return true;
        }
      }
    }

    return false;
  }
  
  /**
   * Checks if the context menu entry is visible in the main menu.
   * 
   * @param entry
   *          The name of the entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  protected boolean isEntryBitSet(final long entry, final long lowRegistryEntry, long highRegistryEntry)
  {
    final long int32BitMaxValue = 0xFFFFFFFFL;
    final long compareValue;
    final long entryValue;

    if (entry > int32BitMaxValue)
    {
      entryValue = entry >> Integer.SIZE;
      compareValue = highRegistryEntry;
    }
    else
    {
      entryValue = entry;
      compareValue = lowRegistryEntry;
    }

    return (entryValue & compareValue) != 0;
  }
}
