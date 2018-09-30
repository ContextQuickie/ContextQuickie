package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.team.ResourceRuleFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.core.RepositoryProvider;

public class SvnRepositoryProvider extends RepositoryProvider implements IResourceChangeListener
{
  private ResourceRuleFactory resourceRuleFactory = new SvnResourceRuleFactory();

  /**
   * Default constructor.
   */
  public SvnRepositoryProvider()
  {
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
  }

  @Override
  public void configureProject() throws CoreException
  {
  }

  @Override
  public void deconfigure() throws CoreException
  {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
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
  
  @Override
  public void resourceChanged(IResourceChangeEvent event)
  {
    try
    {
      event.getDelta().accept(new ResourceDeltaVisitor());
    }
    catch (CoreException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
