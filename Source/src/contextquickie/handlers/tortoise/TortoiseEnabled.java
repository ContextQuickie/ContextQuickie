package contextquickie.handlers.tortoise;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import contextquickie.Activator;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if the Tortoise add-in is enabled in the
 *         settings. It is used to show/hide the Tortoise add-in context menu
 *         entries.
 */
public abstract class TortoiseEnabled extends PropertyTester implements FileFilter {
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		boolean addinEnabled = preferenceStore.getBoolean(this.getPluginEnabledPreferenceName());
		boolean detectWorkingCopy = preferenceStore.getBoolean(this.getPluginWorkingCopyDetectionPreferenceName());

		if ((addinEnabled == true) && (detectWorkingCopy == true)) {

			// Disable add-in by default and search for a working copy
			// afterwards
			addinEnabled = false;

			List<?> selection = (List<?>) receiver;
			for (Object selectedItem : selection) {
				if (selectedItem instanceof IAdaptable) {
					IAdaptable adaptable = (IAdaptable) selectedItem;
					IResource resource = adaptable.getAdapter(IResource.class);

					// Get directory of currently selected item
					File currentDirectory = null;
					if ((resource.getType() == IResource.FOLDER) || (resource.getType() == IResource.PROJECT)) {
						currentDirectory = resource.getLocation().toFile();
					} else if (resource.getType() == IResource.FILE) {
						currentDirectory = resource.getLocation().toFile().getParentFile();
					}

					while ((currentDirectory != null) && (currentDirectory.isDirectory())) {
						File[] childItems = currentDirectory.listFiles(this);
						if ((childItems != null) && (childItems.length > 0)) {
							addinEnabled = true;
							break;
						}
						currentDirectory = currentDirectory.getParentFile();
					}
				}

				// Stop if at least one working copy has been found
				if (addinEnabled == true) {
					break;
				}
			}
		}

		return addinEnabled;
	}

	@Override
	public boolean accept(File dir) {
		return (dir.isDirectory() && this.getWokringCopyFolderName().equals(dir.getName()));
	}

	/**
	 * @return The name of the preference indicating whether the add-in is
	 *         active or not.
	 */
	protected abstract String getPluginEnabledPreferenceName();

	/**
	 * @return The name of the preference indicating whether a working copy
	 *         detection shall be performed or not.
	 */
	protected abstract String getPluginWorkingCopyDetectionPreferenceName();

	/**
	 * @return The name of the folder indicating a working copy.
	 */
	protected abstract String getWokringCopyFolderName();
}
