package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.team.ResourceRuleFactory;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class SvnResourceRuleFactory extends ResourceRuleFactory
{
  private CopyMoveInformation lastCopyInformation;
  
  private CopyMoveInformation lastMoveInformation;
  
  private IResource lastCreatedResource;

  @Override
  public ISchedulingRule createRule(IResource resource)
  {
    this.setLastCreatedResource(resource);
    return super.createRule(resource);
  }

  @Override
  public ISchedulingRule charsetRule(IResource resource)
  {
    return super.charsetRule(resource);
  }

  @Override
  public ISchedulingRule copyRule(IResource source, IResource destination)
  {
    this.setLastCopyInformation(new CopyMoveInformation(source, destination));
    return super.copyRule(source, destination);
  }

  @Override
  public ISchedulingRule deleteRule(IResource resource)
  {
   return super.deleteRule(resource);
  }

  @Override
  public ISchedulingRule modifyRule(IResource resource)
  {
    return super.modifyRule(resource);
  }

  @Override
  public ISchedulingRule moveRule(IResource source, IResource destination)
  {
    this.setLastMoveInformation(new CopyMoveInformation(source, destination));
    return super.moveRule(source, destination);
  }

  @Override
  public ISchedulingRule refreshRule(IResource resource)
  {
    return super.refreshRule(resource);
  }

  @Override
  public ISchedulingRule validateEditRule(IResource[] resources)
  {
    return super.validateEditRule(resources);
  }

  public CopyMoveInformation getLastCopyInformation()
  {
    return lastCopyInformation;
  }

  public void setLastCopyInformation(CopyMoveInformation value)
  {
    this.lastCopyInformation = value;
  }

  public CopyMoveInformation getLastMoveInformation()
  {
    return lastMoveInformation;
  }

  public void setLastMoveInformation(CopyMoveInformation value)
  {
    this.lastMoveInformation = value;
  }

  public IResource getLastCreatedResource()
  {
    return lastCreatedResource;
  }

  public void setLastCreatedResource(IResource value)
  {
    this.lastCreatedResource = value;
  }

}
