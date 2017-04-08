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

public class WorkbenchUtil
{
  public static IResource getCurrentDocument()
  {
    IAdapterManager adapterManager = Platform.getAdapterManager();
    IWorkbench wb = PlatformUI.getWorkbench();
    IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
    IWorkbenchPage page = window.getActivePage();
    IEditorPart editor = page.getActiveEditor();
    if (editor != null)
    {
      IEditorInput input = editor.getEditorInput();
      return adapterManager.getAdapter(input, IResource.class);
    }
    return null;
  }
}
