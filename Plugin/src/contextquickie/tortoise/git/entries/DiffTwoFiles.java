package contextquickie.tortoise.git.entries;

import java.util.Iterator;

import org.eclipse.core.runtime.IPath;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.git.TortoiseGitDiffTwoFilesCommand;

public class DiffTwoFiles extends Diff
{
  private String leftSideForCompare;
  
  private String rightSideForCompare;
  
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000010000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffTwoFiles(String iconPath)
  {
    super(iconPath);
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxItemsCount(2);
    this.setMinItemsCount(2);
    this.setMaxFolderCount(0);
  }

  @Override
  public Boolean isVisible(ContextMenuEnvironment environment)
  {
    if (super.isVisible(environment))
    {
      Iterator<IPath> iterator = environment.getSelectedFiles().iterator();
      this.leftSideForCompare = iterator.next().toOSString();
      this.rightSideForCompare = iterator.next().toOSString();
      return true;
    }
    
    return false;
  }

  @Override
  public void executeCommand()
  {
    new TortoiseGitDiffTwoFilesCommand().execute(this.leftSideForCompare, this.rightSideForCompare);
  }
}
