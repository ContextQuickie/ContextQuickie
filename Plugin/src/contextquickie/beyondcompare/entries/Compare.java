package contextquickie.beyondcompare.entries;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IPath;

import contextquickie.beyondcompare.BeyondCompare;
import contextquickie.tools.ContextMenuEnvironment;

public class Compare extends AbstractBeyondCompareEntry
{
  private Set<IPath> selectedPaths;
  
  public Compare()
  {
    super("Compare.png");
  }

  @Override
  public void executeCommand()
  {
    Iterator<IPath> iterator = this.selectedPaths.iterator();
    String left = iterator.next().toOSString();
    String right = iterator.next().toOSString();
    BeyondCompare.compare(left, right);
  }

  @Override
  public String getLabel()
  {
    return "Compare";
  }

  @Override
  public Boolean isVisible(ContextMenuEnvironment environment)
  {
    Boolean isVisible = false;
    if ((environment.getSelectedFiles().size() == 2) && environment.getSelectedDirectories().isEmpty())
    {
      this.selectedPaths = environment.getSelectedFiles();
      isVisible = true;
    }
    else if ((environment.getSelectedDirectories().size() == 2) && environment.getSelectedFiles().isEmpty())
    {
      this.selectedPaths = environment.getSelectedDirectories();
      isVisible = true;
    }
    
    return isVisible;
  }
}
