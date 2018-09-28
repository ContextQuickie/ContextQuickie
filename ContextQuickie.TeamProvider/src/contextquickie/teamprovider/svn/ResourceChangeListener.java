package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;

/**
 * @author ContextQuickie
 * 
 *         Class for monitoring changes in the eclipse environment to apply the
 *         changes in the working copy.
 *
 */
public class ResourceChangeListener implements IResourceChangeListener
{
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
