package contextquickie.tortoise;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.Registry;

/**
 * Base class for storing the registry value for the "diff later" command.
 */
public class AbstractTortoiseDiffLaterCommand extends AbstractTortoiseHandler
{
  @Override
  public final Object execute(final ExecutionEvent event)
  {
    final String registryUserDirectory = this.getPreferenceConstants().getRegistryUserDirectory();
    final IResource resource = new ContextMenuEnvironment().getSelectedResources().stream().findFirst().orElse(null);
    if ((resource != null) && (resource.getType() == IResource.FILE))
    {
      Registry registry = new Registry();
      registry.writeKey(registryUserDirectory, "DiffLater", resource.getLocation().toOSString());
    }

    return null;
  }
}
