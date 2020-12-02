package contextquickie.teamprovider.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.subversion.javahl.ClientException;
import org.apache.subversion.javahl.SVNClient;
import org.apache.subversion.javahl.types.CopySource;
import org.apache.subversion.javahl.types.Depth;
import org.apache.subversion.javahl.types.Revision;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobGroup;
import org.eclipse.team.core.RepositoryProvider;

import contextquickie.teamprovider.Activator;

public class ResourceDeltaVisitor implements IResourceDeltaVisitor
{
  /**
   * This job group avoids concurrent access to the working copy and 
   * guarantees the order of execution of the created jobs.
   */
  private final JobGroup jobGroup = new JobGroup(Activator.PLUGIN_ID, 1, 1);

  /**
   * The default message which is displayed during execution of a job.
   */
  private static final String DEFAULT_JOB_RUN_MESSAGE = "Applying changes to working copy";

  /**
   * The default message which is displayed for a job error.
   */
  private static final String DEFAULT_JOB_ERROR_MESSAGE = "Error during applying changes to working copy";

  @Override
  public boolean visit(IResourceDelta delta) throws CoreException
  {
    if ((delta.getResource() != null) && (delta.getResource().getProject() != null))
    {
      final RepositoryProvider repositoryProvider = RepositoryProvider.getProvider(delta.getResource().getProject());
      if (repositoryProvider instanceof SvnRepositoryProvider)
      {
        int deltaKind = delta.getKind();
        int deltaFlags = delta.getFlags();
        final SvnRepositoryProvider svnRepositoryProvider = (SvnRepositoryProvider) repositoryProvider;
        final SvnResourceRuleFactory svnResourceRuleFactory = (SvnResourceRuleFactory) svnRepositoryProvider.getRuleFactory();

        if (((deltaKind & IResourceDelta.REMOVED) != 0) && ((deltaFlags & IResourceDelta.MOVED_TO) != 0))
        {
          CopyMoveInformation moveInformation = svnResourceRuleFactory.getLastMoveInformation();
          if ((moveInformation != null) && (moveInformation.getSource().equals(delta.getResource()))
              && (moveInformation.getDestination().getFullPath().equals(delta.getMovedToPath())))
          {
            svnResourceRuleFactory.setLastMoveInformation(null);

            this.doVirtualCopy(moveInformation, true);
          }
        }
        else if ((deltaKind & IResourceDelta.ADDED) != 0)
        {
          // Currently detecting COPIED_FROM doesn't work. See
          // https://bugs.eclipse.org/bugs/show_bug.cgi?id=217489 and
          // https://bugs.eclipse.org/bugs/show_bug.cgi?id=109166 for
          // details. Use information from team provider instead.
          // if ((deltaFlags & IResourceDelta.COPIED_FROM) != 0)

          CopyMoveInformation copyInformation = svnResourceRuleFactory.getLastCopyInformation();
          if ((copyInformation != null) && (copyInformation.getDestination().equals(delta.getResource())))
          {
            svnResourceRuleFactory.setLastCopyInformation(null);
            this.doVirtualCopy(copyInformation, false);

          }
          else if (delta.getResource().equals(svnResourceRuleFactory.getLastCreatedResource()))
          {
            svnResourceRuleFactory.setLastCreatedResource(null);
            this.doAdd(delta.getResource());
          }
        }
      }
    }

    return true;
  }

  private void doAdd(IResource resource)
  {
    IResource parent = resource.getParent();

    // Add new items only if the target parent and the source element is already
    // under version control
    if ((parent != null) && (ResourceDeltaVisitor.isResourceVersioned(parent)))
    {
      Job job = new Job(DEFAULT_JOB_RUN_MESSAGE)
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          IStatus status = Status.OK_STATUS;

          try
          {
            SVNClient client = new SVNClient();
            client.add(resource.getLocation().toOSString(), Depth.empty, true, false, true);
          }
          catch (ClientException e)
          {
            status = new Status(Status.ERROR, Activator.PLUGIN_ID, DEFAULT_JOB_ERROR_MESSAGE, e);
          }

          return status;
        }
      };

