package contextquickie.tools;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * @author ContextQuickie
 *
 *         Class which will wait for a process to finish in the background.
 *         It updates resources after the process has been finished.  
 */
public class ResourceRefresher extends Thread
{
  private Process process;
  
  private Set<IResource> resourcesToRefresh;
  
  public ResourceRefresher(Process p, Set<IResource> resources)
  {
    this.process = p;
    this.resourcesToRefresh = resources;
  }
  
  public void run()
  {
    if ((this.process != null) && (this.resourcesToRefresh != null))
    {
      while (this.process.isAlive())
      {
        try
        {
          Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
      for (IResource resource : this.resourcesToRefresh)
      {
        if (resource.getParent() != null)
        {
          resource = resource.getParent();
        }
        try
        {
          resource.refreshLocal(IResource.DEPTH_INFINITE, null);
        }
        catch (CoreException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
