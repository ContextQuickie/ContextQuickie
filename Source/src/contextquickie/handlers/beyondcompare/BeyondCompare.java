package contextquickie.handlers.beyondcompare;

import java.io.IOException;

import contextquickie.tools.Registry;

public class BeyondCompare {
	private static final String savedLeftKey = "SavedLeft";
	private static final String savedLeftPath = "HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx";
	private static final String savedLeftFile = "F";
	private static final String savedLeftDirectory = "D";
	
	private String _savedLeft = null;
	private BeyondCompareSavedLeft _savedLeftType = BeyondCompareSavedLeft.None;
	
	public void readRegistry() {
		String savedLeft = Registry.ReadKey(savedLeftPath, savedLeftKey);
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
		if (this._savedLeftType == BeyondCompareSavedLeft.File) {
			Registry.WriteKey(savedLeftPath, savedLeftKey, savedLeftFile + this._savedLeft);
		}
		else if (this._savedLeftType == BeyondCompareSavedLeft.Directory) {
			Registry.WriteKey(savedLeftPath, savedLeftKey, savedLeftDirectory + this._savedLeft);
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
		try {
			Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe\" " + left + " " + right);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
