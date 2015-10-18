package contextquickie.handlers.tortoisesvn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseSVNCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<String> command = new ArrayList<String>();
		command.add(Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_TORTOISE_SVN_PATH));
		command.add("/command:" + event.getParameter("ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.CommandID"));
		
		String requiresPathString = event.getParameter("ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.RequiresPath");
		if (requiresPathString != null) {
			Boolean requiresPath = Boolean.parseBoolean(requiresPathString);
			if (requiresPath == true) {
				TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
				Object selectedItem = selection.getFirstElement();
				if (selectedItem instanceof IResource) {
					IResource resource = (IResource)selectedItem;
					command.add("/path:" + '"' + resource.getLocation() + '"');
				}
			}
		}
		
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		try {
			processBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
