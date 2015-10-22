package contextquickie.handlers.tortoisesvn;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ProcessWrapper;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise SVN commands based on the passed
 *         parameters.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class TortoiseSVNCommand extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<String> arguments = new ArrayList<String>();
		String command = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_TORTOISE_SVN_PATH);
		
		arguments.add(
				"/command:" + event.getParameter("ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.CommandID"));

		String requiresPathString = event
				.getParameter("ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.RequiresPath");
		if (requiresPathString != null) {
			Boolean requiresPath = Boolean.parseBoolean(requiresPathString);
			if (requiresPath == true) {
				TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
				Object selectedItem = selection.getFirstElement();
				if (selectedItem instanceof IAdaptable) {
					IAdaptable adaptable = (IAdaptable) selectedItem;
					IResource resource = adaptable.getAdapter(IResource.class);
					arguments.add("/path:" + '"' + resource.getLocation() + '"');
				}
			}
		}

		String parameter1 = event.getParameter("ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.Parameter1");
		if (parameter1 != null) {
			arguments.add(parameter1);
		}
		
		ProcessWrapper.executeCommand(command, arguments);
		
		return null;
	}
}
