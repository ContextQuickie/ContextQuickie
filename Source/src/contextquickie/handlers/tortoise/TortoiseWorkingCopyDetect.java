package contextquickie.handlers.tortoise;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a working copy is present or not. It
 *         is used to show/hide the Tortoise add-in context menu entries.
 */
public class TortoiseWorkingCopyDetect
{
  public final boolean test(final Set<IResource> receiver, final String workingCopyFolderName)
  {
    return receiver.stream()
        .filter(r -> (r.getType() & (IResource.FOLDER | IResource.PROJECT | IResource.FILE)) != IResource.NONE)
        .anyMatch(r -> this.getWorkingCopyRoot(r.getLocation(), workingCopyFolderName) != null);
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
    
    // Filter for retrieving only directories with the expected WC name
    FileFilter filter = new FileFilter()
    {
      @Override
      public boolean accept(final File dir)
      {
        return dir.isDirectory() && workingCopyFolderName.equals(dir.getName());
      }
    };

    while ((currentPath != null) && (currentPath.isDirectory()))
    {
      final File[] childItems = currentPath.listFiles(filter);
      if ((childItems != null) && (childItems.length > 0))
      {
        return childItems[0].getAbsolutePath();
      }
      currentPath = currentPath.getParentFile();
    }

    return null;
  }
}
