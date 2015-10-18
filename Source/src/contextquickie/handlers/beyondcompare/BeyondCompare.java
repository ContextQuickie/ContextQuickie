package contextquickie.handlers.beyondcompare;

import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.Registry;

public class BeyondCompare {
	private static final String savedLeftFile = "F";
	private static final String savedLeftDirectory = "D";
	
	private String _savedLeft = null;
	private BeyondCompareSavedLeft _savedLeftType = BeyondCompareSavedLeft.None;
	
	public void readRegistry() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
		String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
		String savedLeft = Registry.ReadKey(registryPath, registryKey);
		if (savedLeft != null) {
			if (savedLeft.startsWith(savedLeftFile)) {
				this._savedLeftType = BeyondCompareSavedLeft.File;
			}
			else if (savedLeft.startsWith(savedLeftDirectory)) {
				this._savedLeftType = BeyondCompareSavedLeft.Directory;
			}
			
			this._savedLeft = savedLeft.substring(1);
		}
		else {
			this._savedLeft = null;
			this._savedLeftType = BeyondCompareSavedLeft.None;
		}
	}
	
	public void writeRegistry() { 
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String registryKey = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY);
		String registryPath = preferenceStore.getString(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH);
		if (this._savedLeftType == BeyondCompareSavedLeft.File) {
			Registry.WriteKey(registryPath, registryKey, savedLeftFile + this._savedLeft);
		}
		else if (this._savedLeftType == BeyondCompareSavedLeft.Directory) {
			Registry.WriteKey(registryPath, registryKey, savedLeftDirectory + this._savedLeft);
		}
	}

	public String getSavedLeft() {
		return _savedLeft;
	}

	public void setSavedLeft(String savedLeft) {
		this._savedLeft = savedLeft;
	}

	public BeyondCompareSavedLeft getSavedLeftType() {
		return _savedLeftType;
	}

	public void setSavedLeftType(BeyondCompareSavedLeft savedLeftType) {
		this._savedLeftType = savedLeftType;
	}
	
	public static void Compare(String left, String right) {
		String pathToBeyondCompare = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_BEYOND_COMPARE_PATH);
		try {
			Runtime.getRuntime().exec('"' + pathToBeyondCompare + '"' + " " + left + " " + right);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
