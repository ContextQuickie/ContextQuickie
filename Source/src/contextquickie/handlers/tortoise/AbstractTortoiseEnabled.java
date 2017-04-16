package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.WorkbenchUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextSelection;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if the Tortoise add-in is enabled in the
 *         settings. It is used to show/hide the Tortoise add-in context menu
 *         entries.
 */
public abstract class AbstractTortoiseEnabled extends PropertyTester implements FileFilter
{
  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferences;

  /**
   * Default constructor.
   * 
   * @param tortoisePreferences
   *          The preferences which are required for execution.
   */
  protected AbstractTortoiseEnabled(final TortoisePreferenceConstants tortoisePreferences)
  {
    this.preferences = tortoisePreferences;
  }

  @Override
  public final boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue)
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final boolean addinEnabled = preferenceStore.getBoolean(this.preferences.getEnabled());
    final boolean detectWorkingCopy = preferenceStore.getBoolean(this.preferences.getWorkingCopyDetection());
    boolean result = false;
    if ((addinEnabled == true) && (detectWorkingCopy == false))
    {
      result = true;
    }
    else if ((addinEnabled == true) && (detectWorkingCopy == true))
    {
      final Collection<?> selection = (Collection<?>) receiver;
      for (Object selectedItem : selection)
      {
        IResource resource = null;
        if (selectedItem instanceof IAdaptable)
        {
          final IAdaptable adaptable = (IAdaptable) selectedItem;
          resource = adaptable.getAdapter(IResource.class);
        }
        else if (selectedItem instanceof TextSelection)
        {
          resource = WorkbenchUtil.getCurrentDocument();
        }

        if (resource != null)
        {
          // Get directory of currently selected item
          if (((resource.getType() & (IResource.FOLDER) | IResource.PROJECT | IResource.FILE)) != IResource.NONE)
          {
            if (this.getWorkingCopyRoot(resource.getLocation()) != null)
            {
              return true;
            }
          }
        }
      }
    }

    return result;
  }

  @Override
  public final boolean accept(final File dir)
  {
    return dir.isDirectory() && this.preferences.getWokringCopyFolderName().equals(dir.getName());
  }

  /**
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  public String getWorkingCopyRoot(final IPath path)
  {
    File currentPath = path.toFile();
    if (currentPath.isFile())
    {
      currentPath = currentPath.getParentFile();
    }

    while ((currentPath != null) && (currentPath.isDirectory()))
    {
      final File[] childItems = currentPath.listFiles(this);
      if ((childItems != null) && (childItems.length > 0))
      {
        return childItems[0].getAbsolutePath();
      }
      currentPath = currentPath.getParentFile();
    }

    return null;
  }
}
