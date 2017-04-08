package contextquickie.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ContextQuickie
 * 
 *         Class for accessing the registry using the "reg" command
 *
 */
public class Registry
{

  /**
   * Reads a value from the registry.
   * 
   * @param location
   *          The location of the registry entry.
   * @param key
   *          The key of the registry entry.
   * @return The read value.
   */
  public static String ReadKey(String location, String key)
  {

    String value = null;
    Process p = null;
    try
    {
      p = Runtime.getRuntime().exec("reg query " + '"' + location + "\" /v " + key);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (p != null)
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      /**
       * Regular expression for parsing the output of the query " +": One or
       * more spaces "[A-Z_]+": one of REG_SZ, REG_MULTI_SZ, REG_EXPAND_SZ,
       * REG_DWORD, REG_QWORD, REG_BINARY, REG_NONE "(.*)": The queried value
       */
      Pattern queryPattern = Pattern.compile(" +" + key + " +" + "[A-Z_]+" + " +" + "(.*)" + "$");
      try
      {
        while ((line = reader.readLine()) != null)
        {
          Matcher matcher = queryPattern.matcher(line);
          if (matcher.matches())
          {
            value = matcher.group(1);
          }
        }
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
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
  public static void WriteKey(String location, String key, String value)
  {
    ProcessWrapper.executeCommand("reg", "add", location, "/v", key, "/d", value, "/f");
  }

}
