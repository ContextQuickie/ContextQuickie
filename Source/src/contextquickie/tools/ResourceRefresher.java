package contextquickie.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

/**
 * @author ContextQuickie
 *
 *         Class which will wait for a process to finish in the background.
 *         It updates resources after the process has been finished.  
 */
public class ResourceRefresher extends Thread implements IRunnableWithProgress
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
      try
      {
        PlatformUI.getWorkbench().getProgressService().run(true, false, this);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
	  catch (InvocationTargetException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
  {
    final Job applicationJob = new Job("ContextQuickie progress") 
    {
      protected IStatus run(IProgressMonitor monitor) 
      {
        monitor.setTaskName("Running external application");
        while (ResourceRefresher.this.process.isAlive())
        {
          if (monitor.isCanceled())
          {
            return Status.CANCEL_STATUS;
          }
          try
          {
            Thread.sleep(10);
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }
        }
        
        monitor.setTaskName("Refreshing workspace");
        for (IResource resource : ResourceRefresher.this.resourcesToRefresh)
        {
          if (monitor.isCanceled())
          {
            return Status.CANCEL_STATUS;
          }
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
            e.printStackTrace();
          }  
        }
        
        return Status.OK_STATUS;
      }
    };
    
    applicationJob.schedule();
  }
}
