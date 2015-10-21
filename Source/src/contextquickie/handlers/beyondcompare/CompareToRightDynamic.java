package contextquickie.handlers.beyondcompare;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

/**
 * @author ContextQuickie
 * 
 *         Class for creating the "compare to right" context menu entry
 *         dynamically.
 *
 */
public class CompareToRightDynamic extends CompoundContributionItem implements IWorkbenchContribution {

	/**
	 * The service locator used to create the
	 * {@link CommandContributionItemParameter} for the menu entry.
	 */
	private IServiceLocator serviceLocator;

	/**
	 * The {@link BeyondCompare} instance used for accessing the registry. The
	 * instance is queried once because {@link getContributionItems} is called
	 * multiple times and registry shall be queried only once due to performance
	 * reasons.
	 */
	private BeyondCompare bc;

	/**
	 * Constructor, initializes the used {@link BeyondCompare} instance and
	 * queries the registry entries.
	 */
	public CompareToRightDynamic() {
		bc = new BeyondCompare();
		bc.readRegistry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
	 */
	@Override
	protected IContributionItem[] getContributionItems() {

		// By default, return an empty list (if context menu entry must not be
		// shown)
		Boolean showEntry = false;
		IContributionItem[] items = new IContributionItem[] {};

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) window.getSelectionService()
						.getSelection();
				Object receiver = structuredSelection.getFirstElement();

				BeyondCompareSavedLeft savedLeftType = bc.getSavedLeftType();

				// get all
				IAdapterManager adapterManager = Platform.getAdapterManager();
				int resourceType = adapterManager.getAdapter(receiver, IResource.class).getType();

				if (((resourceType == IResource.PROJECT) || (resourceType == IResource.FOLDER))
						&& (savedLeftType == BeyondCompareSavedLeft.Directory)) {
					showEntry = true;
				} else if ((resourceType == IResource.FILE) && (savedLeftType == BeyondCompareSavedLeft.File)) {
					showEntry = true;
				} else {
					showEntry = false;
				}

				if (showEntry) {
					String savedLeft = bc.getSavedLeft();
					if (savedLeft != null) {
						CommandContributionItemParameter parameter = new CommandContributionItemParameter(
								this.serviceLocator, null, "ContextQuickie.commands.compareToRight", 0);
						String filename = new File(savedLeft).getName();
						parameter.label = "Compare to " + filename;
						parameter.icon = contextquickie.Activator.getImageDescriptor("icons/BeyondCompare/Compare.png");
						items = new IContributionItem[] { new CommandContributionItem(parameter) };
					}
				}
			}
		}

		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.menus.IWorkbenchContribution#initialize(org.eclipse.ui.
	 * services.IServiceLocator)
	 */
	@Override
	public void initialize(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
}
