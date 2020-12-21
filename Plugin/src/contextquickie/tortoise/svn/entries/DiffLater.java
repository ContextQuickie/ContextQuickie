package contextquickie.tortoise.svn.entries;

import org.eclipse.core.resources.IResource;

import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tortoise.svn.MenuTextIdentifier;
import rolandomagico.jniregistry.Registry;

public class DiffLater extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000001000000000L;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffLater(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUDIFFLATER, "Diff later");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxItemsCount(0); // TODO: Disabled
    this.setCommand("diff");
  }

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
}
