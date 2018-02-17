package contextquickie.tools;

import java.util.ArrayList;
import java.util.List;

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

/**
 *  Utility class for accessing workbench data.
 */
public final class WorkbenchUtil
{
  /**
   * Prevents from creating instances.
   */
  private WorkbenchUtil()
  {
  }

  /**
   * @return The current document of the workbench.
   */
  public static IResource getCurrentDocument()
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
  
  /**
   * @return A list of all currently selected resources.
   */
  public static List<IResource> getCurrentlySelectedRessources()
  {
    final List<IResource> result = new ArrayList<IResource>();
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
          final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
          if (adapterManager != null)
          {
            for (Object selectedItem : structuredSelection.toList())
            {
              final IResource resource = adapterManager.getAdapter(selectedItem, IResource.class);
              if (resource != null)
              {
                result.add(resource);
              }
            }
          }
        }
        else if (selection instanceof TextSelection)
        {
          result.add(WorkbenchUtil.getCurrentDocument());
        }
      }
    }
    
    return result;
  }
}
