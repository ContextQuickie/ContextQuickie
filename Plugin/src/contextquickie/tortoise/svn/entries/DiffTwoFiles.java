package contextquickie.tortoise.svn.entries;

import java.util.Iterator;

import org.eclipse.core.runtime.IPath;

public class DiffTwoFiles extends AbstractTortoiseSvnEntry
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
  public void executeCommand()
  {
    Iterator<IPath> iterator = this.getEnvironment().getSelectedFiles().iterator();
    this.leftSideForCompare = iterator.next().toOSString();
    this.rightSideForCompare = iterator.next().toOSString();
    this.executeDiffTwoFilesCommand(this.leftSideForCompare, this.rightSideForCompare);
  }
}
