package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.jface.viewers.TreeSelection;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SelectLeftSide extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public SelectLeftSide() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		
		if (selection.size() == 1) {
			BeyondCompare bc = new BeyondCompare();
			if (selection.getFirstElement() instanceof IFile) {
				IFile selectedItem = (IFile) selection.getFirstElement();
				bc.setSavedLeft(selectedItem.getLocation().toString());
				bc.setSavedLeftType(BeyondCompareSavedLeft.File);
				bc.writeRegistry();
			}
			else if (selection.getFirstElement() instanceof IFolder) {
				IFolder selectedItem = (IFolder) selection.getFirstElement();
				bc.setSavedLeft(selectedItem.getLocation().toString());
				bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
				bc.writeRegistry();
			}
			else if (selection.getFirstElement() instanceof IProject) {
				IProject selectedItem = (IProject) selection.getFirstElement();
				bc.setSavedLeft(selectedItem.getLocation().toString());
				bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
				bc.writeRegistry();
			}
		}
		 	
		return null;
	}
}
