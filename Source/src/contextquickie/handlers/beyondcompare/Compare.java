package contextquickie.handlers.beyondcompare;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author ContextQuickie
 * 
 *         Class to compare two selected items using Beyond Compare.
 *
 */
public class Compare extends AbstractHandler
{
  public static final String LeftSideParameterName = "LeftSide";
  
  public static final String RightSideParameterName = "RightSide";

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException
  {
    BeyondCompare.compare(event.getParameter(LeftSideParameterName), event.getParameter(RightSideParameterName));
    return null;
  }
}
