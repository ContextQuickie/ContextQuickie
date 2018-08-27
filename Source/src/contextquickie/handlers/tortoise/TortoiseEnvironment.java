package contextquickie.handlers.tortoise;

import java.util.Set;

import org.eclipse.core.resources.IResource;

/**
 * @author ContextQuickie
 *
 *         Class for storing the current environment configuration.
 */
public class TortoiseEnvironment
{
  /**
   * The number of selected files.
   */
  private long selectedFilesCount;
  
  /**
   * The number of selected folders.
   */
  private long selectedFoldersCount;
  
  /**
   * A value indicating whether a working copy has been found or not.
   */
  private boolean workingCopyFound;
  
  /**
   * A list containing all selected resources.
   */
  private Set<IResource> selectedResources;
  
  /**
   * The root directory of the working copy.
   */
  private String workingCopyRoot;

  /**
   * @return The number of selected files.
   */
  public long getSelectedFilesCount()
  {
    return this.selectedFilesCount;
  }

  /**
   * @param value
   *          The number of selected files.
   */
  public void setSelectedFilesCount(final long value)
  {
    this.selectedFilesCount = value;
  }

  /**
   * @return The number of selected folders.
   */
  public long getSelectedFoldersCount()
  {
    return selectedFoldersCount;
  }

  /**
   * @param value The number of selected folders.
   */
  public void setSelectedFoldersCount(final long value)
  {
    this.selectedFoldersCount = value;
  }

  /**
   * @return A value indicating whether a working copy has been found or not.
   */
  public boolean isWorkingCopyFound()
  {
    return workingCopyFound;
  }

  /**
   * @param value A value indicating whether a working copy has been found or not.
   */
  public void setWorkingCopyFound(final boolean value)
  {
    this.workingCopyFound = value;
  }

  /**
   * @return A list containing all selected resources.
   */
  public Set<IResource> getSelectedResources()
  {
    return selectedResources;
  }

  /**
   * @param value A list containing all selected resources.
   */
  public void setSelectedResources(final Set<IResource> value)
  {
    this.selectedResources = value;
  }

  /**
   * @param value The working copy root.
   */
  public void setWorkingCopyRoot(String value)
  {
    this.workingCopyRoot = value;
  }

  /**
   * @return The working copy root.
   */
  public String getWorkingCopyRoot()
  {
    return workingCopyRoot;
  }
}
