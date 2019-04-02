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
import org.eclipse.core.runtime.IPath;
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
        IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
        IPath checkoutDir = new Path(statusCallback.getStatus().getPath());
        IPath projectLocation = project.getLocation();
        if (workspacePath.isPrefixOf(checkoutDir) && workspacePath.isPrefixOf(projectLocation))
        {
          result.add(String.join(";", projectName, checkoutUrl));
        }
        else if (checkoutDir.equals(projectLocation))
        {
          result.add(String.join(";", projectName, checkoutUrl, checkoutDir.toString()));
        }
        else
        {
          result.add(String.join(";", projectName, checkoutUrl, checkoutDir.toString(), projectLocation.toString()));
        }
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

      String projectName;
      String checkoutUrl;
      IPath projectLocation;
      IPath checkoutDirectory;
      if (entities.length == 2)
      {
        projectName = entities[0];
        checkoutUrl = entities[1];
        checkoutDirectory = projectLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(projectName);
      }
      else if (entities.length == 3)
      {
        projectName = entities[0];
        checkoutUrl = entities[1];
        checkoutDirectory = projectLocation = new Path(entities[2]);
      }
      else if (entities.length == 4)
      {
        projectName = entities[0];
        checkoutUrl = entities[1];
        checkoutDirectory = new Path(entities[2]);
        projectLocation = new Path(entities[3]);
      }
      else
      {
        throw new TeamException("Cannot parse string \"" + referenceString + "\"");
      }

      if ((checkoutDirectory.toFile().exists() == false) || (checkoutDirectory.toFile().isDirectory() == false))
      {
        SVNClient client = new SVNClient();
        try
        {
          client.checkout(checkoutUrl, checkoutDirectory.toString(), Revision.HEAD, Revision.HEAD, Depth.infinity, false, true);
        }
        catch (ClientException e)
        {
          throw new TeamException("Unable to checkout project " + projectName + " from " + checkoutUrl + " to " + checkoutDirectory.toString(), e);
        }
      }

      if ((projectLocation.toFile().exists() == false) || (projectLocation.toFile().isDirectory() == false))
      {
        throw new TeamException("Unable to find project directory " + projectLocation.toString());
      }
      else
      {
        try
        {
          IPath projectFile = projectLocation.append(".project");
          IProjectDescription projectDescription;
          if (projectFile.toFile().exists() && projectFile.toFile().isFile())
          {
            projectDescription = ResourcesPlugin.getWorkspace().loadProjectDescription(projectFile);
          }
          else
          {
            projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
            projectDescription.setLocation(projectLocation);
          }

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

    return projects.toArray(new IProject[projects.size()]);
  }
}
