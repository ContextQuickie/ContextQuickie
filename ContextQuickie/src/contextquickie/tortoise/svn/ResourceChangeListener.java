package contextquickie.tortoise.svn;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * @author ContextQuickie
 * 
 * Class for monitoring changes in the eclipse environment to apply the changes in the working copy.
 *
 */
public class ResourceChangeListener implements IResourceChangeListener
{
  @Override
  public void resourceChanged(IResourceChangeEvent event)
  {
      try
      {
        event.getDelta().accept(new IResourceDeltaVisitor() {
          public boolean visit(IResourceDelta delta) {
            int deltaKind = delta.getKind();
            int deltaFlags = delta.getFlags();
            
            if (((deltaKind & IResourceDelta.REMOVED) != 0) && ((deltaFlags & IResourceDelta.MOVED_TO) != 0))
            {
              // System.out.format("Moved %s to %s\n", delta.getResource().getFullPath().toOSString(), delta.getMovedToPath().toOSString());
            }
            
            if (((deltaKind & IResourceDelta.ADDED) != 0) && ((deltaFlags & IResourceDelta.COPIED_FROM) != 0))
            {
              // Currently detecting COPIED_FROM doesn't work. See https://bugs.eclipse.org/bugs/show_bug.cgi?id=217489 and 
              // https://bugs.eclipse.org/bugs/show_bug.cgi?id=109166 for details
              // System.out.format("Copied %s to %s\n", delta.getResource().getFullPath().toOSString(), delta.getResource().getFullPath().toOSString());
            }

            return true;
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
