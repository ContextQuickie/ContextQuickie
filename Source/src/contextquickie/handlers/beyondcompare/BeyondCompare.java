package contextquickie.handlers.beyondcompare;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.Registry;

/**
 * @author ContextQuickie
 * 
 *         Class for accessing Beyond Compare registry settings and executing
 *         beyond compare.
 *
 */
public class BeyondCompare {
	/**
	 * The prefix used in the registry to specify a file for the left side of
	 * comparison.
	 */
	private static final String savedLeftFile = "F";

	/**
	 * The prefix used in the registry to specify a directory for the left side
	 * of comparison.
	 */
	private static final String savedLeftDirectory = "D";

	/**
	 * The current registry value for the saved left side. The value will be
	 * read by {@link readRegistry} and written by {@link writeRegistry}
	 */
	private String _savedLeft = null;

	/**
	 * The current registry value for the type of the saved left side. The value
	 * will be read by {@link readRegistry} and written by {@link writeRegistry}
	 */
	private BeyondCompareSavedLeft _savedLeftType = BeyondCompareSavedLeft.None;

	/**
	 * Reads the current left side for comparison from the registry.
	 */
	public void readRegistry() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
		String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
		String savedLeft = Registry.ReadKey(registryPath, registryKey);
		if (savedLeft != null) {
			if (savedLeft.startsWith(savedLeftFile)) {
				this._savedLeftType = BeyondCompareSavedLeft.File;
			} else if (savedLeft.startsWith(savedLeftDirectory)) {
				this._savedLeftType = BeyondCompareSavedLeft.Directory;
			}

			this._savedLeft = savedLeft.substring(1);
		} else {
			this._savedLeft = null;
			this._savedLeftType = BeyondCompareSavedLeft.None;
		}
	}

	/**
	 * Writes the current left side for comparison from the registry.
	 */
	public void writeRegistry() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
		String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
		if (this._savedLeftType == BeyondCompareSavedLeft.File) {
			Registry.WriteKey(registryPath, registryKey, savedLeftFile + this._savedLeft);
		} else if (this._savedLeftType == BeyondCompareSavedLeft.Directory) {
			Registry.WriteKey(registryPath, registryKey, savedLeftDirectory + this._savedLeft);
		}
	}

	/**
	 * @return The current registry value for the saved left side.
	 */
	public String getSavedLeft() {
		return _savedLeft;
	}

	/**
	 * @param savedLeft
	 *            The current registry value for the saved left side.
	 */
	public void setSavedLeft(String savedLeft) {
		this._savedLeft = savedLeft;
	}

	/**
	 * @return The current registry value for the type of the saved left side.
	 */
	public BeyondCompareSavedLeft getSavedLeftType() {
		return _savedLeftType;
	}

	/**
	 * @param savedLeftType
	 *            The current registry value for the type of the saved left
	 *            side.
	 */
	public void setSavedLeftType(BeyondCompareSavedLeft savedLeftType) {
		this._savedLeftType = savedLeftType;
	}

	/**
	 * Executes Beyond compare with the passed left and right side for
	 * comparison.
	 * 
	 * @param left
	 *            The path of the left side for comparison.
	 * @param right
	 *            The path of the right side for comparison.
	 */
	public static void Compare(String left, String right) {
		String command = Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_BEYOND_COMPARE_PATH);
		
		ProcessWrapper.executeCommand(command, left, right);		
	}
}
