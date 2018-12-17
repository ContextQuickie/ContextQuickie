package contextquickie.preferences;

import contextquickie.Activator;
import contextquickie.windows.Registry;

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
    String beyondComparePath = new Registry().readStringValue("HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare", "ExePath", null);
    if (beyondComparePath == null)
    {
      beyondComparePath = "C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe";
    }

    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_PATH, beyondComparePath);
    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH,
        "HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx");
    store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, "SavedLeft");

    this.initializeTortoiseSettings(
        store, 
        PreferenceConstants.TORTOISE_SVN, 
        "HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseSVN",
        "C:\\Program Files\\TortoiseSVN\\bin\\TortoiseProc.exe",
        "C:\\Program Files\\TortoiseSVN\\bin\\TortoiseMerge.exe");

    this.initializeTortoiseSettings(
        store, 
        PreferenceConstants.TORTOISE_GIT,
        "HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseGit",
        "C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitProc.exe",
        "C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitMerge.exe");
    
    this.initializeTortoiseSettings(
        store, 
        PreferenceConstants.TORTOISE_HG,
        "HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseHg",
        "C:\\\\Program Files\\\\TortoiseHg\\\\thg.exe",
        "C:\\\\Program Files\\\\TortoiseHg\\\\thg.exe");
    
    store.setDefault(PreferenceConstants.REFRESH_WORKSPACE_AFTER_EXECUTION, false);
    
    store.setDefault(PreferenceConstants.SHOW_PROGRESS_FOR_EXTERNAL_TOOLS, false);
  }

  private void initializeTortoiseSettings(final IPreferenceStore store, final TortoisePreferenceConstants tortoisePreferenceConstants, final String tortioseToolRegistryPath, final String tortoiseProcDefault, final String tortoiseMergeDefault)
  {
    store.setDefault(tortoisePreferenceConstants.getEnabled(), false);
    store.setDefault(tortoisePreferenceConstants.getWorkingCopyDetection(), false);
    store.setDefault(tortoisePreferenceConstants.getUseMenuConfigFromRegistry(), false);
    store.setDefault(tortoisePreferenceConstants.getScanForLinkedResources(), false);

    final String tortoiseProcPathKey = "ProcPath";
    final String tortoiseMergePathKey = "TMergePath";
    this.getStoreConfigurationItemFromRegistry(store, tortioseToolRegistryPath, tortoiseProcPathKey,
        tortoiseProcDefault, tortoisePreferenceConstants.getPath());
    this.getStoreConfigurationItemFromRegistry(store, tortioseToolRegistryPath, tortoiseMergePathKey,
        tortoiseMergeDefault, tortoisePreferenceConstants.getMergePath());

    // Try to retrieve the current tortoise version
    String[] supportedVersions = tortoisePreferenceConstants.getSupportedVersions();
    String tortoiseVersion = new Registry().readStringValue(
        tortoisePreferenceConstants.getRegistryUserDirectory(), 
        "CurrentVersion", 
        supportedVersions[0]);
    for (int i = 0; i < supportedVersions.length; i++)
    {
      if (tortoiseVersion.startsWith(supportedVersions[i]))
      {
        store.setDefault(tortoisePreferenceConstants.getUsedVersion(), supportedVersions[i]);
      }
    }
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
    String registryValue = new Registry().readStringValue(registryLocation, registryKey, defaultValue);
    store.setDefault(configurationItem, registryValue);
  }
}
