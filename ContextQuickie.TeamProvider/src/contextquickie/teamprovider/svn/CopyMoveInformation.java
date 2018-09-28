package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResource;

public class CopyMoveInformation
{
  private IResource source;
  
  private IResource destination;
  
  public CopyMoveInformation(final IResource source, final IResource destination)
  {
    this.source = source;
    this.destination = destination;
  }

  public IResource getDestination()
  {
    return destination;
  }

  public void setDestination(IResource value)
  {
    this.destination = value;
  }

  public IResource getSource()
  {
    return source;
  }

  public void setSource(IResource value)
  {
    this.source = value;
  }
}
