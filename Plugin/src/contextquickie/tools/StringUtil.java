package contextquickie.tools;

/**
 * Utility class for string manipulation.
 */
public final class StringUtil
{
  /**
   * Prevents from creating instances.
   */
  private StringUtil()
  {
  }

  /**
   * Quotes the string.
   * 
   * @param source
   *          The source string.
   * @return The quoted string.
   */
  public static String quoteString(final String source)
  {
    return '"' + source + '"';
  }
}
