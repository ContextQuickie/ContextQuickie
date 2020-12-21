package contextquickie.beyondcompare;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ProcessWrapper;
import rolandomagico.jniregistry.Registry;

/**
 * Class for accessing Beyond Compare registry settings and executing beyond
 * compare.
 *
 */
public class BeyondCompare
{
  /**
   * The prefix used in the registry to specify a file for the left side of
   * comparison.
   */
  private static final String SAVED_LEFT_FILE = "F";

  /**
   * The prefix used in the registry to specify a directory for the left side of
   * comparison.
   */
  private static final String SAVED_LEFT_DIRECTORY = "D";

  /**
   * The current registry value for the saved left side. The value will be read
   * by {@link readRegistry} and written by {@link writeRegistry}
   */
  private String savedLeft;

  /**
   * The current registry value for the type of the saved left side. The value
   * will be read by {@link readRegistry} and written by {@link writeRegistry}
   */
  private BeyondCompareSavedLeft savedLeftType = BeyondCompareSavedLeft.None;

  /**
   * Reads the current left side for comparison from the registry.
   */
  public void readRegistry()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
    final String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
    final String savedLeftFromRegistry = new Registry().readStringValue(registryPath, registryKey, null);
    if (savedLeftFromRegistry != null)
    {
      if (savedLeftFromRegistry.startsWith(SAVED_LEFT_FILE))
      {
        this.savedLeftType = BeyondCompareSavedLeft.File;
      }
      else if (savedLeftFromRegistry.startsWith(SAVED_LEFT_DIRECTORY))
      {
        this.savedLeftType = BeyondCompareSavedLeft.Directory;
      }

      this.savedLeft = savedLeftFromRegistry.substring(1);
    }
    else
    {
      this.savedLeft = null;
      this.savedLeftType = BeyondCompareSavedLeft.None;
    }
  }

  /**
   * Writes the current left side for comparison from the registry.
   */
  public void writeRegistry()
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
    final String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
    if (this.savedLeftType == BeyondCompareSavedLeft.File)
    {
      new Registry().writeKey(registryPath, registryKey, SAVED_LEFT_FILE + this.savedLeft);
    }
    else if (this.savedLeftType == BeyondCompareSavedLeft.Directory)
    {
      new Registry().writeKey(registryPath, registryKey, SAVED_LEFT_DIRECTORY + this.savedLeft);
    }
  }

  /**
   * Gets the current registry value for the saved left side.
   * @return The current registry value for the saved left side.
   */
  public String getSavedLeft()
  {
    return savedLeft;
  }

  /**
   * Sets the current registry value for the saved left side.
   * @param value
   *          The current registry value for the saved left side.
   */
  public void setSavedLeft(final String value)
  {
    this.savedLeft = value;
  }

  /**
   * Gets the current registry value for the type of the saved left side.
   * @return The current registry value for the type of the saved left side.
   */
  public BeyondCompareSavedLeft getSavedLeftType()
  {
    return savedLeftType;
  }

  /**
   * Sets the current registry value for the type of the saved left side.
   * @param value
   *          The current registry value for the type of the saved left side.
   */
  public void setSavedLeftType(final BeyondCompareSavedLeft value)
  {
    this.savedLeftType = value;
  }

  /**
   * Executes Beyond compare with the passed left and right side for comparison.
   * 
   * @param left
   *          The path of the left side for comparison.
   * @param right
   *          The path of the right side for comparison.
   */
  public static void compare(final String left, final String right)
  {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final String command = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_PATH);

    new ProcessWrapper().executeCommand(command, left, right);
  }
}
