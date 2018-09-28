package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.team.ResourceRuleFactory;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class SvnResourceRuleFactory extends ResourceRuleFactory
{

  @Override
  public ISchedulingRule createRule(IResource resource)
  {
    System.out.println("createRule: " + resource.getLocation().toOSString());
    return super.createRule(resource);
  }

  @Override
  public ISchedulingRule charsetRule(IResource resource)
  {
    System.out.println("charsetRule: " + resource.getLocation().toOSString());
    return super.charsetRule(resource);
  }

  @Override
  public ISchedulingRule copyRule(IResource source, IResource destination)
  {
    System.out.println("copyRule: " + source.getLocation().toOSString() + " to " + destination.getLocation().toOSString());
    return super.copyRule(source, destination);
  }

  @Override
  public ISchedulingRule deleteRule(IResource resource)
  {
    System.out.println("deleteRule: " + resource.getLocation().toOSString());
    return super.deleteRule(resource);
  }

  @Override
  public ISchedulingRule modifyRule(IResource resource)
  {
    System.out.println("modifyRule: " + resource.getLocation().toOSString());
    return super.modifyRule(resource);
  }

  @Override
  public ISchedulingRule moveRule(IResource source, IResource destination)
  {
    System.out.println("moveRule: " + source.getLocation().toOSString() + " to " + destination.getLocation().toOSString());
    return super.moveRule(source, destination);
  }

  @Override
  public ISchedulingRule refreshRule(IResource resource)
  {
    System.out.println("refreshRule: " + resource.getLocation().toOSString());
    return super.refreshRule(resource);
  }

  @Override
  public ISchedulingRule validateEditRule(IResource[] resources)
  {
    for (IResource resource : resources)
    {
      System.out.println("validateEditRule: " + resource.getLocation().toOSString());
    }
    return super.validateEditRule(resources);
  }

}
