package contextquickie.tortoise;

import java.io.File;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

/**
 * Property tester which checks if a working copy is present or not. 
 * It is used to show/hide the Tortoise add-in context menu entries.
 */
public class TortoiseWorkingCopyDetect
{
  private String workingCopyRoot;
  
  /**
   * Checks if any of the resources is a file system object within a working copy.
   * @param resources
   *      The resources which will be checked.
   * @param workingCopyFolderName
   *      The name of the folder indicating a working copy.
   * @return
   */
  public final boolean isAnyResourceInWorkingCopy(final Set<IResource> resources, final String workingCopyFolderName)
  {
    return resources.stream()
        .filter(r -> (r.getType() & (IResource.FOLDER | IResource.PROJECT | IResource.FILE)) != IResource.NONE)
        .anyMatch(r -> this.getWorkingCopyRoot(r.getLocation(), workingCopyFolderName) != null);
  }

  /**
   * Gets the working copy root.
   */
  public String getWorkingCopyRoot()
  {
    return this.workingCopyRoot;
  }

  /**
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @param workingCopyFolderName
   *          The folder which indicates a working copy.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  public String getWorkingCopyRoot(final IPath path, final String workingCopyFolderName)
  {
    File currentPath = path.toFile();
    if (currentPath.isFile())
    {
      currentPath = currentPath.getParentFile();
    }

    while ((currentPath != null) && (currentPath.isDirectory()) && (this.workingCopyRoot == null))
    {
      final File[] childItems = currentPath.listFiles(
        (dir) -> dir.isDirectory() && workingCopyFolderName.equals(dir.getName()));
      if ((childItems != null) && (childItems.length > 0))
      {
        this.workingCopyRoot = childItems[0].getAbsolutePath();
      }
      currentPath = currentPath.getParentFile();
    }

    return this.workingCopyRoot;
  }
}
