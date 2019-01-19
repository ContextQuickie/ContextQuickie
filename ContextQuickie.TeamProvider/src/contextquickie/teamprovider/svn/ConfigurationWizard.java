package contextquickie.teamprovider.svn;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.eclipse.team.ui.IConfigurationWizard;
import org.eclipse.ui.IWorkbench;

public class ConfigurationWizard extends Wizard implements IConfigurationWizard
{

  private IProject _project;
  private IWorkbench _workbench;

  @Override
  public boolean performFinish()
  {
    boolean returnValue = false;
    if (this._project != null && this._workbench != null)
    {
      File currentDir = this._project.getLocation().toFile();
      File workingCopyRoot = new WorkingCopy(currentDir).getRoot();
      
      if (workingCopyRoot != null)
      {
        try
        {
          RepositoryProvider.map(this._project, SvnRepositoryProvider.class.getTypeName());
          returnValue = true;
        }
        catch (TeamException e)
        {
          e.printStackTrace();
        }
      }
      else
      {
        // TODO: display error message
      }
    }

    return returnValue;
  }

  @Override
  public void init(IWorkbench workbench, IProject project)
  {
    this._project = project;
    this._workbench = workbench;

  }
}
