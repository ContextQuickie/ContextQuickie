package contextquickie.tortoise.hg.entries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tortoise.TortoiseMenuEntry;

public abstract class AbstractTortoiseHgEntry extends TortoiseMenuEntry
{
  static
  {
    setPreferenceConstants(PreferenceConstants.TORTOISE_HG);
  }

  /**
   * Constructor.
   */
  protected AbstractTortoiseHgEntry()
  {
    this.setMenuId(1); // TODO
    this.setMinItemsCount(1);
    this.setMaxItemsCount(1);
  }
  
  @Override
  public void executeCommand()
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = this.getCommand();
    final String parameter1 = this.getParameter1();
    final String executable = Activator.getDefault().getPreferenceStore().getString(getPreferenceConstants().getPath());
    
    arguments.add(command);
    Set<IResource> currentResources = new ContextMenuEnvironment().getSelectedResources();
    if (currentResources.size() == 1)
    {
      IPath selectedPath = currentResources.iterator().next().getLocation();
      File workingCopyRoot = new File(this.getWorkingCopyRoot(selectedPath)).getParentFile();
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
  }
}
