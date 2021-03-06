package contextquickie.tortoise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.Activator;
import contextquickie.base.AbstractMenuEntry;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import rolandomagico.jniregistry.Registry;

/**
 * Menu entry configuration for Tortoise.
 */
public abstract class AbstractTortoiseMenuEntry extends AbstractMenuEntry
{
  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferenceConstants;
  
  /**
   * The environment for which the entry is created.
   */
  private TortoiseEnvironment environment;
  
  /**
   * The label of the entry.
   */
  private String label;
  
  /**
   * The help text of the entry.
   */
  private String helpText;

  /**
   * The path to the entry icon.
   */
  private ImageDescriptor icon;

  /**
   * The menu ID.
   */
  private long menuId;

  /**
   * The command which will be passed to the Tortoise program.
   */
  private String entryCommand;
  
  /**
   * The first parameter which will be passed to the Tortoise program.
   */
  private String parameter1;

  /**
   * A value indicating whether the path must be passed to the Tortoise program.
   */
  private Boolean entryRequiresPath = true;

  /**
   * A value indicating whether this entry is visible within a working copy.
   */
  private boolean isVisibleInWorkingCopy = true;

  /**
   * A value indicating whether this entry is visible without a working copy.
   */
  private boolean isVisibleWithoutWorkingCopy = false;

  /**
   * The maximum number of selected files to display this entry.
   */
  private int maxFileCount = Integer.MAX_VALUE;

  /**
   * The maximum number of selected folders to display this entry.
   */
  private int maxFolderCount = Integer.MAX_VALUE;
  
  /**
   * The maximum number of selected items to display this entry.
   */
  private int maxItemsCount = Integer.MAX_VALUE;
  
  /**
   * The minimum number of selected files to display this entry.
   */
  private int minFileCount = Integer.MIN_VALUE;

  /**
   * The minimum number of selected folders to display this entry.
   */
  private int minFolderCount = Integer.MIN_VALUE;
  
  /**
   * The minimum number of selected items to display this entry.
   */
  private int minItemsCount = Integer.MIN_VALUE;
  
  /**
   * A value indicating whether this entry supports linked resources as child items or not.
   */
  private boolean isSupportingLinkedResources = true;
  
  /**
   * Gets the preference constants.
   */
  protected TortoisePreferenceConstants getPreferenceConstants()
  {
    return this.preferenceConstants;
  }

  /**
   * Sets the preference constants.
   * @param value The preference constants.
   */
  protected void setPreferenceConstants(TortoisePreferenceConstants value)
  {
    this.preferenceConstants = value;
  }
  
  /**
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  protected final String getWorkingCopyRoot(final IPath path)
  {
    TortoiseWorkingCopyDetect workingCopyDetect = new TortoiseWorkingCopyDetect();
    return workingCopyDetect.getWorkingCopyRoot(path, getPreferenceConstants().getWorkingCopyFolderName());
  }
  
  /**
   * Gets all selected resources of the specified execution event.
   * 
   * @return A collection containing all selected resources.
   */
  protected Set<IResource> getSelectedResources(boolean includeLinkedResources)
  {
    final Set<IResource> selectedResources = this.getEnvironment().getSelectedResources();
    final Set<IResource> result = new HashSet<IResource>(selectedResources);
    if (includeLinkedResources == true)
    {
      final String workingCopyRoot = this.getEnvironment().getWorkingCopyRoot();
      if (workingCopyRoot != null)
      {
        for (IResource resource : selectedResources)
        {
          IContainer container = resource.getAdapter(IContainer.class);
          if (container != null)
          {
            result.addAll(this.getLinkedResourcesOfContainer(container, workingCopyRoot));
          }
        }
      }
    }

    return result;
  }
  
  /**
   * Gets all linked resources in the specified container which have the same
   * working copy root.
   * 
   * @param container
   *          The container which is used for searching for linked resources.
   * @param workingCopyRoot
   *          The root path to the working copy folder of the container.
   * @return A HashSet containing all linked resources of the specified
   *         container.
   */
  private Set<IResource> getLinkedResourcesOfContainer(final IContainer container, final String workingCopyRoot)
  {
    final Set<IResource> linkedResources = new HashSet<IResource>();
    try
    {
      for (IResource member : container.members())
      {
        final String memberWorkingCopyRoot = this.getWorkingCopyRoot(member.getLocation());
        if (member.isLinked() && (workingCopyRoot.equals(memberWorkingCopyRoot)))
        {
          linkedResources.add(member);

          // Check if there are also linked resourced within the linked resource
          // container
          final IAdaptable adaptable = member;
          final IContainer childContainer = adaptable.getAdapter(IContainer.class);
          if (childContainer != null)
          {
            this.getLinkedResourcesOfContainer(childContainer, workingCopyRoot);
          }
        }
      }
    }
    catch (CoreException e)
    {
      e.printStackTrace();
    }

    return linkedResources;
  }
  
