package contextquickie;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import contextquickie.tortoise.svn.ResourceChangeListener;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin
{
  /**
   * The plug-in ID.
   */
  public static final String PLUGIN_ID = "ContextQuickie";

  /**
   * The shared instance.
   */
  private static Activator plugin;
  
  private ResourceChangeListener svnResourceChangeListener;

  @Override
  public final void start(final BundleContext context) throws Exception
  {
    super.start(context);
    plugin = this;
    this.svnResourceChangeListener = new ResourceChangeListener();
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this.svnResourceChangeListener, IResourceChangeEvent.POST_CHANGE);
  }

  @Override
  public final void stop(final BundleContext context) throws Exception
  {
    plugin = null;
    super.stop(context);
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this.svnResourceChangeListener);
  }

  /**
   * Returns the shared instance.
   *
   * @return the shared instance
   */
  public static Activator getDefault()
  {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in
   * relative path.
   *
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(final String path)
  {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }
}
