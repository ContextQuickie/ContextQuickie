package contextquickie.base;

import org.eclipse.jface.resource.ImageDescriptor;

public class MenuSeparator extends AbstractMenuEntry
{
  @Override
  public void executeCommand()
  {
    // Nothing to do for separators
  }

  @Override
  public ImageDescriptor getImageDescriptor()
  {
    // Separators don't have an icon
    return null;
  }

  @Override
  public String getLabel()
  {
    // Separators don't have a label
    return null;
  }

  @Override
  public Boolean isSeparator()
  {
    return true;
  }
}
