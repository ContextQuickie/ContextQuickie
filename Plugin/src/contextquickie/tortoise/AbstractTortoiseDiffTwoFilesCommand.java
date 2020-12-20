package contextquickie.tortoise;

import java.util.ArrayList;
import java.util.List;

import contextquickie.Activator;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

/**
 * Base class for execute a Tortoise diff command with two selected files.
 */
public abstract class AbstractTortoiseDiffTwoFilesCommand extends AbstractTortoiseHandler
{
  /**
   * The name of command parameter which stores the left side for comparison.
   */
  public static final String LeftSideParameterName = "LeftSide";

  /**
   * The name of command parameter which stores the right side for comparison.
   */
  public static final String RightSideParameterName = "RightSide";

  public void execute(String left, String right)
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = Activator.getDefault().getPreferenceStore().getString(this.getPreferenceConstants().getPath());

    arguments.add("/command:diff");
    arguments.add("/path:" + StringUtil.quoteString(left));
    arguments.add("/path2:" + StringUtil.quoteString(right));
    new ProcessWrapper().executeCommand(command, new ContextMenuEnvironment().getSelectedResources(), arguments);
  }

}
