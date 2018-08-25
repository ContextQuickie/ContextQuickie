package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;

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
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  protected final String getWorkingCopyRoot(final IPath path)
  {
    return new TortoiseWorkingCopyDetect().getWorkingCopyRoot(path, this.preferences.getWorkingCopyFolderName());
  }

  @Override
  public final Object execute(final ExecutionEvent event)
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = event.getParameter(TortoiseMenuConstants.COMMAND_ID);
    final String requiresPathString = event.getParameter(TortoiseMenuConstants.REQUIRES_PATH_ID);
    final String supportsLinkedResourcesString = event.getParameter(TortoiseMenuConstants.SUPPORTS_LINKED_RESOURCES_ID);
    final String executable = Activator.getDefault().getPreferenceStore().getString(this.preferences.getPath());
    
    boolean supportsLinkedResources = true;
    if ((supportsLinkedResourcesString != null) && (Boolean.parseBoolean(supportsLinkedResourcesString) == false))
    {
      supportsLinkedResources = false;
    }
    arguments.add("/command:" + command);
    Set<IResource> currentResources = null;
    if ((requiresPathString != null) && (Boolean.parseBoolean(requiresPathString)))
    {
      if (supportsLinkedResources == true)
      {
        currentResources = this.getSelectedResources();
      }
      else
      {
        currentResources = new ContextMenuEnvironment().getSelectedResources();
      }

      final String pathArgument = String.join("*", currentResources.stream().map(resource -> resource.getLocation().toOSString()).collect(Collectors.toSet()));
      arguments.add("/path:" + StringUtil.quoteString(pathArgument));
    }

    final String parameter1 = event.getParameter(TortoiseMenuConstants.PARAMETER_1_ID);
    if (parameter1 != null)
    {
      arguments.add(parameter1);
    }
    
    new ProcessWrapper().executeCommand(executable, currentResources, arguments);
    return null;
  }

  /**
   * Gets all selected resources of the specified execution event.
   * 
   * @return A collection containing all selected resources.
   */
  private Set<IResource> getSelectedResources()
  {
    final Set<IResource> selectedResources = new ContextMenuEnvironment().getSelectedResources();
    final Set<IResource> result = new HashSet<IResource>(selectedResources);
    if (Activator.getDefault().getPreferenceStore().getBoolean(this.preferences.getScanForLinkedResources()))
    {
      selectedResources.stream().map(resource -> resource.getAdapter(IContainer.class)).filter(Objects::nonNull).forEach(container ->
      {
        final String workingCopyRoot = this.getWorkingCopyRoot(container.getLocation());
        if (workingCopyRoot != null)
        {
          result.addAll(this.getLinkedResourcesOfContainer(container, workingCopyRoot));
        }
      });
    }

    return result;
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
}