  /**
   * Gets the label of the instance.
   * 
   * @return the label
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Sets the label of the instance.
   * 
   * @param value
   *          the label to set
   */
  public void setLabel(final String value)
  {
    this.label = value;
  }

  /**
   * Sets the icon path.
   * 
   * @param value
   *          the iconPath to set
   */
  public void setIconPath(final String value)
  {
    final String iconFolder = "icons/";
    this.icon = contextquickie.Activator.getImageDescriptor(iconFolder + value);
    if (this.icon == null)
    {
      throw new IllegalArgumentException(value);
    }
  }

  /**
   * Gets the menu ID.
   * 
   * @return the menuId
   */
  public long getMenuId()
  {
    return menuId;
  }

  /**
   * Sets the menu ID.
   * 
   * @param value
   *          the menuId to set
   */
  public void setMenuId(final long value)
  {
    this.menuId = value;
  }

  /**
   * Gets the command which will be passed to the Tortoise program.
   */
  public String getCommand()
  {
    return entryCommand;
  }

  /**
   * Sets the command which will be passed to the Tortoise program.
   */
  public void setCommand(final String value)
  {
    this.entryCommand = value;
  }

  /**
   * Gets a value indicating whether the path must be passed to the Tortoise program.
   */
  public Boolean getEntryRequiresPath()
  {
    return entryRequiresPath;
  }

  /**
   * Sets a value indicating whether the path must be passed to the Tortoise program.
   */
  public void setEntryRequiresPath(final Boolean value)
  {
    this.entryRequiresPath = value;
  }

  /**
   * Sets a value indicating whether this entry is visible within an working copy.
   */
  public void setIsVisibleInWorkingCopy(final boolean value)
  {
    this.isVisibleInWorkingCopy = value;
  }

  /**
   * Gets a value indicating whether this entry is visible within an working copy.
   */
  public boolean isVisibleInWorkingCopy()
  {
    return this.isVisibleInWorkingCopy;
  }

  /**
   * Gets the maximum number of selected folders to display this entry.
   */
  public int getMaxFolderCount()
  {
    return this.maxFolderCount;
  }

  /**
   * Sets the maximum number of selected folders to display this entry.
   */
  public void setMaxFolderCount(final int value)
  {
    this.maxFolderCount = value;
  }

  /**
   * Gets the maximum number of selected files to display this entry.
   */
  public int getMaxFileCount()
  {
    return this.maxFileCount;
  }

  /**
   * Sets the maximum number of selected files to display this entry.
   */
  public void setMaxFileCount(final int value)
  {
    this.maxFileCount = value;
  }
  
  /**
   * Gets the maximum number of selected items to display this entry.
   */
  public int getMaxItemsCount()
  {
    return this.maxItemsCount;
  }

  /**
   * Sets the maximum number of selected items to display this entry.
   */
  public void setMaxItemsCount(final int value)
  {
    this.maxItemsCount = value;
  }
  
  /**
   * Gets the minimum number of selected folders to display this entry.
   */
  public int getMinFolderCount()
  {
    return this.minFolderCount;
  }

  /**
   * Sets the minimum number of selected folders to display this entry.
   */
  public void setMinFolderCount(final int value)
  {
    this.minFolderCount = value;
  }

  /**
   * Gets the minimum number of selected files to display this entry.
   */
  public int getMinFileCount()
  {
    return this.minFileCount;
  }

  /**
   * Sets the minimum number of selected files to display this entry.
   */
  public void setMinFileCount(final int value)
  {
    this.minFileCount = value;
  }
  
  /**
   * Gets the minimum number of selected items to display this entry.
   */
  public int getMinItemsCount()
  {
    return this.minItemsCount;
  }

  /**
   * Sets the minimum number of selected items to display this entry.
   */
  public void setMinItemsCount(final int value)
  {
    this.minItemsCount = value;
  }

  /**
   * Gets a value indicating whether this entry is visible without a working copy.
   */
  public boolean isVisibleWithoutWorkingCopy()
  {
    return this.isVisibleWithoutWorkingCopy;
  }

  /**
   * Sets a value indicating whether this entry is visible without a working copy.
   */
  public void setVisibleWithoutWorkingCopy(final boolean value)
  {
    this.isVisibleWithoutWorkingCopy = value;
  }

  /**
   * Gets a value indicating whether this entry supports linked resources as child items or not.
   */
  public Boolean isSupportingLinkedResources()
  {
    return this.isSupportingLinkedResources;
  }

  /**
   * Sets a value indicating whether this entry supports linked resources as child items or not.
   */
  public void setSupportingLinkedResources(boolean value)
  {
    this.isSupportingLinkedResources = value;
  }

  /**
   * Gets the first parameter which will be passed to the Tortoise program.
   */
  public String getParameter1()
  {
    return  this.parameter1;
  }

  /**
   * Sets the first parameter which will be passed to the Tortoise program.
   */
  public void setParameter1(String value)
  {
    this.parameter1 = value;
  }

