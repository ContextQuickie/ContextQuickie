package contextquickie.teamprovider.svn;

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
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc2.SvnCopy;
import org.tmatesoft.svn.core.wc2.SvnCopySource;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import contextquickie.teamprovider.Activator;

public class ResourceDeltaVisitor implements IResourceDeltaVisitor
{
  /**
   * This job group avoids concurrent access to the working copy and guarantees the order of execution of the created jobs.
   */
  private final JobGroup jobGroup = new JobGroup(Activator.PLUGIN_ID, 1, 1);

  /**
   * The default message which is displayed during execution of a job.
   */
  private final static String DEFAULT_JOB_RUN_MESSAGE = "Applying changes to working copy";

  /**
   * The default message which is displayed for a job error.
   */
  private final static String DEFAULT_JOB_ERROR_MESSAGE = "Error during applying changes to working copy";

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
            if ((moveInformation != null) && 
                (moveInformation.getSource().equals(delta.getResource())) && 
                (moveInformation.getDestination().getFullPath().equals(delta.getMovedToPath())))
            {
              System.out.format("resourceChanged: moved %s to %s\n", 
                  moveInformation.getSource().getLocation().toOSString(),
                  moveInformation.getDestination().getLocation().toOSString());
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
              System.out.format("resourceChanged: copied %s to %s\n", 
                  copyInformation.getSource().getLocation().toOSString(),
                  copyInformation.getDestination().getLocation().toOSString());
              svnResourceRuleFactory.setLastCopyInformation(null);
              this.doVirtualCopy(copyInformation, false);

            }
            else if (delta.getResource().equals(svnResourceRuleFactory.getLastCreatedResource()))
            {
              System.out.format("resourceChanged: added %s\n", svnResourceRuleFactory.getLastCreatedResource());
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

    // Add new items only if the target parent and the source element is already under version control
    if ((parent != null) && (ResourceDeltaVisitor.isResourceVersioned(parent)))
    {
      Job job = new Job(DEFAULT_JOB_RUN_MESSAGE)
      {
        protected IStatus run(IProgressMonitor monitor)
        {
          IStatus status = Status.OK_STATUS;
          
            try
            {
              SVNClientManager.newInstance().getWCClient().doAdd(
                  resource.getLocation().toFile(), 
                  true, false, true, SVNDepth.EMPTY, true, false);
            }
            catch (SVNException e)
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
          SVNClientManager clientManager = SVNClientManager.newInstance();

          if (ResourceDeltaVisitor.isResourceVersioned(source))
          {
            SvnCopy svnCopy = clientManager.getCopyClient().getOperationsFactory().createCopy();
            svnCopy.addCopySource(SvnCopySource.create(SvnTarget.fromFile(source.getLocation().toFile()), SVNRevision.WORKING));
            svnCopy.setSingleTarget(SvnTarget.fromFile(target.getLocation().toFile()));
            svnCopy.setMove(move);
            svnCopy.setVirtual(false);
            svnCopy.setMetadataOnly(true);
            svnCopy.setMakeParents(true);
            svnCopy.run();
          }
        }
        catch (SVNException e)
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
    SVNClientManager clientManager = SVNClientManager.newInstance();
    boolean result = false;
    try
    {
      result = clientManager.getStatusClient().doStatus(resource.getLocation().toFile(), false).isVersioned();
    }
    catch (SVNException e)
    {
      // Status of parent element cannot be determined. This can happen if the parent element is an ignored folder
      // => Ignore this exception
    }

    return result;
  }
}
