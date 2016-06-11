package contextquickie.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPreferencePage;

import contextquickie.Activator;

import org.eclipse.ui.IWorkbench;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class ContextQuickie extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage, IPropertyChangeListener {

	/**
	 * Map which stores the relation between a field editor for
	 * enabling/disabling a feature and the child controls for setting up the
	 * feature. The key is the field editor for enabling/disabling the feature.
	 * The value is a list of filed editors for setting up the feature.
	 */
	private Map<BooleanFieldEditor, List<FieldEditor>> controlMapping = new HashMap<BooleanFieldEditor, List<FieldEditor>>();

	/**
	 * Constructor
	 */
	public ContextQuickie() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		this.createBeyondCompareFieldEditors();
		this.createTortoiseFieldEditors("SVN", "TortoiseProc.exe", "TortoiseMerge.exe", PreferenceConstants.P_TORTOISE_SVN_ENABLED,
				PreferenceConstants.P_TORTOISE_SVN_PATH, PreferenceConstants.P_TORTOISE_SVN_MERGE_PATH, PreferenceConstants.P_TORTOISE_SVN_WORKING_COPY_DETECTION,
				PreferenceConstants.P_TORTOISE_SVN_USE_MENU_CONFIG_FROM_REGISTRY);

		this.createTortoiseFieldEditors("Git", "TortoiseGitProc.exe", "TortoiseGitMerge.exe", PreferenceConstants.P_TORTOISE_GIT_ENABLED,
				PreferenceConstants.P_TORTOISE_GIT_PATH, PreferenceConstants.P_TORTOISE_GIT_MERGE_PATH, PreferenceConstants.P_TORTOISE_GIT_WORKING_COPY_DETECTION,
        PreferenceConstants.P_TORTOISE_GIT_USE_MENU_CONFIG_FROM_REGISTRY);

		for (BooleanFieldEditor featureEnabledEditor : this.controlMapping.keySet()) {
			featureEnabledEditor.setPropertyChangeListener(this);
			boolean featureEnabled = this.getPreferenceStore().getBoolean(featureEnabledEditor.getPreferenceName());
			for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor)) {
				fieldEditor.setPropertyChangeListener(this);
				fieldEditor.setEnabled(featureEnabled, getFieldEditorParent());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange(org
	 * .eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Object eventSource = event.getSource();
		if (eventSource instanceof BooleanFieldEditor) {
			final BooleanFieldEditor featureEnabledEditor = (BooleanFieldEditor) eventSource;
			if (this.controlMapping.keySet().contains(featureEnabledEditor)) {
				boolean featureEnabled = featureEnabledEditor.getBooleanValue();
				for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor)) {
					fieldEditor.setEnabled(featureEnabled, getFieldEditorParent());
				}
			}
		}

		// Updated the apply button in every case because the property change
		// can be caused by a changed string or path value
		this.updateApplyButton();
	}

	@Override
	public boolean isValid() {
		// Search for enabled features wit invalid configuration options
		for (BooleanFieldEditor featureEnabledEditor : this.controlMapping.keySet()) {
			if (featureEnabledEditor.getBooleanValue() == true) {
				for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor)) {
					if (fieldEditor.isValid() == false) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Creates the field editors for Beyond Compare.
	 */
	private void createBeyondCompareFieldEditors() {
		ArrayList<FieldEditor> dependentFields = new ArrayList<FieldEditor>();

		BooleanFieldEditor featureEnabledEditor = new BooleanFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_ENABLED,
				"Enable Beyond Compare", getFieldEditorParent());
		addField(featureEnabledEditor);

		this.controlMapping.put(featureEnabledEditor, dependentFields);

		FileFieldEditor fileFieldEditor = new FileFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_PATH,
				"Path to BCompare.exe", getFieldEditorParent());
		fileFieldEditor.setFileExtensions(new String[] { "BCompare.exe" });
		addField(fileFieldEditor);
		dependentFields.add(fileFieldEditor);

		StringFieldEditor shellRegPathEditor = new StringFieldEditor(
				PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH, "Left Side Registy Path", getFieldEditorParent());
		addField(shellRegPathEditor);
		dependentFields.add(shellRegPathEditor);

		StringFieldEditor shellRegKeyEditor = new StringFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY,
				"Left Side Registy Key", getFieldEditorParent());
		addField(shellRegKeyEditor);
		dependentFields.add(shellRegKeyEditor);
	}

	/**
	 * Creates the settings interface for a Tortoise feature
	 * 
	 * @param name
	 *            The name of the feature (e.g. SVN, Git)
	 * @param execName
	 *            The name of the executable of the feature.
	 * @param mergeExecName
	 *            The name of the merge executable of the feature.
	 * @param enabledString
	 *            The preference constant describing the setting for
	 *            enabling/disabling the feature.
	 * @param execPathString
	 *            The preference constant describing the setting for the
	 *            executable path.
	 * @param mergeExecPathString
	 *            The preference constant describing the setting for the
	 *            merge executable path.
	 * @param wokringCopyDetectionString
	 *            The preference constant describing the setting for
	 *            enabling/disabling the working copy detection.
	 */
	private void createTortoiseFieldEditors(final String name, final String execName, final String mergeExeName, final String enabledString,
			final String execPathString, final String mergeExecPathString, final String wokringCopyDetectionString, final String useRegistryMenuSettingsString) {
		
		FileFieldEditor fileFieldEditor;
		FieldEditor dependentFieldEditor;
		
		ArrayList<FieldEditor> dependentFields = new ArrayList<FieldEditor>();
		BooleanFieldEditor featureEnabledEditor = new BooleanFieldEditor(enabledString, "Enable Tortoise " + name,
				getFieldEditorParent());
		addField(featureEnabledEditor);

		this.controlMapping.put(featureEnabledEditor, dependentFields);
		
		/* Parameter for setting the main executable */
		fileFieldEditor = new FileFieldEditor(execPathString, "Path to " + execName,
				getFieldEditorParent());
		fileFieldEditor.setFileExtensions(new String[] { execName });
		addField(fileFieldEditor);
		dependentFields.add(fileFieldEditor);
		
		/* Parameter for setting the merge executable */ 
		fileFieldEditor = new FileFieldEditor(mergeExecPathString, "Path to " + mergeExeName,
				getFieldEditorParent());
		fileFieldEditor.setFileExtensions(new String[] { mergeExeName });
		addField(fileFieldEditor);
		dependentFields.add(fileFieldEditor);

		dependentFieldEditor = new BooleanFieldEditor(wokringCopyDetectionString,
				"Show Tortoise " + name + " only if a working copy has been found", getFieldEditorParent());
		addField(dependentFieldEditor);
		dependentFields.add(dependentFieldEditor);
/* TODO: in progress
    dependentFieldEditor = new BooleanFieldEditor(useRegistryMenuSettingsString,
        "Show Tortoise " + name + " menu strucutre based on registry settings", getFieldEditorParent());
    addField(dependentFieldEditor);
    dependentFields.add(dependentFieldEditor);
	*/
	}
}