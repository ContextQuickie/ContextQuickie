package contextquickie.teamprovider.svn;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.core.RepositoryProvider;

/**
 * @author ContextQuickie
 * 
 *         Class for monitoring changes in the eclipse environment to apply the
 *         changes in the working copy.
 *
 */
public class ResourceChangeListener implements IResourceChangeListener
{
  @Override
  public void resourceChanged(IResourceChangeEvent event)
  {
    try
    {
      event.getDelta().accept(new IResourceDeltaVisitor()
      {
        public boolean visit(IResourceDelta delta)
        {
          boolean returnValue = true;
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
                  returnValue = false;
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
                  returnValue = false;
                }
                else if (delta.getResource().equals(svnResourceRuleFactory.getLastCreatedResource()))
                {
                  System.out.format("resourceChanged: added %s\n", svnResourceRuleFactory.getLastCreatedResource());
                  svnResourceRuleFactory.setLastCreatedResource(null);
                  returnValue = false;
                }
              }
            }
          }

          return returnValue;
        }
      });
    }
    catch (CoreException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
