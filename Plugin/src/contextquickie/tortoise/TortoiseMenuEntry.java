package contextquickie.tortoise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.base.AbstractMenuEntry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author ContextQuickie
 *
 *         Menu entry configuration for Tortoise.
 */
public class TortoiseMenuEntry extends AbstractMenuEntry
{
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
   * Custom parameters for this entry.
   */
  private Map<String, Object> customParameters;

  /**
   * An interface for an additional visibility checks.
   */
  private List<BiPredicate<TortoiseMenuEntry, TortoiseEnvironment>> visibilityCheckers = new ArrayList<BiPredicate<TortoiseMenuEntry, TortoiseEnvironment>>();

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
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setLabel(final String value)
  {
    this.label = value;
    return this;
  }

  /**
   * Sets the icon path.
   * 
   * @param value
   *          the iconPath to set
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setIconPath(final String value)
  {
    final String iconFolder = "icons/";
    this.icon = contextquickie.Activator.getImageDescriptor(iconFolder + value);
    if (this.icon == null)
    {
      throw new IllegalArgumentException(value);
    }
    return this;
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
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMenuId(final long value)
  {
    this.menuId = value;
    return this;
  }

  /**
   * @return The command which will be passed to the Tortoise program.
   */
  public String getCommand()
  {
    return entryCommand;
  }

  /**
   * @param value
   *          The command which will be passed to the Tortoise program.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setCommand(final String value)
  {
    this.entryCommand = value;
    return this;
  }

  /**
   * @return A value indicating whether the path must be passed to the Tortoise
   *         program.
   */
  public Boolean getEntryRequiresPath()
  {
    return entryRequiresPath;
  }

  /**
   * @param value
   *          A value indicating whether the path must be passed to the Tortoise
   *          program.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setEntryRequiresPath(final Boolean value)
  {
    this.entryRequiresPath = value;
    return this;
  }

  /**
   * @param value
   *          A value indicating whether this entry is visible within an working
   *          copy.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setIsVisibleInWorkingCopy(final boolean value)
  {
    this.isVisibleInWorkingCopy = value;
    return this;
  }

  /**
   * 
   * @return A value indicating whether this entry is visible within an working
   *         copy.
   */
  public boolean isVisibleInWorkingCopy()
  {
    return this.isVisibleInWorkingCopy;
  }

  /**
   * @return The maximum number of selected folders to display this entry.
   */
  public int getMaxFolderCount()
  {
    return this.maxFolderCount;
  }

  /**
   * @param value
   *          The maximum number of selected folders to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMaxFolderCount(final int value)
  {
    this.maxFolderCount = value;
    return this;
  }

  /**
   * @return The maximum number of selected files to display this entry.
   */
  public int getMaxFileCount()
  {
    return this.maxFileCount;
  }

  /**
   * @param value
   *          The maximum number of selected files to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMaxFileCount(final int value)
  {
    this.maxFileCount = value;
    return this;
  }
  
  /**
   * @return The maximum number of selected items to display this entry.
   */
  public int getMaxItemsCount()
  {
    return this.maxItemsCount;
  }

  /**
   * @param value
   *          The maximum number of selected items to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMaxItemsCount(final int value)
  {
    this.maxItemsCount = value;
    return this;
  }
  
  /**
   * @return The minimum number of selected folders to display this entry.
   */
  public int getMinFolderCount()
  {
    return this.minFolderCount;
  }

  /**
   * @param value
   *          The minimum number of selected folders to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMinFolderCount(final int value)
  {
    this.minFolderCount = value;
    return this;
  }

  /**
   * @return The minimum number of selected files to display this entry.
   */
  public int getMinFileCount()
  {
    return this.minFileCount;
  }

  /**
   * @param value
   *          The minimum number of selected files to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMinFileCount(final int value)
  {
    this.minFileCount = value;
    return this;
  }
  
  /**
   * @return The minimum number of selected items to display this entry.
   */
  public int getMinItemsCount()
  {
    return this.minItemsCount;
  }

  /**
   * @param value
   *          The minimum number of selected items to display this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setMinItemsCount(final int value)
  {
    this.minItemsCount = value;
    return this;
  }

  /**
   * @return A value indicating whether this entry is visible without a working.
   *         copy.
   */
  public boolean isVisibleWithoutWorkingCopy()
  {
    return this.isVisibleWithoutWorkingCopy;
  }

  /**
   * @param value
   *          A value indicating whether this entry is visible without a working.
   *          copy.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setVisibleWithoutWorkingCopy(final boolean value)
  {
    this.isVisibleWithoutWorkingCopy = value;
    return this;
  }

  /**
   * @return A value indicating whether this entry supports linked resources as child items or not.
   */
  public Boolean isSupportingLinkedResources()
  {
    return this.isSupportingLinkedResources;
  }

  /**
   * @param value
   *          A value indicating whether this entry supports linked resources as child items or not.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setSupportingLinkedResources(boolean value)
  {
    this.isSupportingLinkedResources = value;
    return this;
  }

  /**
   * @return The first parameter which will be passed to the Tortoise program.
   */
  public String getParameter1()
  {
    return  this.parameter1;
  }

  /**
   * @param value
   *          The first parameter which will be passed to the Tortoise program.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setParameter1(String value)
  {
    this.parameter1 = value;
    return this;
  }

  /**
   * @param value
   *          The visibility checker for this instance.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry addVisibilityChecker(BiPredicate<TortoiseMenuEntry, TortoiseEnvironment> value)
  {
    this.visibilityCheckers.add(value);
    return this;
  }

  /**
   * @param environment 
   *          The current environment of the context menu.
   * @return True if the entry is visible, otherwise false.
   */
  public boolean isVisible(TortoiseEnvironment environment)
  {
    return ! (this.visibilityCheckers.stream().anyMatch(predicate -> predicate.test(this, environment) == false));
  }

  /**
   * @return The custom parameters for this entry.
   */
  public Map<String, Object> getCustomParameters()
  {
    return customParameters;
  }

  /**
   * @param value
   *          The custom parameters for this entry.
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setCustomParameters(Map<String, Object> value)
  {
    this.customParameters = value;
    return this;
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
   * @param value
   *          the help text to set
   * @return The instance with the changed value.
   */
  public TortoiseMenuEntry setHelpText(String helpText)
  {
    this.helpText = helpText;
    return this;
  }

  @Override
  public ImageDescriptor getImageDescriptor()
  {
    return this.icon;
  }

  @Override
  public void executeCommand()
  {
    // TODO: This class should be abstract in the end so the method is not required anymore.
    throw new NotImplementedException();
  }
}
