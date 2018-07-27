package contextquickie.tools;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class ContextMenuEnvironment
{
  public Set<IResource> getSelectedResources()
  {
    final Set<IResource> selectedResources = new HashSet<IResource>();
    
    final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null)
    {
      final ISelection selection = window.getSelectionService().getSelection();
      if ((selection != null) && (selection.isEmpty() == false))
      {
        // Context menu has been opened in a tree view
        if (selection instanceof IStructuredSelection)
        {
          final IAdapterManager adapterManager = Platform.getAdapterManager();
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
          selectedResources.add(this.getCurrentDocument());
        }
      }
    }
    
    return selectedResources;
  }
  
  /**
   * @return The current document of the workbench.
   */
  private IResource getCurrentDocument()
  {
    final IAdapterManager adapterManager = Platform.getAdapterManager();
    final IWorkbench wb = PlatformUI.getWorkbench();
    final IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
    final IWorkbenchPage page = window.getActivePage();
    final IEditorPart editor = page.getActiveEditor();
    if (editor != null)
    {
      final IEditorInput input = editor.getEditorInput();
      return adapterManager.getAdapter(input, IResource.class);
    }
    return null;
  }
}
