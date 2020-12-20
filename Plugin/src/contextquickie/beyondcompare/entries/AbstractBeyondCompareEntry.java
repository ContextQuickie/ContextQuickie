package contextquickie.beyondcompare.entries;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.base.AbstractMenuEntry;

public abstract class AbstractBeyondCompareEntry extends AbstractMenuEntry
{
  private final ImageDescriptor imageDescriptor;
  
  protected AbstractBeyondCompareEntry(String iconName)
  {
    this.imageDescriptor = contextquickie.Activator.getImageDescriptor("icons/BeyondCompare/" + iconName); 
  }
  
  @Override
  public ImageDescriptor getImageDescriptor()
  {
    return this.imageDescriptor;
  }
}
