package contextquickie.tortoise.git.entries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;

import contextquickie.Activator;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;

public class DiffTwoFiles extends AbstractTortoiseGitEntry
{
  private String leftSideForCompare;
  
  private String rightSideForCompare;
  
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000010000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 176;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffTwoFiles(String iconPath)
  {
    super(MenuTextIdentifier, "Diff");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxItemsCount(2);
    this.setMinItemsCount(2);
    this.setMaxFolderCount(0);
  }

  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    if (super.isVisible(environment))
    {
      Iterator<IPath> iterator = environment.getSelectedFiles().iterator();
      this.leftSideForCompare = iterator.next().toOSString();
      this.rightSideForCompare = iterator.next().toOSString();
      return true;
    }
    
    return false;
  }

  @Override
  public void executeCommand()
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = Activator.getDefault().getPreferenceStore().getString(getPreferenceConstants().getPath());

    arguments.add("/command:diff");
    arguments.add("/path:" + StringUtil.quoteString(this.leftSideForCompare));
    arguments.add("/path2:" + StringUtil.quoteString(this.rightSideForCompare));
    new ProcessWrapper().executeCommand(command, new ContextMenuEnvironment().getSelectedResources(), arguments);
  }
}
