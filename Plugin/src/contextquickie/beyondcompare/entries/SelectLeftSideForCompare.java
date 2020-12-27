package contextquickie.beyondcompare.entries;

import java.io.File;

import org.eclipse.core.runtime.IPath;

import contextquickie.beyondcompare.BeyondCompare;
import contextquickie.beyondcompare.BeyondCompareSavedLeft;
import contextquickie.tools.ContextMenuEnvironment;

public class SelectLeftSideForCompare extends AbstractBeyondCompareEntry
{
  private String label;
  
  private IPath selectedPath;
  
  /**
   * Default constructor. 
   */
  public SelectLeftSideForCompare()
  {
    super("SelectLeftFile.png");
  }

  @Override
  public void executeCommand()
  {
    File file = this.selectedPath.toFile();
    final BeyondCompare bc = new BeyondCompare();
    bc.setSavedLeft(this.selectedPath.toOSString());
    if (file.isDirectory())
    {
      bc.setSavedLeftType(BeyondCompareSavedLeft.Directory);
      bc.writeRegistry();
    }
    else if (file.isFile())
    {
      bc.setSavedLeftType(BeyondCompareSavedLeft.File);
      bc.writeRegistry();
    }
  }

  @Override
  public String getLabel()
  {
    return this.label;
  }
  
  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    Boolean isVisible = false;
    if ((environment.getSelectedFiles().size() == 1) && environment.getSelectedDirectories().isEmpty())
    {
      this.selectedPath = environment.getSelectedFiles().iterator().next();
      this.label = "Select Left File for Compare";
      isVisible = true;
    }
    else if ((environment.getSelectedDirectories().size() == 1) && environment.getSelectedFiles().isEmpty())
    {
      this.selectedPath = environment.getSelectedDirectories().iterator().next();
      this.label = "Select Left Folder for Compare";
      isVisible = true;
    }
    
    return isVisible;
  }
}
