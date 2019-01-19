package contextquickie.teamprovider.svn;

import java.io.File;

import org.apache.subversion.javahl.SVNClient;

/**
 * @author ContextQuickie.
 *
 */
public class WorkingCopy
{
  private File currentPath;
  
  private File root;
  
  public WorkingCopy(File path)
  {
    this.currentPath = path;
  }
  
  public File getRoot()
  {
    if (this.root == null)
    {
      String adminDirectoryName = new SVNClient().getAdminDirectoryName();
      File currentDir = currentPath;
      while (this.root == null && currentDir != null) 
      {
        File adminDirectory = new File(currentDir, adminDirectoryName);
        if (adminDirectory.exists() && adminDirectory.isDirectory())
        {
          this.root = currentDir;
        }
        else
        {
          currentDir = currentDir.getParentFile();
        }
      }
    }
    
    return this.root;
  }
}
