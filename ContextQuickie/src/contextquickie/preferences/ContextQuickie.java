package contextquickie.preferences;

import contextquickie.Activator;
import contextquickie.preferences.controls.ConditionalFileFieldEditor;
import contextquickie.preferences.controls.EmptyFieldEitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * </p>
 */

public class ContextQuickie extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, IPropertyChangeListener
{
  /**
   * Map which stores the relation between a field editor for enabling/disabling
   * a feature and the child controls for setting up the feature. The key is the
   * field editor for enabling/disabling the feature. The value is a list of
   * filed editors for setting up the feature.
   */
  private Map<BooleanFieldEditor, List<FieldEditor>> controlMapping = new HashMap<BooleanFieldEditor, List<FieldEditor>>();

  /**
   * Constructor.
   */
  public ContextQuickie()
  {
    super(GRID);
    setPreferenceStore(Activator.getDefault().getPreferenceStore());
  }

  /**
   * Creates the field editors. Field editors are abstractions of the common GUI
   * blocks needed to manipulate various types of preferences. Each field editor
   * knows how to save and restore itself.
   */
  public void createFieldEditors()
  {
    final BooleanFieldEditor showProgressForExternalTools = new BooleanFieldEditor(
        PreferenceConstants.SHOW_PROGRESS_FOR_EXTERNAL_TOOLS, 
        "Show progress when executing external tools",
        getFieldEditorParent());
    addField(showProgressForExternalTools);

    final BooleanFieldEditor refreshWorkspaceEditor = new BooleanFieldEditor(
        PreferenceConstants.REFRESH_WORKSPACE_AFTER_EXECUTION, 
        "Refresh workspace after executing external tools",
        getFieldEditorParent());
    addField(refreshWorkspaceEditor);
    
    this.createBeyondCompareFieldEditors();
    this.addField(new EmptyFieldEitor(this.getFieldEditorParent()));
    this.createTortoiseFieldEditors("SVN", "TortoiseProc.exe", "TortoiseMerge.exe", PreferenceConstants.TORTOISE_SVN);
    this.addField(new EmptyFieldEitor(this.getFieldEditorParent()));
    this.createTortoiseFieldEditors("Git", "TortoiseGitProc.exe", "TortoiseGitMerge.exe", PreferenceConstants.TORTOISE_GIT);
    
    this.addField(new EmptyFieldEitor(this.getFieldEditorParent()));
    this.createTortoiseFieldEditors("Hg", "thg.exe", null, PreferenceConstants.TORTOISE_HG);
    
    /*final String tortoiseHgExecutable = "thg.exe";
    this.addField(new EmptyFieldEitor(this.getFieldEditorParent()));
    this.createTortoiseFieldEditors("Hg", tortoiseHgExecutable, tortoiseHgExecutable, PreferenceConstants.TORTOISE_HG);
*/
    for (BooleanFieldEditor featureEnabledEditor : this.controlMapping.keySet())
    {
      featureEnabledEditor.setPropertyChangeListener(this);
      final boolean featureEnabled = this.getPreferenceStore().getBoolean(featureEnabledEditor.getPreferenceName());
      for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor))
      {
        fieldEditor.setPropertyChangeListener(this);
        fieldEditor.setEnabled(featureEnabled, getFieldEditorParent());
      }
    }
  }

  @Override
  public void init(final IWorkbench workbench)
  {
  }

  @Override
  public final void propertyChange(final PropertyChangeEvent event)
  {
    final Object eventSource = event.getSource();
    if (eventSource instanceof BooleanFieldEditor)
    {
      final BooleanFieldEditor featureEnabledEditor = (BooleanFieldEditor) eventSource;
      if (this.controlMapping.keySet().contains(featureEnabledEditor))
      {
        final boolean featureEnabled = featureEnabledEditor.getBooleanValue();
        for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor))
        {
          fieldEditor.setEnabled(featureEnabled, getFieldEditorParent());
        }
      }
    }

    // Updated the apply button in every case because the property change
    // can be caused by a changed string or path value
    this.updateApplyButton();
  }

  @Override
  public final boolean isValid()
  {
    // Search for enabled features wit invalid configuration options
    for (BooleanFieldEditor featureEnabledEditor : this.controlMapping.keySet())
    {
      if (featureEnabledEditor.getBooleanValue() == true)
      {
        for (FieldEditor fieldEditor : this.controlMapping.get(featureEnabledEditor))
        {
          if (fieldEditor.isValid() == false)
          {
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
  private void createBeyondCompareFieldEditors()
  {
    final ArrayList<FieldEditor> dependentFields = new ArrayList<FieldEditor>();

    final BooleanFieldEditor featureEnabledEditor = new BooleanFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_ENABLED, "Enable Beyond Compare",
        getFieldEditorParent());
    addField(featureEnabledEditor);

    this.controlMapping.put(featureEnabledEditor, dependentFields);

    final FileFieldEditor fileFieldEditor = new ConditionalFileFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_PATH, "Path to BCompare.exe",
        getFieldEditorParent(), featureEnabledEditor);
    fileFieldEditor.setFileExtensions(new String[]
    { "BCompare.exe" });
    addField(fileFieldEditor);
    dependentFields.add(fileFieldEditor);

    final StringFieldEditor shellRegPathEditor = new StringFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_PATH, "Left Side Registy Path",
        getFieldEditorParent());
    addField(shellRegPathEditor);
    dependentFields.add(shellRegPathEditor);

    final StringFieldEditor shellRegKeyEditor = new StringFieldEditor(PreferenceConstants.P_BEYOND_COMPARE_SHELL_REG_KEY, "Left Side Registy Key",
        getFieldEditorParent());
    addField(shellRegKeyEditor);
    dependentFields.add(shellRegKeyEditor);
  }

  /**
   * Creates the settings interface for a Tortoise feature.
   * 
   * @param name
   *          The name of the feature (e.g. SVN, Git)
   * @param execName
   *          The name of the executable of the feature.
   * @param mergeExeName
   *          The name of the merge executable of the feature.
   * @param preferenceConstants
   *          The preference constants describing the settings.
   */
  private void createTortoiseFieldEditors(final String name, final String execName, final String mergeExeName,
      final TortoisePreferenceConstants preferenceConstants)
  {
    FieldEditor dependentFieldEditor;
    final String showTortoise = "Show Tortoise ";
    
    final ArrayList<FieldEditor> dependentFields = new ArrayList<FieldEditor>();
    final BooleanFieldEditor featureEnabledEditor = new BooleanFieldEditor(preferenceConstants.getEnabled(), "Enable Tortoise " + name,
        getFieldEditorParent());
    addField(featureEnabledEditor);

    this.controlMapping.put(featureEnabledEditor, dependentFields);

    dependentFieldEditor = new BooleanFieldEditor(preferenceConstants.getScanForLinkedResources(), "Include linked files and folders",
        getFieldEditorParent());
    addField(dependentFieldEditor);
    dependentFields.add(dependentFieldEditor);

    dependentFieldEditor = new BooleanFieldEditor(preferenceConstants.getWorkingCopyDetection(),
        showTortoise + name + " only if a working copy has been found", getFieldEditorParent());
    addField(dependentFieldEditor);
    dependentFields.add(dependentFieldEditor);
    
    dependentFieldEditor = new BooleanFieldEditor(preferenceConstants.getUseMenuConfigFromRegistry(),
        showTortoise + name + " context menu entries based on settings in registry", getFieldEditorParent());
    addField(dependentFieldEditor);
    dependentFields.add(dependentFieldEditor);

    /* Parameter for setting the main executable */
    final String pathToDescriptionPrefix = "Path to ";
    FileFieldEditor fileFieldEditor = new ConditionalFileFieldEditor(
        preferenceConstants.getPath(), pathToDescriptionPrefix + execName, getFieldEditorParent(),
        featureEnabledEditor);
    fileFieldEditor.setFileExtensions(new String[]
    { execName });
    addField(fileFieldEditor);
    dependentFields.add(fileFieldEditor);

    if (mergeExeName != null)
    {
      /* Parameter for setting the merge executable */
      fileFieldEditor = new ConditionalFileFieldEditor(
          preferenceConstants.getMergePath(), pathToDescriptionPrefix + mergeExeName, getFieldEditorParent(),
          featureEnabledEditor);
      fileFieldEditor.setFileExtensions(new String[]
      { mergeExeName });
      addField(fileFieldEditor);
      dependentFields.add(fileFieldEditor);
    }

    // Editor for selecting the used version
    String[][] supportedVersions = new String[preferenceConstants.getSupportedVersions().length][2];
    for (int i = 0; i < preferenceConstants.getSupportedVersions().length; i++)
    {
      supportedVersions[i][0] = supportedVersions[i][1] = preferenceConstants.getSupportedVersions()[i];
    }
    ComboFieldEditor comboFieldEditor = new ComboFieldEditor(
        preferenceConstants.getUsedVersion(), 
        "Select Tortoise " + name + " version", supportedVersions, getFieldEditorParent());
    addField(comboFieldEditor);
    dependentFields.add(comboFieldEditor);
  }
}
