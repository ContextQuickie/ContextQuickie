package contextquickie.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author ContextQuickie
 *
 *         Class which provides information about the current context menu
 *         environment.
 */
public class ContextMenuEnvironment
{
  /**
   * Gets the currently selected resources.
   * 
   * @return The currently selected resources.
   */
  public Set<IResource> getSelectedResources()
  {
    final Set<IResource> selectedResources = new HashSet<IResource>();

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
            if (resource != null)
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
            selectedResources.add(adapterManager.getAdapter(editor.getEditorInput(), IResource.class));
          }
        }
      }
    }

    return selectedResources.stream().filter(r -> r != null).collect(Collectors.toSet());
  }
}
