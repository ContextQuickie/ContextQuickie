package contextquickie.tortoise.hg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tortoise.AbstractTortoiseCommand;
import contextquickie.tortoise.TortoiseMenuConstants;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all TortoiseHg commands based on the passed
 *         parameters.
 */
public class TortoiseHgCommand extends AbstractTortoiseCommand
{
  /**
   * Default constructor.
   */
  public TortoiseHgCommand()
  {
    this.setPreferenceConstants(PreferenceConstants.TORTOISE_HG);
  }
  
  @Override
  public final Object execute(final ExecutionEvent event)
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = event.getParameter(TortoiseMenuConstants.COMMAND_ID);
    final String parameter1 = event.getParameter(TortoiseMenuConstants.PARAMETER_1_ID);
    final String executable = Activator.getDefault().getPreferenceStore().getString(this.getPreferenceConstants().getPath());
    
    arguments.add(command);
    Set<IResource> currentResources = new ContextMenuEnvironment().getSelectedResources();
    if (currentResources.size() == 1)
    {
      IPath selectedPath = currentResources.iterator().next().getLocation();
      File workingCopyRoot = new File (this.getWorkingCopyRoot(selectedPath)).getParentFile();
      if ((workingCopyRoot != null) && (workingCopyRoot.isDirectory()))
      {
        arguments.add("--repository");
        arguments.add(StringUtil.quoteString(workingCopyRoot.getAbsolutePath()));
      }

      if (parameter1 != null)
      {
        arguments.add(StringUtil.quoteString(selectedPath.toOSString()));
      }

      new ProcessWrapper().executeCommand(executable, currentResources, arguments);
    }

    return null;
  }
}