  /**
   * Gets the help text of the instance.
   * 
   * @return the help text
   */
  public String getHelpText()
  {
    return helpText;
  }
  
  /**
   * Sets the help text of the instance.
   * 
   * @param helpText
   *          the help text to set
   */
  public void setHelpText(String helpText)
  {
    this.helpText = helpText;
  }

  @Override
  public ImageDescriptor getImageDescriptor()
  {
    return this.icon;
  }
  
  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    boolean isVisible = true;
    if (super.isVisible(environment))
    {
      this.environment = TortoiseEnvironment.class.cast(environment);
      final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
      final boolean workingCopyDetection = preferenceStore.getBoolean(
          getPreferenceConstants().getWorkingCopyDetection());
      if (workingCopyDetection == true)
      {
        if ((this.isVisibleInWorkingCopy() == true) && 
            (this.isVisibleWithoutWorkingCopy() == false) &&
            (this.environment.isWorkingCopyFound() == false))
        {
          isVisible = false;
        }
        if ((this.isVisibleInWorkingCopy() == false) && 
            (this.isVisibleWithoutWorkingCopy() == true) &&
            (this.environment.isWorkingCopyFound() == true))
        {
          isVisible = false;
        }
      }
      
      if ((this.environment.getSelectedResources().size() > this.getMaxItemsCount()) ||
          (this.environment.getSelectedFiles().size() > this.getMaxFileCount()) ||
          (this.environment.getSelectedDirectories().size() > this.getMaxFolderCount()))
      {
        isVisible = false;
      }
      else if ((this.environment.getSelectedResources().size() < this.getMinItemsCount()) ||
               (this.environment.getSelectedFiles().size() < this.getMinFileCount()) ||
               (this.environment.getSelectedDirectories().size() < this.getMinFolderCount()))
      {
        isVisible = false;
      }
    }
    
    return isVisible;
  }

  @Override
  public void executeCommand()
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = this.getCommand();
    
    final String executable = Activator.getDefault().getPreferenceStore().getString(getPreferenceConstants().getPath());
    
    final boolean supportsLinkedResources = this.isSupportingLinkedResources();;

    arguments.add("/command:" + command);
    Set<IResource> currentResources = null;
    if (this.getEntryRequiresPath())
    {
      if (supportsLinkedResources == true)
      {
        currentResources = this.getSelectedResources(supportsLinkedResources);
      }
      else
      {
        currentResources = this.getEnvironment().getSelectedResources();
      }
      
      List<String> pathArguments = new ArrayList<String>();
      for (IResource resource : currentResources)
      {
        pathArguments.add(resource.getLocation().toOSString());
      }
      final String pathArgument = String.join("*", pathArguments);
      arguments.add("/path:" + StringUtil.quoteString(pathArgument));
    }

    final String parameter1 = this.getParameter1();
    if (parameter1 != null)
    {
      arguments.add(parameter1);
    }
    
    new ProcessWrapper().executeCommand(executable, currentResources, arguments);
  }

  protected void executeApplyPatchCommand()
  {
    if (this.getEnvironment().getSelectedResources().isEmpty() == false)
    {
      IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
      final String command = preferenceStore.getString(getPreferenceConstants().getMergePath());
      final List<String> arguments = new ArrayList<String>();
      final IResource resource = this.getEnvironment().getSelectedResources().iterator().next(); 
      arguments.add("/patchpath:" + resource.getLocation().toString());
      new ProcessWrapper().executeCommand(command, null, arguments);
    }
  }

  protected void executeDiffLaterCommand()
  {
    final String registryUserDirectory = getPreferenceConstants().getRegistryUserDirectory();
    final IResource resource = this.getEnvironment().getSelectedResources().stream().findFirst().orElse(null);
    if ((resource != null) && (resource.getType() == IResource.FILE))
    {
      Registry registry = new Registry();
      registry.writeKey(registryUserDirectory, "DiffLater", resource.getLocation().toOSString());
    }
  }

  protected void executeDiffTwoFilesCommand(final String leftSide, final String rightSide)
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = Activator.getDefault().getPreferenceStore().getString(getPreferenceConstants().getPath());

    arguments.add("/command:diff");
    arguments.add("/path:" + StringUtil.quoteString(leftSide));
    arguments.add("/path2:" + StringUtil.quoteString(rightSide));
    new ProcessWrapper().executeCommand(command, new ContextMenuEnvironment().getSelectedResources(), arguments);
  }

  protected void executeDiffWithLeftSideCommand()
  {
    String leftSide = this.getLeftSideForDiff();
    String rightSide = this.getEnvironment().getSelectedResources().iterator().next().getLocation().toOSString();
    this.executeDiffTwoFilesCommand(leftSide, rightSide);
  }

  protected String getLeftSideForDiff()
  {
    return new Registry().readStringValue(getPreferenceConstants().getRegistryUserDirectory(), "DiffLater", null);
  }

  /**
   * Gets the current Tortoise environment.
   */
  public TortoiseEnvironment getEnvironment()
  {
    return this.environment;
  }
}
