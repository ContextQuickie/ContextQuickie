package contextquickie.preferences.controls;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * Implementation of a file field editor where its checks can be enabled or disabled.
 */
public class ConditionalFileFieldEditor extends FileFieldEditor
{
  /**
   * The field editor whose value is used to enable or disable checks.
   */
  private BooleanFieldEditor currentActivationControl;

  /**
   * Creates a file field editor.
   *
   * @param name
   *          the name of the preference this field editor works on
   * @param labelText
   *          the label text of the field editor
   * @param parent
   *          the parent of the field editor's control
   * @param activationControl
   *          The parent control to enable/disable this feature.
   */
  public ConditionalFileFieldEditor(
      final String name, final String labelText, final Composite parent, final BooleanFieldEditor activationControl)
  {
    super(name, labelText, false, parent);
    this.currentActivationControl = activationControl;
  }

  @Override
  protected final boolean checkState()
  {
    if (this.currentActivationControl.getBooleanValue() == false)
    {
      return true;
    }
    
    return super.checkState();
  }
}
