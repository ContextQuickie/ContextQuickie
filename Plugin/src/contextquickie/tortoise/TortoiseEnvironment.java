package contextquickie.tortoise;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import contextquickie.tools.ContextMenuEnvironment;

/**
 * Class for storing the current Tortoise environment configuration.
 * 
 * @author ContextQuickie
 */
public class TortoiseEnvironment
{
  /**
   * A value indicating whether a working copy has been found or not.
   */
  private boolean workingCopyFound;

  /**
   * The root directory of the working copy.
   */
  private String workingCopyRoot;
  
  /**
   * The context menu environment of this instance.
   */
  private final ContextMenuEnvironment contextMenuEnvironment;
  
  public TortoiseEnvironment(ContextMenuEnvironment contextMenuEnvironment)
  {
    this.contextMenuEnvironment = contextMenuEnvironment;
  }

  /**
   * @return The number of selected files.
   */
  public Set<IPath> getSelectedFiles()
  {
    return this.contextMenuEnvironment.getSelectedFiles();
  }
  
  public Set<IPath> getSelectedDirectories()
  {
    return this.contextMenuEnvironment.getSelectedDirectories();
  }

  /**
   * @return The number of selected files.
   */
  public long getSelectedFilesCount()
  {
    return this.contextMenuEnvironment.getSelectedFiles().size();
  }

  /**
   * @return The number of selected folders.
   */
  public long getSelectedFoldersCount()
  {
    return this.contextMenuEnvironment.getSelectedDirectories().size();
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
    return this.contextMenuEnvironment.getSelectedResources();
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
