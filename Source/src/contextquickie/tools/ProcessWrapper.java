package contextquickie.tools;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Wrapper class for starting processes.
 */
public final class ProcessWrapper implements IRunnableWithProgress
{
  private Process process;
  
  private Set<IResource> resourcesToRefresh;
  
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
   * @param arguments
   *          The arguments of the command.
   */
  public void executeCommand(final String command, Set<IResource> resources, final String... arguments)
  {
    this.executeCommand(command, resources, Arrays.asList(arguments));
  }

  /**
   * Executes a command with the passed arguments.
   * 
   * @param command
   *          The command to execute.
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
      this.process = processBuilder.start();
      this.resourcesToRefresh = resources;
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
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
  {
    final boolean showProgressForExternalTools = Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SHOW_PROGRESS_FOR_EXTERNAL_TOOLS);
    final boolean refreshWorkspaceAfterExecution = Activator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.REFRESH_WORKSPACE_AFTER_EXECUTION);
    
    if (showProgressForExternalTools || refreshWorkspaceAfterExecution)
    {
      final Job job = new Job("ContextQuickie progress") 
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          IStatus status = ProcessWrapper.this.waitForProcessToFinish(ProcessWrapper.this.process, monitor);
          if ((status == Status.OK_STATUS) && refreshWorkspaceAfterExecution)
          {
            status = ProcessWrapper.this.refreshResourcesAfterProcessEnd(ProcessWrapper.this.resourcesToRefresh, monitor);
          }
          
          return status;
        }
      };

      job.schedule();
    }
  }
  
  private IStatus waitForProcessToFinish(Process process, IProgressMonitor monitor)
  {
    if (process != null)
    {
      monitor.setTaskName("Running external application");
      
      // Java 1.6 compatibility
      boolean processIsAlive = true;
      while (processIsAlive)
      {
        if (monitor.isCanceled())
        {
          ProcessWrapper.this.process.destroy();
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
        
        processIsAlive = false;
        try
        {
          process.exitValue();
        }
        catch (IllegalThreadStateException e)
        {
          processIsAlive = true;
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
      for (IResource resource : ProcessWrapper.this.resourcesToRefresh)
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
