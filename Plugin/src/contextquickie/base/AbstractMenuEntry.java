package contextquickie.base;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.tools.ContextMenuEnvironment;

public abstract class AbstractMenuEntry
{
  public static String ParameterName = AbstractMenuEntry.class.getCanonicalName();

  public abstract void executeCommand();
  
  public abstract ImageDescriptor getImageDescriptor();
  
  public abstract String getLabel();
  
  public Boolean isVisible(ContextMenuEnvironment environment)
  {
    return true;
  }
}
