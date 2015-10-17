package contextquickie.handlers.beyondcompare;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

public class CompareToRightDynamic extends CompoundContributionItem implements IWorkbenchContribution {

	private IServiceLocator serviceLocator;
	private BeyondCompare bc;
	
	public CompareToRightDynamic() {
		bc = new BeyondCompare();
		bc.readRegistry();
	}
	@Override
	protected IContributionItem[] getContributionItems() {
		
		IContributionItem[] items = new IContributionItem[] {};

		
		Boolean showEntry = false;
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			Object receiver = selection.getFirstElement();

			BeyondCompareSavedLeft savedLeftType = bc.getSavedLeftType();
			if (((receiver instanceof IProject) || (receiver instanceof IFolder))
					&& (savedLeftType == BeyondCompareSavedLeft.Directory)) {
				showEntry = true;
			} else if ((receiver instanceof IFile) && (savedLeftType == BeyondCompareSavedLeft.File)) {
				showEntry = true;
			} else {
				showEntry = false;
			}

			if (showEntry) {
				String savedLeft = bc.getSavedLeft();
				if (savedLeft != null) {
					CommandContributionItemParameter parameter = new CommandContributionItemParameter(
							this.serviceLocator, "my.project.myCommandContributionItem",
							"ContextQuickie.commands.compareToRight", 0);
					String filename = new File(savedLeft).getName();
					parameter.label = "Compare to " + filename;
					parameter.icon = contextquickie.Activator.getImageDescriptor("icons/Compare.png");
					items = new IContributionItem[] { new CommandContributionItem(parameter) };
					items[0].update();
				}
			}
		}

		return items;
	}

	@Override
	public void initialize(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
}
