package contextquickie.tortoise.git.entries;

import org.eclipse.core.resources.IResource;

import contextquickie.tools.ContextMenuEnvironment;
import rolandomagico.jniregistry.Registry;

public class DiffLater extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 232;

  @Override
  public void executeCommand()
  {
    final String registryUserDirectory = getPreferenceConstants().getRegistryUserDirectory();
    final IResource resource = new ContextMenuEnvironment().getSelectedResources().stream().findFirst().orElse(null);
    if ((resource != null) && (resource.getType() == IResource.FILE))
    {
      Registry registry = new Registry();
      registry.writeKey(registryUserDirectory, "DiffLater", resource.getLocation().toOSString());
    }
  }

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffLater(String iconPath)
  {
    super(MenuTextIdentifier, "Diff later");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxFileCount(1);
    this.setMaxItemsCount(1);
  }
}
