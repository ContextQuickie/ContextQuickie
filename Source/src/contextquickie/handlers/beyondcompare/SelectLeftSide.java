package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.jface.viewers.TreeSelection;

/**
 * @author ContextQuickie
 * 
 *         Class which implements the "Select Left Side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SelectLeftSide extends AbstractHandler {

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);

		BeyondCompare bc = new BeyondCompare();
		IAdapterManager adapterManager = Platform.getAdapterManager();
		IResource resource = adapterManager.getAdapter(selection.getFirstElement(), IResource.class);
		int resourceType = resource.getType();
		if ((resourceType == IResource.FOLDER) || (resourceType == IResource.PROJECT)) {
			bc.setSavedLeft(resource.getLocation().toString());
			bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
			bc.writeRegistry();
		} else if (resourceType == IResource.FILE) {
			bc.setSavedLeft(resource.getLocation().toString());
			bc.setSavedLeftType(BeyondCompareSavedLeft.File);
			bc.writeRegistry();
		}

		return null;
	}
}
