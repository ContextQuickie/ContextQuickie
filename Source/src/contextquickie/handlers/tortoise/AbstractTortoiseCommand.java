package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tools.WorkbenchUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/**
 * Class which executes all Tortoise commands based on the passed parameters.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public abstract class AbstractTortoiseCommand extends AbstractHandler
{
  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferences;
  
  /**
   * Default constructor.
   * 
   * @param tortoisePreferences
   *          The preferences which will be used for execution.
   */
  protected AbstractTortoiseCommand(final TortoisePreferenceConstants tortoisePreferences)
  {
    this.preferences = tortoisePreferences;
  }

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

  @Override
  public final Object execute(final ExecutionEvent event)
  {
    final List<String> arguments = new ArrayList<String>();
    arguments.add("/command:" + event.getParameter(this.getCommandIdName()));
    Set<IResource> currentResources = null;
    final String requiresPathString = event.getParameter(this.getRequiresPathName());
    if ((requiresPathString != null) && (Boolean.parseBoolean(requiresPathString)))
    {
      currentResources = this.getSelectedResources(event);
      final Set<String> pathArguments = new HashSet<String>();
      for (IResource resource : currentResources)
      {
        pathArguments.add(this.getResourcePath(resource));
      }

      String pathArgument = String.join("*", pathArguments);
      arguments.add("/path:" + StringUtil.quoteString(pathArgument));
    }

    final String parameter1 = event.getParameter(this.getParameter1Name());
    if (parameter1 != null)
    {
      arguments.add(parameter1);
    }

    final String command = Activator.getDefault().getPreferenceStore().getString(this.preferences.getPath());
    new ProcessWrapper().executeCommand(command, currentResources, arguments);

    return null;
  }

  /**
   * Gets all selected resources of the specified execution event.
   * 
   * @param event
   *          The used execution event.
   * @return A collection containing all selected resources.
   */
  private Set<IResource> getSelectedResources(final ExecutionEvent event)
  {
    final Set<IResource> currentResources = new HashSet<IResource>();
    final ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof TreeSelection)
    {
      final TreeSelection treeSelection = (TreeSelection) selection;
      for (Object selectedItem : treeSelection.toList())
      {
        if (selectedItem instanceof IAdaptable)
        {
          final IAdaptable adaptable = (IAdaptable) selectedItem;
          currentResources.add(adaptable.getAdapter(IResource.class));

          if (Activator.getDefault().getPreferenceStore().getBoolean(this.preferences.getScanForLinkedResources()))
          {
            final IContainer container = adaptable.getAdapter(IContainer.class);
            if (container != null)
            {
              final String workingCopyRoot = this.getWorkingCopyRoot(container.getLocation());
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
      currentResources.add(WorkbenchUtil.getCurrentDocument());
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
   * @return A HashSet containing all linked resources of the specified
   *         container.
   */
  private Set<IResource> getLinkedResourcesOfContainer(final IContainer container, final String workingCopyRoot)
  {
    final Set<IResource> linkedResources = new HashSet<IResource>();
    try
    {
      for (IResource member : container.members())
      {
        final String memberWorkingCopyRoot = this.getWorkingCopyRoot(member.getLocation());
        if (member.isLinked() && (member instanceof IAdaptable) && (workingCopyRoot.equals(memberWorkingCopyRoot)))
        {
          linkedResources.add(member);

          // Check if there are also linked resourced within the linked resource
          // container
          final IAdaptable adaptable = (IAdaptable) member;
          final IContainer childContainer = adaptable.getAdapter(IContainer.class);
          if (childContainer != null)
          {
            this.getLinkedResourcesOfContainer(childContainer, workingCopyRoot);
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

  /**
   * Gets the resource path from the specified IAdaptable instance.
   * 
   * @param adaptable
   *          The instance which will be evaluated.
   * @return The resource path or null if the path cannot be determined.
   */
  private String getResourcePath(final IAdaptable adaptable)
  {
    final IResource resource = adaptable.getAdapter(IResource.class);
    if (resource != null)
    {
      return resource.getLocation().toOSString();
    }

    return null;
  }
}
