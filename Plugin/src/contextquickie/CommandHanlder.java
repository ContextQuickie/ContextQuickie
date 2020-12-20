package contextquickie;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import contextquickie.base.AbstractMenuEntry;

public class CommandHanlder extends AbstractHandler
{
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    Object contextMenuObject = event.getObjectParameterForExecution(AbstractMenuEntry.ParameterName);
    if (contextMenuObject != null)
    {
      if (AbstractMenuEntry.class.isInstance(contextMenuObject))
      {
        AbstractMenuEntry entry = AbstractMenuEntry.class.cast(contextMenuObject);
        entry.executeCommand();
      }
    }

    ObjectParameterConverter.clearEntries();
    return null;
  }
}
