package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class Compare extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		Object[] paths = selection.toArray();
		IAdaptable leftSide = (IAdaptable) paths[0];
		IAdaptable rightSide = (IAdaptable) paths[1];
		IResource leftResource = leftSide.getAdapter(IResource.class);
		IResource rightResource = rightSide.getAdapter(IResource.class);
		BeyondCompare.Compare(leftResource.getLocation().toString(), rightResource.getLocation().toString());

		return null;
	}

}
