package contextquickie.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

import contextquickie.tools.ContextMenuEnvironment;

public abstract class AbstractMenuEntry
{
  private final List<AbstractMenuEntry> childEntries = new ArrayList<AbstractMenuEntry>();
  
  public static String ParameterName = AbstractMenuEntry.class.getCanonicalName();

  public abstract void executeCommand();
  
  public abstract ImageDescriptor getImageDescriptor();
  
  public abstract String getLabel();
  
  public Boolean isSeparator()
  {
    return false;
  }

  public Boolean isVisible(ContextMenuEnvironment environment)
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
