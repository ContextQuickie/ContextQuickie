package contextquickie.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.tools.ContextMenuEnvironment;

/**
 * Base class for all context menu entries.
 */
public abstract class AbstractMenuEntry
{
  private final List<AbstractMenuEntry> childEntries = new ArrayList<AbstractMenuEntry>();
  
  public static String ParameterName = AbstractMenuEntry.class.getCanonicalName();

  /**
   * Executes the command of this entry. 
   */
  public abstract void executeCommand();
  
  /**
   * Gets the image descriptor of this entry.
   */
  public abstract ImageDescriptor getImageDescriptor();

  /**
   * Gets the label (text) of this entry.
   */
  public abstract String getLabel();

  /**
   * Gets a value indicating whether this entry is a separator or not.
   */
  public Boolean isSeparator()
  {
    return false;
  }

  
  /**
   * Checks whether the entry is visible in the specific environment.
   * @param environment
   *      The current context menu environment.
   * @return A value indicating whether the entry is visible in the specific environment.
   */
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    return true;
  }

  /**
   * Gets the child entries of this entry.
   * 
   * @return the childEntries
   */
  public List<AbstractMenuEntry> getChildEntries()
  {
    return this.childEntries;
  }
  
  /**
   * Adds a child entry to this entry.
   * 
   * @param entry
   *      The entry to add.
   */
  public void addChildEntry(AbstractMenuEntry entry)
  {
    this.childEntries.add(entry);
  }
}
