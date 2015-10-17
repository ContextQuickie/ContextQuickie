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
			if (selection.getFirstElement() instanceof IFile) {
				IFile selectedItem = (IFile) selection.getFirstElement();
				BaseBeyoncCompare.setSafedLeft(BeyondCompareSavedLeft.File, selectedItem.getLocation());
			}
			else if (selection.getFirstElement() instanceof IFolder) {
				IFolder selectedItem = (IFolder) selection.getFirstElement();
				BaseBeyoncCompare.setSafedLeft(BeyondCompareSavedLeft.Directory, selectedItem.getLocation());
			}
			else if (selection.getFirstElement() instanceof IProject) {
				IProject selectedItem = (IProject) selection.getFirstElement();
				BaseBeyoncCompare.setSafedLeft(BeyondCompareSavedLeft.Directory, selectedItem.getLocation());
			}
		}
		 	
		return null;
	}
}
