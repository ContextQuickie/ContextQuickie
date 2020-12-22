package contextquickie.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Wrapper class for starting processes.
 */
public final class ProcessWrapper
{
  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
   * @param arguments
   *          The arguments of the command.
   */
  public void executeCommand(final String command, final String... arguments)
  {
    this.executeCommand(command, null, Arrays.asList(arguments));
  }

  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
   * @param resources
   *          The selected resources of the command.
   * @param arguments
   *          The arguments of the command.
   */
  public void executeCommand(final String command, Set<IResource> resources, final List<String> arguments)
  {
    final List<String> commandAndArguments = new ArrayList<String>();
    commandAndArguments.add(command);
    for (String parameter : arguments)
    {
      if (parameter.contains(" "))
      {
        commandAndArguments.add(StringUtil.quoteString(parameter));
      }
      else
      {
        commandAndArguments.add(parameter);
      }
    }
    final ProcessBuilder processBuilder = new ProcessBuilder(commandAndArguments);
    try
    {
      Process process = processBuilder.start();
      new Thread(() -> this.runMonitorJobs(process, resources)).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void runMonitorJobs(Process process, Set<IResource> resources)
  {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final boolean showProgressForExternalTools = preferenceStore.getBoolean(PreferenceConstants.SHOW_PROGRESS_FOR_EXTERNAL_TOOLS);
    final boolean refreshWorkspaceAfterExecution = preferenceStore.getBoolean(PreferenceConstants.REFRESH_WORKSPACE_AFTER_EXECUTION);
    final String progresstitle = "ContextQuickie progress";
    Job job = null;

    if (showProgressForExternalTools && refreshWorkspaceAfterExecution)
    {
      job = new Job(progresstitle) 
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          IStatus status = ProcessWrapper.this.waitForProcessToFinish(process, monitor);
          
          if ((status == Status.OK_STATUS) && refreshWorkspaceAfterExecution)
          {
            status = ProcessWrapper.this.refreshResourcesAfterProcessEnd(resources, monitor);
          }
          
          return status;
        }
      };
    }
    else if (showProgressForExternalTools)
    {
      // refreshWorkspaceAfterExecution is set to false in this case, otherwise the previous if condition would match
      job = new Job(progresstitle) 
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          return ProcessWrapper.this.waitForProcessToFinish(process, monitor);
        }
      };
    }
    else if (refreshWorkspaceAfterExecution)
    {
      // showProgressForExternalTools is set to false in this case, otherwise the previous if condition would match
      ProcessWrapper.this.waitForProcessToFinish(process, null);
      job = new Job(progresstitle) 
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          return ProcessWrapper.this.refreshResourcesAfterProcessEnd(resources, monitor);
        }
      };
    }
    
    if (job != null)
    {
      job.schedule();
    }
  }
  
  private IStatus waitForProcessToFinish(Process process, IProgressMonitor monitor)
  {
    if (process != null)
    {
      if (monitor != null)
      {
        monitor.setTaskName("Running external application");
      }
      while (process.isAlive())
      {
        if ((monitor != null) && (monitor.isCanceled()))
        {
          process.destroy();
          return Status.CANCEL_STATUS;
        }
        try
        {
          Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
          return Status.CANCEL_STATUS;
        }
      }
    }
    
    return Status.OK_STATUS;
  }

  private IStatus refreshResourcesAfterProcessEnd(Set<IResource> resources, IProgressMonitor monitor)
  {
    if (resources != null)
    {
      monitor.setTaskName("Refreshing workspace");
      for (IResource resource : resources)
      {
        if (monitor.isCanceled())
        {
          return Status.CANCEL_STATUS;
        }
        if ((resource.getParent() != null) && (resource.getType() != IResource.PROJECT))
        {
          resource = resource.getParent();
        }
        try
        {
          resource.refreshLocal(IResource.DEPTH_INFINITE, monitor);
        }
        catch (CoreException e)
        {
          e.printStackTrace();
          return Status.CANCEL_STATUS;
        }
      }
    }
    
    return Status.OK_STATUS;
  }
}
