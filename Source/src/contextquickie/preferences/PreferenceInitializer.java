package contextquickie.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.tools.Registry;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_ENABLED, false);

		// Try to retrieve the path to the Beyond Compare executable.
		String beyondComparePath = Registry.ReadKey("HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare",
				"ExePath");
		if (beyondComparePath == null) {
			beyondComparePath = "C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe";
		}

		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_PATH, beyondComparePath);
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH,
				"HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx");
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, "SavedLeft");

		store.setDefault(PreferenceConstants.P_TORTOISE_SVN_ENABLED, false);
		store.setDefault(PreferenceConstants.P_TORTOISE_SVN_WORKING_COPY_DETECTION, false);

		// Try to retrieve the path to the Tortoise SVN executable.
		this.getStoreConfigurationItemFromRegistry(
				store,
				"HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseSVN", 
				"ProcPath",
				"C:\\Program Files\\TortoiseSVN\\bin\\TortoiseProc.exe",
				PreferenceConstants.P_TORTOISE_SVN_PATH);
		
		// Try to retrieve the path to the Tortoise SVN merge executable.
		this.getStoreConfigurationItemFromRegistry(
				store,
				"HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseSVN", 
				"TMergePath",
				"C:\\Program Files\\TortoiseSVN\\bin\\TortoiseMerge.exe",
				PreferenceConstants.P_TORTOISE_SVN_MERGE_PATH);
		
		store.setDefault(PreferenceConstants.P_TORTOISE_GIT_ENABLED, false);
		store.setDefault(PreferenceConstants.P_TORTOISE_GIT_WORKING_COPY_DETECTION, false);
		
		// Try to retrieve the path to the Tortoise GIT executable.
		this.getStoreConfigurationItemFromRegistry(
				store,
				"HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseGit", 
				"ProcPath",
				"C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitProc.exe",
				PreferenceConstants.P_TORTOISE_GIT_PATH);
		
		// Try to retrieve the path to the Tortoise GIT merge executable.
		this.getStoreConfigurationItemFromRegistry(
				store,
				"HKEY_LOCAL_MACHINE\\SOFTWARE\\TortoiseGit", 
				"TMergePath",
				"C:\\Program Files\\TortoiseGit\\bin\\TortoiseGitMerge.exe",
				PreferenceConstants.P_TORTOISE_GIT_MERGE_PATH);

	}
	
	private void getStoreConfigurationItemFromRegistry(final IPreferenceStore store, final String registryLocation, final String registryKey, final String defaultValue, final String configurationItem) {
		// Try to retrieve the path to the Tortoise SVN executable.
		String registryValue = Registry.ReadKey(registryLocation, registryKey);
		if (registryValue == null) {
			registryValue = defaultValue;
		}
		store.setDefault(configurationItem, registryValue);
	}

}
