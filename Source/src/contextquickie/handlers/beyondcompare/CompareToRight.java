package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author ContextQuickie
 *
 *         Class which implements the "Compare to right side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CompareToRight extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		BeyondCompare bc = new BeyondCompare();
		bc.readRegistry();
		String savedLeft = bc.getSavedLeft();

		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		IAdapterManager adapterManager = Platform.getAdapterManager();

		IResource rightResource = adapterManager.getAdapter(selection.getFirstElement(), IResource.class);
		if (rightResource != null) {
			BeyondCompare.Compare(savedLeft, rightResource.getLocation().toString());
		}
		return null;
	}
}
