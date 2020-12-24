package contextquickie.tortoise.git.entries;

import java.util.Iterator;

import org.eclipse.core.runtime.IPath;

import contextquickie.tools.ContextMenuEnvironment;

public class DiffTwoFiles extends AbstractTortoiseGitEntry
{
  private String leftSideForCompare;
  
  private String rightSideForCompare;
  
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000010000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 176;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffTwoFiles(String iconPath)
  {
    super(MenuTextIdentifier, "Diff");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxItemsCount(2);
    this.setMinItemsCount(2);
    this.setMaxFolderCount(0);
  }

  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
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
    this.executeDiffTwoFilesCommand(this.leftSideForCompare, this.rightSideForCompare);
  }
}
