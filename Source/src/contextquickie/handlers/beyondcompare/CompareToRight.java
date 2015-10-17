package contextquickie.handlers.beyondcompare;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class CompareToRight extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String savedLeft = BaseBeyoncCompare.getSavedLeft();

		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);

		if ((savedLeft != null) && (selection.size() == 1)) {
			try {
				if (selection.getFirstElement() instanceof IFile) {
					IFile selectedItem = (IFile) selection.getFirstElement();
					Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe\" " + savedLeft
							+ " " + selectedItem.getLocation());
				} else if (selection.getFirstElement() instanceof IFolder) {
					IFolder selectedItem = (IFolder) selection.getFirstElement();
					Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe\" " + savedLeft
							+ " " + selectedItem.getLocation());
				} else if (selection.getFirstElement() instanceof IProject) {
					IProject selectedItem = (IProject) selection.getFirstElement();
					Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Beyond Compare 4\\BCompare.exe\" " + savedLeft
							+ " " + selectedItem.getLocation());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}
