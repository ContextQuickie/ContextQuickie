package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.team.ResourceRuleFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.core.RepositoryProvider;

public class SvnRepositoryProvider extends RepositoryProvider
{
  private ResourceRuleFactory resourceRuleFactory = new SvnResourceRuleFactory();

  private ResourceChangeListener resourceChangeListener = new ResourceChangeListener();

  /**
   * Default constructor.
   */
  public SvnRepositoryProvider()
  {
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this.resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
  }

  @Override
  public void configureProject() throws CoreException
  {
  }

  @Override
  public void deconfigure() throws CoreException
  {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this.resourceChangeListener);
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
