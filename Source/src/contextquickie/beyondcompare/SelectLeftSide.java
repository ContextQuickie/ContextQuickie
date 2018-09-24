package contextquickie.beyondcompare;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author ContextQuickie
 * 
 *         Class which implements the "Select Left Side" command.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SelectLeftSide extends AbstractHandler
{
  /**
   * The name of command parameter which stores the left side for comparison.
   */
  public static final String PathParameterName = "Path";
  
  @Override
  public final Object execute(final ExecutionEvent event) throws ExecutionException
  {
    String path = event.getParameter(PathParameterName);

    File file = new File(path);
    final BeyondCompare bc = new BeyondCompare();
    bc.setSavedLeft(path);
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

    return null;
  }
}
