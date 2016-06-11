package contextquickie.handlers.tortoise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import contextquickie.Activator;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tools.WorkbenchUtil;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise commands based on the passed
 *         parameters.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public abstract class TortoiseCommand extends AbstractHandler {

  /**
   * @return The preference name of the command path.
   */
  protected abstract String getCommandPathName();

  /**
   * @return The name of the "command Id" parameter.
   */
  protected abstract String getCommandIdName();

  /**
   * @return The name of the "requires path name" parameter.
   */
  protected abstract String getRequiresPathName();

  /**
   * @return The name of the "Parameter1" parameter.
   */
  protected abstract String getParameter1Name();

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
   * ExecutionEvent)
   */
  @Override
  public Object execute(ExecutionEvent event) {
    List<String> arguments = new ArrayList<String>();
    String command = Activator.getDefault().getPreferenceStore().getString(this.getCommandPathName());

    arguments.add("/command:" + event.getParameter(this.getCommandIdName()));

    String requiresPathString = event.getParameter(this.getRequiresPathName());
    if (requiresPathString != null) {
      Boolean requiresPath = Boolean.parseBoolean(requiresPathString);
      if (requiresPath == true) {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        String pathArgument = "";
        if (selection instanceof TreeSelection) {
          TreeSelection treeSelection = (TreeSelection) selection;
          if (treeSelection.isEmpty() == false) {
            Iterator<?> iterator = treeSelection.iterator();
            List<String> currentResources = new ArrayList<String>();
            while (iterator.hasNext()) {
              Object selectedItem = iterator.next();
              if (selectedItem instanceof IAdaptable) {
                IAdaptable adaptable = (IAdaptable) selectedItem;
                IResource resource = adaptable.getAdapter(IResource.class);
                if (resource != null) {
                  String resoureString = resource.getLocation().toString();
                  if (currentResources.contains(resoureString) == false) { // Avoids
                                                                           // duplicate
                                                                           // entries
                    currentResources.add(resoureString);
                  }
                }
              }
            }

            Iterator<String> resourcesIterator = currentResources.iterator();
            while (resourcesIterator.hasNext()) {
              pathArgument += resourcesIterator.next();
              if (resourcesIterator.hasNext()) {
                pathArgument += "*";
              }
            }
          }
        } else if (selection instanceof TextSelection) {
          IResource resource = WorkbenchUtil.getCurrentDocument();
          if (resource != null) {
            pathArgument = resource.getLocation().toOSString();
          }
        }

        arguments.add("/path:" + StringUtil.QuoteString(pathArgument));
      }
    }

    String parameter1 = event.getParameter(this.getParameter1Name());
    if (parameter1 != null) {
      arguments.add(parameter1);
    }

    ProcessWrapper.executeCommand(command, arguments);

    return null;
  }
}
