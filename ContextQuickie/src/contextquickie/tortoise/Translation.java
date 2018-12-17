package contextquickie.tortoise;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.windows.Registry;

/**
 * @author ContextQuickie
 * 
 *         Class for retrieving translated menu entries from the Tortoise DLLs.
 */
public final class Translation
{
  private static final String archDataModel = System.getProperty("sun.arch.data.model");

  private final String libraryPath;
  
  private final long languageId;
  
  /**
   * Constructor
   * 
   * @param preferenceConstants
   *        The preference constants for this instance.
   */
  public Translation(TortoisePreferenceConstants preferenceConstants)
  {
    final String libraryBase = "TortoiseProc";
    String tempTortoiseLanguagesPath = null;
    
    Registry registry = new Registry();
    long languageId = registry.readIntValue(preferenceConstants.getRegistryUserDirectory(), "LanguageID", 0x409);
    
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    File tortoiseLanguagesPath = new File(preferenceStore.getString(preferenceConstants.getPath()));
    if (tortoiseLanguagesPath.exists() &&  tortoiseLanguagesPath.getParentFile() != null)
    {
      // Get the path to the directory which contains the executable 
      tortoiseLanguagesPath = tortoiseLanguagesPath.getParentFile();
      if (tortoiseLanguagesPath.exists() &&  tortoiseLanguagesPath.getParentFile() != null)
      {
        // Get path to the root directory of the Tortoise tool
        tortoiseLanguagesPath = new File(tortoiseLanguagesPath.getParentFile(), "Languages");
        if (archDataModel == "32")
        {
          tortoiseLanguagesPath = new File(tortoiseLanguagesPath, libraryBase + archDataModel + languageId + ".dll");
        }
        else
        {
          tortoiseLanguagesPath = new File(tortoiseLanguagesPath, libraryBase + languageId + ".dll");
        }
        
        if (tortoiseLanguagesPath.exists() && tortoiseLanguagesPath.isFile())
        {
          tempTortoiseLanguagesPath = tortoiseLanguagesPath.getAbsolutePath();
        }
      }
    }
    
    this.libraryPath = tempTortoiseLanguagesPath;
    this.languageId = languageId;
  }
  
  /**
   * @param libraryDirectory
   *        The path to the directory containing the translation data.
   * @param libraryBase
   *        The base name of the library. It will be extended by <libraryBase><architecture><languageId>.dll  
   * @param languageId
   *        The ID of the language.
   * @param menuId
   *        The ID of the menu entry.
   * @param defaultValue
   *        The default value which will be returned if no translation has been found.
   * @return
   *        The translated string.
   */
  public String getTranslatedString(final long menuId, final String defaultValue)
  {
    if (this.libraryPath != null)
    {
      return contextquickie.windows.Translation.getTranslatedString(this.libraryPath, this.languageId, menuId, defaultValue);
    }
    else
    {
      return defaultValue;
    }
  }
}
