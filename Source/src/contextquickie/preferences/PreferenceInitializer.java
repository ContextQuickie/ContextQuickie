package contextquickie.preferences;

import contextquickie.Activator;
import contextquickie.tools.Registry;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{
  @Override
  public final void initializeDefaultPreferences()
  {
    final IPreferenceStore store = Activator.getDefault().getPreferenceStore();

    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_ENABLED, false);

    // Try to retrieve the path to the Beyond Compare executable.
    String beyondComparePath = Registry.readKey("HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare", "ExePath");
    if (beyondComparePath == null)
    {
      beyondComparePath = "C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe";
    }

    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_PATH, beyondComparePath);
    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH,
        "HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx");
    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, "SavedLeft");

    store.setDefault(PreferenceConstants.TORTOISE_SVN.getEnabled(), false);
    store.setDefault(PreferenceConstants.TORTOISE_SVN.getWorkingCopyDetection(), false);
    store.setDefault(PreferenceConstants.TORTOISE_SVN.getUseMenuConfigFromRegistry(), false);
    store.setDefault(PreferenceConstants.TORTOISE_SVN.getScanForLinkedResources(), false);

    final String tortoiseProcPathKey = "ProcPath";
    final String tortoiseMergePathKey = "TMergePath";

    // Try to retrieve the path to the Tortoise SVN executable.
    final String tortoiseSvnRegPath = "HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseSVN";
    this.getStoreConfigurationItemFromRegistry(store, tortoiseSvnRegPath, tortoiseProcPathKey,
        "C:\\Program Files\\TortoiseSVN\\bin\\TortoiseProc.exe", PreferenceConstants.TORTOISE_SVN.getPath());

    // Try to retrieve the path to the Tortoise SVN merge executable.
    this.getStoreConfigurationItemFromRegistry(store, tortoiseSvnRegPath, tortoiseMergePathKey,
        "C:\\Program Files\\TortoiseSVN\\bin\\TortoiseMerge.exe", PreferenceConstants.TORTOISE_SVN.getMergePath());

    store.setDefault(PreferenceConstants.TORTOISE_GIT.getEnabled(), false);
    store.setDefault(PreferenceConstants.TORTOISE_GIT.getWorkingCopyDetection(), false);
    store.setDefault(PreferenceConstants.TORTOISE_GIT.getUseMenuConfigFromRegistry(), false);
    store.setDefault(PreferenceConstants.TORTOISE_GIT.getScanForLinkedResources(), false);

    // Try to retrieve the path to the Tortoise GIT executable.
    final String tortoiseGitRegPath = "HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseGit";
    this.getStoreConfigurationItemFromRegistry(store, tortoiseGitRegPath, tortoiseProcPathKey,
        "C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitProc.exe", PreferenceConstants.TORTOISE_GIT.getPath());

    // Try to retrieve the path to the Tortoise GIT merge executable.
    this.getStoreConfigurationItemFromRegistry(store, tortoiseGitRegPath, tortoiseMergePathKey,
        "C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitMerge.exe", PreferenceConstants.TORTOISE_GIT.getMergePath());

  }

  /**
   * Reads configuration items from the registry and stores it to the passed
   * preference store. If the configuration item cannot be read, the default
   * value will be used.
   * 
   * @param store
   *          The preference store which is used to store the value.
   * @param registryLocation
   *          The location in the registry.
   * @param registryKey
   *          The key in the registry.
   * @param defaultValue
   *          The default value which is used if the configuration item cannot
   *          be read.
   * @param configurationItem
   *          The name of the parameter in the preference store.
   */
  private void getStoreConfigurationItemFromRegistry(final IPreferenceStore store, final String registryLocation, final String registryKey,
      final String defaultValue, final String configurationItem)
  {
    // Try to retrieve the path to the Tortoise SVN executable.
    String registryValue = Registry.readKey(registryLocation, registryKey);
    if (registryValue == null)
    {
      registryValue = defaultValue;
    }
    store.setDefault(configurationItem, registryValue);
  }
}
