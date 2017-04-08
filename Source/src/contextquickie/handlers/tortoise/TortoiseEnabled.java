package contextquickie.handlers.tortoise;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextSelection;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import contextquickie.tools.WorkbenchUtil;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if the Tortoise add-in is enabled in the
 *         settings. It is used to show/hide the Tortoise add-in context menu
 *         entries.
 */
public abstract class TortoiseEnabled extends PropertyTester implements FileFilter
{
  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants _preferences;

  protected TortoiseEnabled(TortoisePreferenceConstants preferences)
  {
    this._preferences = preferences;
  }

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
  {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    boolean addinEnabled = preferenceStore.getBoolean(this._preferences.getEnabled());
    boolean detectWorkingCopy = preferenceStore.getBoolean(this._preferences.getWorkingCopyDetection());

    if ((addinEnabled == true) && (detectWorkingCopy == false))
    {
      return true;
    }
    else if ((addinEnabled == true) && (detectWorkingCopy == true))
    {
      Collection<?> selection = (Collection<?>) receiver;
      for (Object selectedItem : selection)
      {
        IResource resource = null;
        if (selectedItem instanceof IAdaptable)
        {
          IAdaptable adaptable = (IAdaptable) selectedItem;
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

    return false;
  }

  @Override
  public boolean accept(File dir)
  {
    return (dir.isDirectory() && this._preferences.getWokringCopyFolderName().equals(dir.getName()));
  }

  /**
   * Gets the working copy root directory of the specific directory.
   * 
   * @param path
   *          The directory which is used for start of the working copy search.
   * @return The path to the working copy or null if no working copy has been
   *         found.
   */
  public String getWorkingCopyRoot(IPath path)
  {
    File currentPath = path.toFile();
    if (currentPath.isFile())
    {
      currentPath = currentPath.getParentFile();
    }

    while ((currentPath != null) && (currentPath.isDirectory()))
    {
      File[] childItems = currentPath.listFiles(this);
      if ((childItems != null) && (childItems.length > 0))
      {
        return childItems[0].getAbsolutePath();
      }
      currentPath = currentPath.getParentFile();
    }

    return null;
  }
}
