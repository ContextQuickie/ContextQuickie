package contextquickie.preferences.controls;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class ConditionalFileFieldEditor extends FileFieldEditor {

  private BooleanFieldEditor activationControl;

  /**
   * Creates a file field editor.
   *
   * @param name
   *          the name of the preference this field editor works on
   * @param labelText
   *          the label text of the field editor
   * @param parent
   *          the parent of the field editor's control
   * @param parentActivationControl
   *          The parent control to enable/disable this feature.
   */
  public ConditionalFileFieldEditor(String name, String labelText, Composite parent,
      BooleanFieldEditor parentActivationControl) {
    super(name, labelText, false, parent);
    this.activationControl = parentActivationControl;
  }

  @Override
  protected boolean checkState() {
    if (this.activationControl.getBooleanValue() == false) {
      return true;
    } else {
      return super.checkState();
    }
  }
}
