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
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_ENABLED, true);
		
		String beyondComparePath = Registry.ReadKey("HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare", "ExePath");
		if (beyondComparePath == null) {
			beyondComparePath = "C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe";
		}
		
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_PATH, beyondComparePath);
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH, "HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx");
		store.setDefault(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, "SavedLeft");
	}

}
