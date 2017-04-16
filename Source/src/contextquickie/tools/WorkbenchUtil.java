package contextquickie.tools;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
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
}
