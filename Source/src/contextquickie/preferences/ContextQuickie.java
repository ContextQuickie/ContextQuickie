package contextquickie.preferences;


import org.eclipse.jface.preference.*;
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

public class ContextQuickie extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ContextQuickie() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		//setDescription("Set up Context Quickie");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {	
		FileFieldEditor fileFiledEditor;
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.P_BEYOND_COMPARE_ENABLED, 
					"Enable Beyond Compare",
					getFieldEditorParent()));
		
		fileFiledEditor = new FileFieldEditor(
				PreferenceConstants.P_BEYOND_COMPARE_PATH,
				"Path to BCompare.exe", getFieldEditorParent());
		fileFiledEditor.setFileExtensions(new String[] { "BCompare.exe" });
		addField(fileFiledEditor);
		
		addField(
				new StringFieldEditor(
					PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH, 
					"Left Side Registy Path",
					getFieldEditorParent()));
		 
		addField(
				new StringFieldEditor(
					PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, 
					"Left Side Registy Key",
					getFieldEditorParent()));
		
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.P_TORTOISE_SVN_ENABLED, 
					"Enable Tortoise SVN",
					getFieldEditorParent()));
		
		fileFiledEditor = new FileFieldEditor(
				PreferenceConstants.P_TORTOISE_SVN_PATH,
				"Path to TortoiseProc.exe", getFieldEditorParent());
		fileFiledEditor.setFileExtensions(new String[] { "TortoiseProc.exe" });
		addField(fileFiledEditor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}