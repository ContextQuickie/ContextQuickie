package contextquickie.tortoise;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.base.AbstractMenuEntry;

public class TortoiseSubMenu extends AbstractMenuEntry
{
  private final String label;
  private final ImageDescriptor imageDescriptor;
  
  /**
   * Default constructor.
   * 
   * @param label
   *      Then entry label.
   * @param iconPath
   *      The path to the icon for the entry.
   */
  public TortoiseSubMenu(String label, String iconPath)
  {
    this.imageDescriptor = contextquickie.Activator.getImageDescriptor(iconPath);
    this.label = label;
  }
  
  @Override
  public void executeCommand()
  {
    // Nothing to do, the sub menu cannot be executed
  }

  @Override
  public ImageDescriptor getImageDescriptor()
  {
    return this.imageDescriptor;
  }

  @Override
  public String getLabel()
  {
    return this.label;
  }

}
