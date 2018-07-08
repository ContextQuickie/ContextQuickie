package contextquickie.handlers.beyondcompare;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import contextquickie.tools.ContextMenuEnvironment;

/**
 * @author ContextQuickie
 * 
 *         Class for creating the "compare to right" context menu entry
 *         dynamically.
 *
 */
public class CompareToRightDynamic extends CompoundContributionItem implements IWorkbenchContribution
{

  /**
   * The service locator used to create the
   * {@link CommandContributionItemParameter} for the menu entry.
   */
  private IServiceLocator currentServiceLocator;

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
  public CompareToRightDynamic()
  {
    bc = new BeyondCompare();
    bc.readRegistry();
  }

  @Override
  protected final IContributionItem[] getContributionItems()
  {
    // By default, return an empty list (if context menu entry must not be shown)
    Boolean showEntry = false;
    IContributionItem[] items = new IContributionItem[] {};
    IResource selectedResource = new ContextMenuEnvironment().getSelectedResources().stream().findFirst().orElse(null);

    if (selectedResource != null)
    {
      int resourceType = selectedResource.getType();
      final BeyondCompareSavedLeft savedLeftType = bc.getSavedLeftType();
      
      if (((resourceType == IResource.PROJECT) || (resourceType == IResource.FOLDER)) && (savedLeftType == BeyondCompareSavedLeft.Directory))
      {
        showEntry = true;
      }
      else if ((resourceType == IResource.FILE) && (savedLeftType == BeyondCompareSavedLeft.File))
      {
        showEntry = true;
      }
      else
      {
        showEntry = false;
      }

      if (showEntry)
      {
        final String savedLeft = bc.getSavedLeft();
        if (savedLeft != null)
        {
          final CommandContributionItemParameter parameter = new CommandContributionItemParameter(this.currentServiceLocator, null,
              "ContextQuickie.commands.BeyondCompare.CompareToRight", 0);
          final String filename = new File(savedLeft).getName();
          parameter.label = "Compare to " + filename;
          parameter.icon = contextquickie.Activator.getImageDescriptor("icons/BeyondCompare/Compare.png");
          items = new IContributionItem[] { new CommandContributionItem(parameter) };
        }
      }
    }

    return items;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.menus.IWorkbenchContribution#initialize(org.eclipse.ui.
   * services.IServiceLocator)
   */
  @Override
  public void initialize(final IServiceLocator serviceLocator)
  {
    this.currentServiceLocator = serviceLocator;
  }
}
