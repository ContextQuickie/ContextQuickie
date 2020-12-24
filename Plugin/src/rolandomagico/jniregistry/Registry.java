/***********************************************************************************************************************
 MIT License

 Copyright(c) 2020 Roland Reinl

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files(the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and /or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions :

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
***********************************************************************************************************************/

package rolandomagico.jniregistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for accessing the windows registry.
 */
public final class Registry
{
  static
  {
    final String archDataModel = System.getProperty("sun.arch.data.model");
    System.loadLibrary("libraries/Registry.Java." + archDataModel);
  }

  private static final int HKEY_CLASSES_ROOT = 0x80000000;
  private static final int HKEY_CURRENT_CONFIG = 0x80000005;
  private static final int HKEY_CURRENT_USER = 0x80000001;
  private static final int HKEY_LOCAL_MACHINE = 0x80000002;
  private static final int HKEY_PERFORMANCE_DATA = 0x80000004;
  private static final int HKEY_PERFORMANCE_NLSTEXT = 0x80000060;
  private static final int HKEY_PERFORMANCE_TEXT = 0x80000050;
  private static final int HKEY_USERS = 0x80000003;

  private class RootMapping
  {
    public int root;
    public String location;
  }

  /**
   * Mapping from registry root elements as string to the corresponding HKEY
   * value.
   */
  private static final Map<String, Integer> rootMapping = new HashMap<String, Integer>();

  static
  {
    rootMapping.put("HKEY_CURRENT_USER\\", HKEY_CURRENT_USER);
    rootMapping.put("HKEY_LOCAL_MACHINE\\", HKEY_LOCAL_MACHINE);
  }

  private RootMapping mapRegistryRoot(String location)
  {
    RootMapping mapping = new RootMapping();
    mapping.root = HKEY_CURRENT_USER;
    for (String rootString : rootMapping.keySet())
    {
      if (location.startsWith(rootString))
      {
        mapping.root = rootMapping.get(rootString);
        mapping.location = location.substring(rootString.length());
      }
    }
    return mapping;
  }

  private native long readLongValue(final int hive, final String location, final String key, final long defaultValue);

  private native void writeStringValue(final int hive, final String location, final String key, final String value);

  /**
   * Reads a value from the registry.
   * 
   * @param location
   *          The location of the registry entry.
   * @param key
   *          The key of the registry entry.
   * @param defaultValue
   *          The default value which is returned in case of an error (e.g. the
   *          entry doesn't exist)
   * @return The read value.
   */
  public String readStringValue(String location, final String key, final String defaultValue)
  {
    RootMapping mapping = this.mapRegistryRoot(location);
    return readStringValue(mapping.root, mapping.location, key, defaultValue);
  }
  
  private native String readStringValue(
      final int hive, final String location, final String key, final String defaultValue);

  /**
   * Reads a value from the registry.
   * 
   * @param location
   *          The location of the registry entry.
   * @param key
   *          The key of the registry entry.
   * @param defaultValue
   *          The default value which is returned in case of an error (e.g. the
   *          entry doesn't exist)
   * @return The read value.
   */
  public long readIntValue(String location, final String key, long defaultValue)
  {
    RootMapping mapping = this.mapRegistryRoot(location);
    return readLongValue(mapping.root, mapping.location, key, defaultValue);
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
  public void writeKey(final String location, final String key, final String value)
  {
    RootMapping mapping = this.mapRegistryRoot(location);
    writeStringValue(mapping.root, mapping.location, key, value);
  }
}
