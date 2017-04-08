package contextquickie.preferences;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.widgets.Composite;

public class EmptyFieldEitor extends FieldEditor
{
  public EmptyFieldEitor(Composite parent)
  {
    super("label", "", parent);
  }

  @Override
  protected void adjustForNumColumns(int numColumns)
  {
  }

  @Override
  protected void doFillIntoGrid(Composite parent, int numColumns)
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
  public int getNumberOfControls()
  {
    return 0;
  }
}
