package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class Compare extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		Object[] paths = selection.toArray();
		IResource leftSide = (IResource) paths[0];
		IResource rightSide = (IResource) paths[1];
		BeyondCompare.Compare(leftSide.getLocation().toString(), rightSide.getLocation().toString());

		return null;
	}

}
