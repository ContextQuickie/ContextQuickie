package contextquickie.handlers.beyondcompare;

import org.eclipse.core.runtime.IPath;

import contextquickie.tools.Registry;

public class BaseBeyoncCompare {
	private static final String savedLeftKey = "SavedLeft";
	private static final String savedLeftPath = "HKEY_CURRENT_USER\\SOFTWARE\\Scooter Software\\Beyond Compare 4\\BcShellEx";
	private static final String savedLeftFile = "F";
	private static final String savedLeftDirectory = "D";
	
	public static BeyondCompareSavedLeft getSavedLeftType() {
		String savedLeft = null;
		savedLeft = Registry.ReadKey(savedLeftPath, savedLeftKey); 
		if (savedLeft == null) {
			return BeyondCompareSavedLeft.None;
		}
		
		if (savedLeft.startsWith(savedLeftFile)) {
			return BeyondCompareSavedLeft.File;
		}
		
		if (savedLeft.startsWith(savedLeftDirectory)) {
			return BeyondCompareSavedLeft.Directory;
		}
		
		return BeyondCompareSavedLeft.None;		
	}
	
	public static void setSafedLeft(BeyondCompareSavedLeft type, IPath path) {
		if (type == BeyondCompareSavedLeft.File) {
			Registry.WriteKey(savedLeftPath, savedLeftKey, savedLeftFile + path);
		}
		else if (type == BeyondCompareSavedLeft.Directory) {
			Registry.WriteKey(savedLeftPath, savedLeftKey, savedLeftDirectory + path);
		}
	}
	
	public static String getSavedLeft() {
		String savedLeft = null;
		savedLeft = Registry.ReadKey(savedLeftPath, savedLeftKey);
		if ((savedLeft != null) && (savedLeft.startsWith(savedLeftFile) || savedLeft.startsWith(savedLeftDirectory))) {
			savedLeft = savedLeft.substring(1);
		}
		else {
			savedLeft = null;
		}
		
		return savedLeft;
	}
}
