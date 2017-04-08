package contextquickie.handlers.tortoise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.Activator;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tools.WorkbenchUtil;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise commands based on the passed
 *         parameters.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public abstract class TortoiseCommand extends AbstractHandler
{

  /**
   * @return The preference name of the command path.
   */
  protected abstract String getCommandPathName();

  /**
   * @return The name of the "command Id" parameter.
   */
  protected abstract String getCommandIdName();

  /**
   * @return The name of the "requires path name" parameter.
   */
  protected abstract String getRequiresPathName();

  /**
   * @return The name of the "Parameter1" parameter.
   */
  protected abstract String getParameter1Name();

  /**
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  protected abstract String getWorkingCopyRoot(IPath path);

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
   * ExecutionEvent)
   */
  @Override
  public Object execute(ExecutionEvent event)
  {
    List<String> arguments = new ArrayList<String>();
    arguments.add("/command:" + event.getParameter(this.getCommandIdName()));

    String requiresPathString = event.getParameter(this.getRequiresPathName());
    if ((requiresPathString != null) && (Boolean.parseBoolean(requiresPathString) == true))
    {
      Collection<String> currentResources = this.getSelectedResources(event);
      String pathArgument = String.join("*", currentResources);
      arguments.add("/path:" + StringUtil.QuoteString(pathArgument));
    }

    String parameter1 = event.getParameter(this.getParameter1Name());
    if (parameter1 != null)
    {
      arguments.add(parameter1);
    }

    String command = Activator.getDefault().getPreferenceStore().getString(this.getCommandPathName());
    ProcessWrapper.executeCommand(command, arguments);

    return null;
  }

  private Collection<String> getSelectedResources(ExecutionEvent event)
  {
    HashSet<String> currentResources = new HashSet<String>();
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof TreeSelection)
    {
      TreeSelection treeSelection = (TreeSelection) selection;
      for (Object selectedItem : treeSelection.toList())
      {
        if (selectedItem instanceof IAdaptable)
        {
          IAdaptable adaptable = (IAdaptable) selectedItem;
          currentResources.add(getResourcePath(adaptable));

          IContainer container = adaptable.getAdapter(IContainer.class);
          String workingCopyRoot = this.getWorkingCopyRoot(container.getLocation());
          if ((container != null) && (workingCopyRoot != null))
          {
            try
            {
              for (IResource member : container.members())
              {
                String memberWorkingCopyRoot = this.getWorkingCopyRoot(member.getLocation());
                if (member.isLinked() && (member instanceof IAdaptable) && (workingCopyRoot.equals(memberWorkingCopyRoot)))
                {
                  currentResources.add(this.getResourcePath(member));
                }
              }
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
    else if (selection instanceof TextSelection)
    {
      currentResources.add(this.getResourcePath(WorkbenchUtil.getCurrentDocument()));
    }

    return currentResources;
  }

  private String getResourcePath(IAdaptable adaptable)
  {
    IResource resource = adaptable.getAdapter(IResource.class);
    if (resource != null)
    {
      return resource.getLocation().toOSString();
    }

    return null;
  }
}
