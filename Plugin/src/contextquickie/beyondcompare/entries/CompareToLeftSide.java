package contextquickie.beyondcompare.entries;

import java.io.File;

import org.eclipse.core.runtime.IPath;

import contextquickie.beyondcompare.BeyondCompare;
import contextquickie.beyondcompare.BeyondCompareSavedLeft;
import contextquickie.tools.ContextMenuEnvironment;

public class CompareToLeftSide extends AbstractBeyondCompareEntry
{
  final BeyondCompare beyondCompare = new BeyondCompare();
  
  private String label;
  
  private IPath selectedPath;

  @Override
  public Boolean isVisible(ContextMenuEnvironment environment)
  {
    Boolean isVisible = false;
    
    this.beyondCompare.readRegistry();
    final BeyondCompareSavedLeft savedLeftType = this.beyondCompare.getSavedLeftType();
    
    if ((savedLeftType == BeyondCompareSavedLeft.File) &&
        (environment.getSelectedFiles().size() == 1) &&
        (environment.getSelectedDirectories().isEmpty()))
    {
      isVisible = true;
      this.selectedPath = environment.getSelectedFiles().iterator().next();
    }
    else if ((savedLeftType == BeyondCompareSavedLeft.Directory) &&
        (environment.getSelectedDirectories().size() == 1) &&
        (environment.getSelectedFiles().isEmpty()))
    {
      isVisible = true;
      this.selectedPath = environment.getSelectedDirectories().iterator().next();
    }
    
    if (isVisible)
    {
      final String savedLeft = this.beyondCompare.getSavedLeft();
      {
        final String filename = new File(savedLeft).getName();
        this.label = "Compare to " + filename;
      }
    }
    
    return isVisible;
  }

  public CompareToLeftSide()
  {
    super("Compare.png");
  }

  @Override
  public void executeCommand()
  {
    String left = this.beyondCompare.getSavedLeft();
    String right = this.selectedPath.toOSString();
    BeyondCompare.compare(left, right);
  }

  @Override
  public String getLabel()
  {
    return this.label;
  }
}
