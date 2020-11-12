package contextquickie.beyondcompare;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

import contextquickie.base.AbstractMenuBuilder;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;

/**
 * @author ContextQuickie
 *
 *         Class for creating Beyond Compare menu entries.
 */
public class MenuBuilder extends AbstractMenuBuilder
{
  /**
   * The {@link BeyondCompare} instance used for accessing the registry. The
   * instance is queried once because {@link getContributionItems} is called
   * multiple times and registry shall be queried only once due to performance
   * reasons.
   */
  private BeyondCompare bc;

  private static final List<Integer> requiredResourceTypesForFiles = Arrays.asList(IResource.FILE);
  
  private static final List<Integer> requiredResourceTypesForFolders = Arrays.asList(IResource.FOLDER, IResource.PROJECT);

  /**
   * Constructor.
   */
  public MenuBuilder()
  {
    super(PreferenceConstants.P_BEYOND_COMPARE_ENABLED);
  }
  
  @Override
  protected List<IContributionItem> getMenuEntries()
  {
    List<IContributionItem> menuEntries = new ArrayList<IContributionItem>();
    List<IResource> selectedResources = new ContextMenuEnvironment().getSelectedResources().stream().collect(Collectors.toList());
    
    if (selectedResources.size() == 1)
    {
      IResource selectedResource = selectedResources.get(0);
      this.addSelectLeftSideMenuEntry(menuEntries, selectedResource);
      this.addCompareToRightMenuEntry(menuEntries, selectedResource);
    }
    else if (selectedResources.size() == 2)
    {
      this.addCompareMenuEntry(menuEntries, selectedResources);
    }
    
    return menuEntries;
  }
  
  private void addSelectLeftSideMenuEntry(List<IContributionItem> menuEntries, IResource selectedResource)
  {
    String label = null;
    if (requiredResourceTypesForFiles.contains(selectedResource.getType()))
    {
      label = "Select Left File";
    }
    else if (requiredResourceTypesForFolders.contains(selectedResource.getType()))
    {
      label = "Select Left Folder";
    }
    
    if (label != null)
    {
      final CommandContributionItemParameter commandParameter = new CommandContributionItemParameter(
          this.getServiceLocator(), 
          null,
          "ContextQuickie.commands.BeyondCompare.SelectLeftSide", 
          CommandContributionItem.STYLE_PUSH);

      // Create map of parameters for the command
      final Map<String, Object> parameters = new HashMap<String, Object>();
      final IPath location = selectedResource.getLocation();
      if (location != null)
      {
        parameters.put(SelectLeftSide.PathParameterName, location.toOSString());
        commandParameter.parameters = parameters;
        commandParameter.label = label;
        commandParameter.icon = contextquickie.Activator.getImageDescriptor("icons/BeyondCompare/SelectLeftFile.png");
        menuEntries.add(new CommandContributionItem(commandParameter));
      }
    }
  }

  private void addCompareToRightMenuEntry(List<IContributionItem> menuEntries, IResource selectedResource)
  {
    if (this.bc == null)
    {
      this.bc = new BeyondCompare();
      this.bc.readRegistry();
    }

    final BeyondCompareSavedLeft savedLeftType = bc.getSavedLeftType();
    
    if ((requiredResourceTypesForFiles.contains(selectedResource.getType()) && (savedLeftType == BeyondCompareSavedLeft.File)) ||
        (requiredResourceTypesForFolders.contains(selectedResource.getType()) && (savedLeftType == BeyondCompareSavedLeft.Directory)))
    {
      final String savedLeft = bc.getSavedLeft();
      final IPath location = selectedResource.getLocation();
      if (savedLeft != null && location != null)
      {
        final String filename = new File(savedLeft).getName();
        menuEntries.add(this.createCompareCommandMenuEntry(savedLeft, location.toOSString(), "Compare to " + filename));
      }
    }
  }

  private void addCompareMenuEntry(List<IContributionItem> menuEntries, List<IResource> selectedResources)
  {
    if ((requiredResourceTypesForFiles.contains(selectedResources.get(0).getType()) && requiredResourceTypesForFiles.contains(selectedResources.get(1).getType())) ||
        (requiredResourceTypesForFolders.contains(selectedResources.get(0).getType()) && requiredResourceTypesForFolders.contains(selectedResources.get(1).getType())))
    {
      final IPath location1 = selectedResources.get(0).getLocation();
      final IPath location2 = selectedResources.get(1).getLocation();
      if (location1 != null && location2 != null)
      {
        menuEntries.add(this.createCompareCommandMenuEntry(location1.toOSString(), location2.toOSString(), "Compare"));
      }
    }
  }
  
  private IContributionItem createCompareCommandMenuEntry(String leftSide, String rightSide, String label)
  {
    final CommandContributionItemParameter commandParameter = new CommandContributionItemParameter(
        this.getServiceLocator(), 
        null,
        "ContextQuickie.commands.BeyondCompare.Compare", 
        CommandContributionItem.STYLE_PUSH);

    // Create map of parameters for the command
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(Compare.LeftSideParameterName, leftSide);
    parameters.put(Compare.RightSideParameterName, rightSide);
    commandParameter.parameters = parameters;
    commandParameter.label = label;
    commandParameter.icon = contextquickie.Activator.getImageDescriptor("icons/BeyondCompare/Compare.png");
    return new CommandContributionItem(commandParameter);
  }
}
