package contextquickie.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ContextQuickie
 * 
 *         Class for accessing the registry using the "reg" command.
 *
 */
public final class Registry
{
  /**
   * Prevents from creating instances.
   */
  private Registry()
  {
  }

  /**
   * Reads a value from the registry.
   * 
   * @param location
   *          The location of the registry entry.
   * @param key
   *          The key of the registry entry.
   * @return The read value.
   */
  public static String readKey(final String location, final String key)
  {

    String value = null;
    Process p = null;
    try
    {
      p = Runtime.getRuntime().exec("reg query " + '"' + location + "\" /v " + key);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (p != null)
    {
      final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      /**
       * Regular expression for parsing the output of the query " +": One or
       * more spaces "[A-Z_]+": one of REG_SZ, REG_MULTI_SZ, REG_EXPAND_SZ,
       * REG_DWORD, REG_QWORD, REG_BINARY, REG_NONE "(.*)": The queried value
       */
      final String regexOneOrMoreSpaces = " +";
      final Pattern queryPattern = Pattern.compile(
          regexOneOrMoreSpaces + key + regexOneOrMoreSpaces + "[A-Z_]+" + regexOneOrMoreSpaces + "(.*)" + "$");
      try
      {
        while ((line = reader.readLine()) != null)
        {
          final Matcher matcher = queryPattern.matcher(line);
          if (matcher.matches())
          {
            value = matcher.group(1);
          }
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return value;
  }

  /**
   * Writes a value to the registry.
   * 
   * @param location
   *          The location of the registry entry.
   * @param key
   *          The key of the registry entry.
   * @param value
   *          The value to write.
   */
  public static void writeKey(final String location, final String key, final String value)
  {
    new ProcessWrapper().executeCommand("reg", "add", location, "/v", key, "/d", value, "/f");
  }

}
