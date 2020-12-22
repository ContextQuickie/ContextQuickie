package contextquickie.tools;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Class which provides information about the current context menu environment.
 */
public class ContextMenuEnvironment
{
  private final Set<IPath> selectedFiles = new HashSet<IPath>();
  
  private final Set<IPath> selectedDirectories = new HashSet<IPath>();
  
  private final Set<IResource> selectedResources = new HashSet<IResource>();
  
  /**
   * Default constructor which checks the environment for selected resources, files and folders.
   */
  public ContextMenuEnvironment()
  {
    final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null)
    {
      final ISelection selection = window.getSelectionService().getSelection();
      if (selection != null)
      {
        final IAdapterManager adapterManager = Platform.getAdapterManager();

        if ((selection instanceof ITreeSelection) && (selection.isEmpty() == false))
        {
          // Context menu has been opened in a tree view
          for (Object selectedItem : ((IStructuredSelection) selection).toList())
          {
            final IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
            if (this.convertIResourceToIPath(resource) != null)
            {
              selectedResources.add(resource);
            }
          }
        }
        else if (selection instanceof TextSelection)
        {
          // Context menu has been opened in an editor
          IEditorPart editor = window.getActivePage().getActiveEditor();
          if (editor != null)
          {
            final IResource resource = adapterManager.getAdapter(editor.getEditorInput(), IResource.class);
            if (this.convertIResourceToIPath(resource) != null)
            {
              selectedResources.add(resource);
            }
          }
        }
      }
    }
    
    for (IResource resource : selectedResources)
    {
      IPath location = resource.getLocation();
      File locationFile = location.toFile();
      if (locationFile.isFile())
      {
        this.selectedFiles.add(location);
      }
      else if (locationFile.isDirectory())
      {
        this.selectedDirectories.add(location);
      }
    }
  }

  /**
   * Gets the currently selected resources.
   * 
   * @return The currently selected resources.
   */
  public Set<IResource> getSelectedResources()
  {
    return this.selectedResources;
  }

  private IPath convertIResourceToIPath(IResource resource)
  {
    IPath location = null;
    if (resource != null)
    {
      location = resource.getLocation();
    }
    
    return location;
  }

  /**
   * Gets a set containing all selected items which are files.
   */
  public Set<IPath> getSelectedFiles()
  {
    return this.selectedFiles;
  }

  /**
   * Gets a set containing all selected items which are directories.
   */
  public Set<IPath> getSelectedDirectories()
  {
    return this.selectedDirectories;
  }
}
