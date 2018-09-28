package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.team.ResourceRuleFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.core.RepositoryProvider;

public class SvnRepositoryProvider extends RepositoryProvider
{
  ResourceRuleFactory resourceRuleFactory = new SvnResourceRuleFactory();

  @Override
  public void configureProject() throws CoreException
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void deconfigure() throws CoreException
  {
    // TODO Auto-generated method stub
  }

  @Override
  public String getID()
  {
    return SvnRepositoryProvider.class.getTypeName();
  }

  @Override
  public IResourceRuleFactory getRuleFactory()
  {
    return this.resourceRuleFactory;
  }
}
