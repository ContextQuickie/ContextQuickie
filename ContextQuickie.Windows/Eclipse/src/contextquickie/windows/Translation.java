package contextquickie.windows;

public class Translation extends BaseLoader {
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
	  public native static String getTranslatedString(final String library, final long languageId, final long menuId, final String defaultValue);
}
