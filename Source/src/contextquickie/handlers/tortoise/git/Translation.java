package contextquickie.handlers.tortoise.git;

import java.io.File;

/**
 * @author ContextQuickie
 * 
 *         Class for retrieving translated menu entries from the Tortoise dlls.
 */
public final class Translation
{
  private static final String archDataModel = System.getProperty("sun.arch.data.model");

  static
  {   
    System.loadLibrary("ContextQuickie.native" + archDataModel);
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
  public String getTranslatedString(final String libraryDirectory, final String libraryBase, final long languageId, final long menuId, final String defaultValue)
  {
    final String libraryPath = libraryDirectory + File.pathSeparator + libraryBase + archDataModel + ".dll";
    return getTranslatedString(libraryPath, languageId, menuId, defaultValue);
  }
}
