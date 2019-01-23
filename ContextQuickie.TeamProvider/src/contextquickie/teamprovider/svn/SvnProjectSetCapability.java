package contextquickie.teamprovider.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.subversion.javahl.ClientException;
import org.apache.subversion.javahl.SVNClient;
import org.apache.subversion.javahl.types.Depth;
import org.apache.subversion.javahl.types.Revision;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.core.ProjectSetCapability;
import org.eclipse.team.core.ProjectSetSerializationContext;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;

public class SvnProjectSetCapability extends ProjectSetCapability
{
  @Override
  public void projectSetCreated(File file, ProjectSetSerializationContext context, IProgressMonitor monitor)
  {
    // TODO Auto-generated method stub
    super.projectSetCreated(file, context, monitor);
  }

  @Override
  public String[] asReference(IProject[] providerProjects, ProjectSetSerializationContext context, IProgressMonitor monitor) throws TeamException
  {
    List<String> result = new ArrayList<String>();
    for (IProject project : providerProjects)
    {
      SVNClient client = new SVNClient();
      SvnStatusCallback statusCallback = new SvnStatusCallback();
      File workingCopyRoot = new WorkingCopy(project.getLocation().toFile()).getRoot();
      if (workingCopyRoot != null)
      {
        try
        {
          client.status(workingCopyRoot.getAbsolutePath(), Depth.empty, false, true, true, true, false, true, null, statusCallback);
        }
        catch (ClientException e)
        {
          throw new TeamException("Unable to get status of project " + project.getName(), e);
        }

        String projectName = project.getName();
        String checkoutUrl = statusCallback.getStatus().getUrl();
        String checkoutDir = this.convertToWorkspaceRelativePath(statusCallback.getStatus().getPath());
        String projectLocation = this.convertToWorkspaceRelativePath(project.getLocation().toString());
        result.add(String.join(";", projectName, checkoutUrl, checkoutDir, projectLocation));
      }
      else
      {
        throw new TeamException("Unable to find working copy root of project " + project.getName());
      }
    }

    return result.toArray(new String[result.size()]);
  }

  @Override
  public IProject[] addToWorkspace(String[] referenceStrings, ProjectSetSerializationContext context, IProgressMonitor monitor) throws TeamException
  {
    List<IProject> projects = new ArrayList<IProject>();
    for (String referenceString : referenceStrings)
    {
      String[] entities = referenceString.split(";");
      if (entities.length == 4)
      {
        String projectName = entities[0];
        String checkoutUrl = entities[1];
        File checkoutDirFile = this.convertToAbsolutePath(entities[2]);
        File projectLocationFile = this.convertToAbsolutePath(entities[3]);
        if ((checkoutDirFile.exists() == false) || (checkoutDirFile.isDirectory() == false))
        {
          SVNClient client = new SVNClient();
          try
          {
            client.checkout(checkoutUrl, checkoutDirFile.toString(), Revision.HEAD, Revision.HEAD, Depth.infinity, false, true);
          }
          catch (ClientException e)
          {
            throw new TeamException("Unable to checkout project " + projectName + " from " + checkoutUrl + " to " +  checkoutDirFile.toString(), e);
          }
        }
        
        if ((projectLocationFile.exists() == false) || (projectLocationFile.isDirectory() == false))
        {
          throw new TeamException("Unable to find project directory " + projectLocationFile.toString());
        }
        else
        {
          try
          {
            IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().loadProjectDescription(Path.fromOSString(new File(projectLocationFile, ".project").getAbsolutePath()));
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectDescription.getName());
            project.create(projectDescription, monitor);
            project.open(monitor);
            RepositoryProvider.map(project, SvnRepositoryProvider.class.getTypeName());
          }
          catch (CoreException e)
          {
            throw new TeamException("Unable to create project " + projectName, e);
          }
        }
      }
      else
      {
        throw new TeamException("Cannot parse string \"" + referenceString + "\"");
      }
    }
    
    return projects.toArray(new IProject[projects.size()]);
  }

  private String convertToWorkspaceRelativePath(String source)
  {   
    String workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath();
    return new File(source).getAbsolutePath().replace(workspaceLocation, "${WORKSPACE_LOC}");
  }
  
  private File convertToAbsolutePath(String source)
  {   
    String workspaceLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath();
    return new File(source.replace("${WORKSPACE_LOC}", workspaceLocation));
  }
}
