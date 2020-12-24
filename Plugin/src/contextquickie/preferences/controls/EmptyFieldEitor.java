package contextquickie.preferences.controls;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * An empty filed editor which is used to created space between the settings.
 */
public class EmptyFieldEitor extends FieldEditor
{
  /**
   * Default constructor.
   * 
   * @param parent
   *          The parent UI element of this editor.
   */
  public EmptyFieldEitor(final Composite parent)
  {
    super("label", "", parent);
  }

  @Override
  protected void adjustForNumColumns(final int numColumns)
  {
  }

  @Override
  protected final void doFillIntoGrid(final Composite parent, final int numColumns)
  {
    getLabelControl(parent).setLayoutData(getLabelControl().getLayoutData());
  }

  @Override
  protected void doLoad()
  {
  }

  @Override
  protected void doLoadDefault()
  {
  }

  @Override
  protected void doStore()
  {
  }

  @Override
  public final int getNumberOfControls()
  {
    return 0;
  }
}