      job.setJobGroup(jobGroup);
      job.schedule();
    }
  }

  private void doVirtualCopy(CopyMoveInformation copyMoveInformation, boolean move)
  {
    Job job = new Job(DEFAULT_JOB_RUN_MESSAGE)
    {
      protected IStatus run(IProgressMonitor monitor)
      {
        IStatus status = Status.OK_STATUS;
        IResource source = copyMoveInformation.getSource();
        IResource target = copyMoveInformation.getDestination();
        IResource targetParent = target.getParent();
        if (targetParent != null)
          try
          {
            SVNClient client = new SVNClient();

            if (ResourceDeltaVisitor.isResourceVersioned(source) && (ResourceDeltaVisitor.isResourceIgnored(target.getParent()) == false))
            {
              if ((source.getType() == IResource.FOLDER) && (target.getType() == IResource.FOLDER))
              {
                if (move)
                {
                  HashSet<String> sources = new HashSet<String>();
                  sources.add(source.getLocation().toOSString());
                  client
                      .move(sources, // srcPaths
                          target.getLocation().toOSString(), false, // force
                          false, // moveAsChild
                          true, // makeParents
                          true, // metadataOnly
                          true, // allowMixRev
                          null, // revpropTable
                          null, // handler
                          null); // callback
                }
                else
                {
                  List<CopySource> sources = new ArrayList<CopySource>();
                  sources.add(new CopySource(source.getLocation().toOSString(), Revision.WORKING, Revision.WORKING));
                  client.copy(sources, target.getLocation().toOSString(), false, true, false, true, false, null, null, null, null);
                }
              }
              else if ((source.getType() == IResource.FILE) && (target.getType() == IResource.FILE))
              {
                if (move)
                {
                  HashSet<String> sources = new HashSet<String>();
                  sources.add(source.getLocation().toOSString());
                  client
                      .move(sources, // srcPaths
                          target.getLocation().toOSString(), false, // force
                          false, // moveAsChild
                          true, // makeParents
                          true, // metadataOnly
                          true, // allowMixRev
                          null, // revpropTable
                          null, // handler
                          null); // callback
                }
                else
                {
                  List<CopySource> sources = new ArrayList<CopySource>();
                  sources.add(new CopySource(source.getLocation().toOSString(), Revision.WORKING, Revision.WORKING));
                  client.copy(sources, target.getLocation().toOSString(), false, true, false, true, false, null, null, null, null);
                }
              }
            }
          }
          catch (ClientException e)
          {
            status = new Status(Status.ERROR, Activator.PLUGIN_ID, DEFAULT_JOB_ERROR_MESSAGE, e);
          }

        return status;
      }
    };

    job.setJobGroup(jobGroup);
    job.schedule();
  }

  private static boolean isResourceVersioned(final IResource resource)
  {
    SvnStatusCallback statusCallback = new SvnStatusCallback();
    try
    {
      SVNClient client = new SVNClient();
      client.status(resource.getLocation().toOSString(), Depth.empty, false, true, true, true, false, true, null, statusCallback);
    }
    catch (ClientException e)
    {
      // Status of parent element cannot be determined. This can happen if the parent element is an ignored folder
      // => Ignore this exception
    }

    if (statusCallback.getStatus() != null)
    {
      return statusCallback.getStatus().isManaged();
    }
    return false;
  }

  private static boolean isResourceIgnored(final IResource resource)
  {
    File workingCopyRoot = new WorkingCopy(resource.getLocation().toFile()).getRoot();
    if (workingCopyRoot != null)
    {
      SVNClient client = new SVNClient();
      IResource currentResource = resource;
      SvnStatusCallback statusCallback = new SvnStatusCallback();
      while ((statusCallback.getStatus() == null) && (currentResource != null)
          && (currentResource.getLocation().toFile().equals(workingCopyRoot) == false))
      {
        try
        {
          client.status(currentResource.getLocation().toOSString(), Depth.empty, false, true, true, true, false, true, null, statusCallback);
        }
        catch (ClientException e)
        {
          System.out.println(e.toString());
          // Status of parent element cannot be determined. This can happen if
          // the parent element is an ignored folder
          // => Ignore this exception
        }

        if (statusCallback.getStatus() != null)
        {
          return statusCallback.getStatus().isIgnored();
        }

        currentResource = currentResource.getParent();
      }
    }

    return false;
  }
}
