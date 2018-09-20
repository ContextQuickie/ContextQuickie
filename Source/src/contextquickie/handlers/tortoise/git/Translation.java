package contextquickie.handlers.tortoise.git;

import java.io.File;

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
  
  static
  {   
    System.loadLibrary("ContextQuickie.native" + archDataModel);
  }
  
  /**
   * Constructor
   * 
   * @param tortoiseGitLanguagesPath
   *        The path to the tortoise Git directory containing the language DLLs.
   * @param libraryBase
   *        The base file name of the library containing the language information.
   * @param languageId
   *        The ID of the language.
   */
  public Translation(String tortoiseGitLanguagesPath, String libraryBase, long languageId)
  {
    if (archDataModel == "32")
    {
      libraryPath = tortoiseGitLanguagesPath + File.separator + libraryBase + archDataModel + languageId + ".dll";
    }
    else
    {
      libraryPath = tortoiseGitLanguagesPath + File.separator + libraryBase + languageId + ".dll";
    }
    
    this.languageId = languageId;
  }

  /**
   * @param library
   *        The path to the library containing the translation data. 
   * @param languageId
   *        The ID of the language.
   * @param menuId
   *        The ID of the menu entry.
   * @param defaultValue
   *        The default value which will be returned if no translation has been found.
   * @return
   *        The translated string.
   */
  private native String getTranslatedString(final String library, final long languageId, final long menuId, final String defaultValue);
  
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
    return getTranslatedString(this.libraryPath, this.languageId, menuId, defaultValue);
  }
}
