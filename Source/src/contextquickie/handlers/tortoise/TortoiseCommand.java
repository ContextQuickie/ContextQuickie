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
import contextquickie.preferences.TortoisePreferenceConstants;
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
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants _preferences;

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

  protected TortoiseCommand(TortoisePreferenceConstants preferences)
  {
    this._preferences = preferences;
  }

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

    String command = Activator.getDefault().getPreferenceStore().getString(this._preferences.getPath());
    ProcessWrapper.executeCommand(command, arguments);

    return null;
  }

  /**
   * Gets all selected resources of the specified execution event.
   * 
   * @param event
   *          The used execution event.
   * @return A collection containing all selected resources.
   */
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

          if (Activator.getDefault().getPreferenceStore().getBoolean(this._preferences.getScanForLinkedResources()))
          {
            IContainer container = adaptable.getAdapter(IContainer.class);
            if (container != null)
            {
              String workingCopyRoot = this.getWorkingCopyRoot(container.getLocation());
              if (workingCopyRoot != null)
              {
                currentResources.addAll(this.getLinkedResourcesOfContainer(container, workingCopyRoot));
              }
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

  /**
   * Gets all linked resources in the specified container which have the same
   * working copy root.
   * 
   * @param container
   *          The container which is used for searching for linked resources.
   * @param workingCopyRoot
   *          The root path to the working copy folder of the container.
   * @return
   */
  private HashSet<String> getLinkedResourcesOfContainer(IContainer container, String workingCopyRoot)
  {
    HashSet<String> linkedResources = new HashSet<String>();
    try
    {
      for (IResource member : container.members())
      {
        String memberWorkingCopyRoot = this.getWorkingCopyRoot(member.getLocation());
        if (member.isLinked() && (member instanceof IAdaptable) && (workingCopyRoot.equals(memberWorkingCopyRoot)))
        {
          linkedResources.add(this.getResourcePath(member));

          // Check if there are also linked resourced within the linked resource
          // container
          IAdaptable adaptable = (IAdaptable) member;
          container = adaptable.getAdapter(IContainer.class);
          if (container != null)
          {
            this.getLinkedResourcesOfContainer(container, workingCopyRoot);
          }
        }
      }
    }
    catch (CoreException e)
    {
      e.printStackTrace();
    }

    return linkedResources;
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
