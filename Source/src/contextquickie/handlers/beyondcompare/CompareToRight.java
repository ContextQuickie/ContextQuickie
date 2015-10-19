package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class CompareToRight extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		BeyondCompare bc = new BeyondCompare();
		bc.readRegistry();
		String savedLeft = bc.getSavedLeft();

		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);

		IAdaptable rightSide = (IAdaptable) selection.getFirstElement();
		IResource rigthResource = rightSide.getAdapter(IResource.class);
		BeyondCompare.Compare(savedLeft, rigthResource.getLocation().toString());

		return null;
	}
}
